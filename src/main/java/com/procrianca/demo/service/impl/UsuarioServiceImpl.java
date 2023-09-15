package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.dtos.UserRecordDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.procrianca.demo.domain.entity.Usuario;
import com.procrianca.demo.domain.repository.UsuarioRepository;
import com.procrianca.demo.exception.SenhaInvalidaException;

import jakarta.transaction.Transactional;

import java.util.List;


@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public UserRecordDto salvar(Usuario user){
        if (user == null)
            throw new NullPointerException();

        UserRecordDto userRecordDto = new UserRecordDto(user.getLogin(), user.getSenha(), user.getCreatedAt(), user.getUpdatedAt());

        BeanUtils.copyProperties(user, userRecordDto);
        repository.save(user);

        return userRecordDto;
    }

    public UserDetails autenticar(UserRecordDto userRecordDto){
        UserDetails user = loadUserByUsername(userRecordDto.login());
        boolean passwordMatch = encoder.matches(userRecordDto.password(), user.getPassword());

        if(passwordMatch){
            
            return user;
        }
        throw new SenhaInvalidaException();
    }

    public List<Usuario> listAllUsers(){
        return this.repository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado na base de dados!"));
        
       String[] roles = usuario.isAdmin() ? new String[] {"ADMIN","USER"} : new String[] {"USER"};

       return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}