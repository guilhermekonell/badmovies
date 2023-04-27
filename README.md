# Bad Movies API

Aplicação desenvolvida para ler a lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Tecnologias
- JDK 20
- Spring Boot
- H2 Database

## Getting Started
- Clone este repositório
- Abra o projeto em sua IDE de preferência
- Instale as dependências Maven
- Configure as propriedades da aplicação no arquivo `application.properties`:
  - `file.movieList.path`: caminho para o arquivo que contém a lista de indicados e vencedores para o Pior Filme do Golden Raspberry Awards.

Para rodar a aplicação:
- Execute a classe `BadMoviesApplication.java`

Para executar os testes de integração:
- Execute a classe `BadMoviesApplicationTests.java`

## ENDPOINT
Endpoint para obter o produtor com maior intervalo entre dois prêmios consecutivos e o que obteve dois prêmios mais rápido.

Para acessar:
- Acesse em seu navegador: http://localhost:8080/producer/min-max-interval-winners
- Ou execute em seu console: `curl http://localhost:8080/producer/min-max-interval-winners`

## Database
Para acessar o console da base de dados H2, acesse: http://localhost:8080/h2-console
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:goldenRaspberryAwards`
- User Name: `sa`
- Password: `password`
