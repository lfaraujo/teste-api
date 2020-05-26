# Teste - Vivo API
Projeto de teste para criação de uma API

### Tecnologias utilizadas
- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Docker](https://www.docker.com/products/docker-desktop)
- [RabbitMQ](https://www.rabbitmq.com/#getstarted)
- [MySQL](https://www.mysql.com/downloads/)

### Documentação da aplicação
A documentação da aplicação foi gerada utilizando o [Swagger](https://swagger.io/tools/). 
É possível observar os paths de acesso, bem como efetuar chamadas direto da UI ao visitar http://localhost:8080/swagger-ui.html

### Subindo a aplicação
Para rodar a aplicação, você precisa:
- Instalar o **MySQL** e criar o a base **vivoapi** com o usuário **root** e senha **password** (ou subir imagem **mysql:5.7** com o Docker e definir environment para os itens anteriores, além da porta **3306**)
- Instalar o **RabbitMQ** com acesso para usuário **guest** e senha **guest** (ou subir imagem **rabbitmq:3.8.4-rc.3-management** com o Docker e definir portas **5672** e **15672**)
- Possuir o **Java 11** e o **Maven** instalados e configurados e rodar o servidor local (ou subir **imagem** deste projeto através do [repositório do DockerHub](https://hub.docker.com/repository/docker/felipekx/vivo-api))

> Para mais definições de como configurar as variáveis de ambiente das imagens, favor verificar arquivo [docker-compose.yml](https://github.com/lfaraujo/teste-api/blob/master/docker-compose.yml)

> Para subir a aplicação dentro do mesmo contâiner das demais imagens, basta alterar o "localhost" contido no [application.properties](https://github.com/lfaraujo/teste-api/blob/master/src/main/resources/application.properties) para os valores contidos em "container_name" dentro do arquivo [docker-compose.yml](https://github.com/lfaraujo/teste-api/blob/master/docker-compose.yml)

### Observações acerca do ambiente de desenvolvimento utilizado
- Sistema Operacional: Windows 10 x64
- IDE: STS 4
- Docker v19.03.8
- MySQL 5.7
- RabbitMQ 3.8.4
