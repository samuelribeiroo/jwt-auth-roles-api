### Introdução
Java17 - Autenticação e Autorização com Spring Security, JWT Tokens e Roles.

### Descrição do Projeto

Este projeto é a replicação de um sistema que possui três funcionalidades principais: Criação de usuário, autenticação e autorização utilizando as tecnologias: Spring Security, JWT Tokens e PostgreSQL.

O processo de construção deste projeto ocorreu devido à necessidade de validar meus conhecimentos no estudo da linguagem Java e dos frameworks SpringBoot/Security, além de aplicar o padrão JWT, que é amplamente utilizado no mercado.

O projeto consiste em duas rotas principais: `/api/sign-up` e `/product`. Ambas passam pela validação feita pelo método `SecurityFilterChain`, que aplica critérios de segurança em todas as rotas (incluindo quem pode acessar, baseado na validação do token que contém informações sobre as autorizações).

- ✅ Usuários que são persistidos no banco e que possuem no payload do token a role "user" não podem criar produtos.
- ✅ Usuários persistidos no banco e que contêm a role "admin" podem acessar a rota `/product` e inserir produtos.

Para o design da aplicação, foi aplicada a arquitetura `MVC - Model, View e Controller`, onde a aplicação é codificada atendendo aos princípios de separação de responsabilidades, modularidade e facilidade de manutenção.

Juntamente com a arquitetura `MVC`, foi aplicado o padrão `Repository Pattern`, que serve como uma camada de persistência intermediária entre o data source da aplicação (SQL, NoSQL, API, GraphQL) e o restante da aplicação.

Além disso, a aplicação segue os conceitos de implementação do padrão `REST`, contendo nomes de endpoints significativos e status codes correspondentes a cada requisição.


### Requisitos
- Java 17
- Maven 3.6+

### Instalação
1. Clone o repositório:
   ```sh
   git clone https://github.com/samuelribeiroo/jwt-auth-roles-api.git
   ```
2. Navegue até o diretório do projeto:
   ```sh
   cd jwt-auth-roles-api
   ```
3. Compile o projeto:
   ```sh
   mvn clean install
   ```
4. Execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

### Dependências
As principais dependências utilizadas no projeto são:
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-web`
- `spring-boot-devtools`
- `postgresql`
- `lombok`
- `spring-boot-starter-test`
- `spring-security-test`
- `jjwt-api`
- `jjwt-impl`
- `jjwt-jackson`

### Versão
- Versão do projeto: `0.0.1-SNAPSHOT`
- Versão do Spring Boot: `3.3.6`
- Versão do JJWT: `0.11.5`

### Como instalar
1. Certifique-se de ter o Java 17 e o Maven instalados.
2. Clone o repositório e navegue até o diretório do projeto.
3. Certifique de ter o Docker instalado e rode o arquivo `docker-compose.yml`
4. Compile o projeto utilizando `mvn clean install`.
5. Execute o projeto com `mvn spring-boot:run`.

### Contato

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/samuel-ribeiro-dev/)
