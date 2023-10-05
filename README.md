# Pro Criança API
#### O Movimento Pró Criança é uma entidade sem fins lucrativos, ligada à Igreja Católica, que visa minimizar as dificuldades vivenciadas pelos jovens carentes da Região Metropolitana por meio de trabalhos sociais. Com o objetivo de abordar essa demanda de maneira abrangente, estamos propondo um projeto voltado para o desenvolvimento de um sistema de cadastro e busca altamente eficiente. Nosso foco principal é garantir a facilidade de uso do sistema, além de oferecer portabilidade, confiabilidade, escalabilidade e aderência a princípios éticos.

## Documentação Técnica

### Tecnologias Utilizadas
- Java Development Kit (JDK) 8 ou superior
- Spring Boot
- Apache Maven
- MySQL (ou qualquer outro banco de dados relacional compatível)

## Documentação API: CRUD e Login de Usuários

A nossa API inclui os seguintes endpoints para operações de CRUD (Criar, Ler, Atualizar, Excluir) e login de usuários:

### 1. Criar um novo usuário

- Método: POST
- Rota: /usuario
- Parâmetros da Requisição: Informações do novo usuário (login, senha, admin, createdAt, updatedAt)
- Descrição: Cria um novo usuário no sistema.
- [!OBS] o parâmetro admin é um booleano que distingue os usuários master dos usuários colaboradores.

### 2. Autenticar um usuário

- Método: POST
- Rota: /auth
- Parâmetros da Requisição: Login e senha do usuário
- Descrição: Permite que um usuário faça login no sistema.

### 3. Obter informações de um usuário

- Método: GET
- Rota: /usuarios/{id}
- Parâmetros da Requisição: ID do usuário
- Descrição: Retorna as informações de um usuário específico com base no ID fornecido.

### 4. Atualizar informações de um usuário

- Método: PUT
- Rota: /usuarios/{id}
- Parâmetros da Requisição: ID do usuário e informações atualizadas
- Descrição: Atualiza as informações de um usuário existente com base no ID fornecido.

### 5. Excluir um usuário

- Método: DELETE
- Rota: /usuarios/{id}
- Parâmetros da Requisição: ID do usuário
- Descrição: Exclui um usuário existente com base no ID fornecido.

- ## Autores

- [Gabrielle Rodrigues Machado da Silva](https://github.com/gabrielle-1) - [Perfil no LinkedIn](https://www.linkedin.com/in/gabrielle-1/)
- [Flávio Vinicius](https://github.com/FVSSANTOS/) - [Perfil no LinkedIn](https://www.linkedin.com/in/flavio-vinicius-programador/)
- [Giovanni Aguiar]() - [Perfil no LinkedIn](https://www.linkedin.com/in/giovanni-de-aguiar/)
- [Maria do Carmo](https://github.com/Madu-dev) - [Perfil no LinkedIn](https://www.linkedin.com/in/mariadocarmoalcantara/)
- [Luís Eduardo](https://github.com/LEDHU) - [Perfil no LinkedIn](https://www.linkedin.com/in/edupeixot0/)
- [Arthur Franco](https://github.com/ArthurF36) - [Perfil no LinkedIn](https://www.linkedin.com/in/arthur-franco-956031278/)
- [João Gabriel](https://github.com/Gabrielabsalao24) - [Perfil no LinkedIn]()

