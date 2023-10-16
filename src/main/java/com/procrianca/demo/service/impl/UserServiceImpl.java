package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.dtos.UserRecordDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.repository.UserRepository;
import com.procrianca.demo.exception.passwordInvalidException;

import jakarta.transaction.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public User saveUser(User user){
        var userExists = this.repository.findByLogin(user.getLogin());

        if (userExists.isPresent() || user == null || user.getLogin() == null || user.getLogin() == "") {
            return null;
        } else {
            UserRecordDto userRecordDto = new UserRecordDto(user.getLogin(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt());
            BeanUtils.copyProperties(user, userRecordDto);
            return repository.save(user);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado na base de dados!"));
        
       String[] roles = user.isAdmin() ? new String[] {"ADMIN","USER"} : new String[] {"USER"};

       return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserDetails authenticate(User userInput) {
        UserDetails user = loadUserByUsername(userInput.getLogin());

        if (correctPassword(userInput.getPassword(), user.getPassword())) {
            return user;
        }

        throw new passwordInvalidException();
    }

    private boolean correctPassword(String inputPassword, String storedPassword) {
        return encoder.matches(inputPassword, storedPassword);
    }

    public List<User> listAllUsers(){
        return this.repository.findAll();
    }


    
}