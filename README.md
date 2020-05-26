# Teste - Vivo API
Projeto de teste para criação de uma API

### Tecnologias utilizadas
- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Docker](https://www.docker.com/products/docker-desktop)
- [RabbitMQ](https://www.rabbitmq.com/#getstarted)
- [MySQL](https://www.mysql.com/downloads/)
- [FlyWay](https://flywaydb.org/download/)

### Documentação da aplicação
A documentação da aplicação foi gerada utilizando o [Swagger](https://swagger.io/tools/). 
É possível observar os paths de acesso, bem como efetuar chamadas direto da UI ao visitar http://localhost:8080/swagger-ui.html

### Subindo a aplicação
Para rodar a aplicação, você precisa:
- Instalar o **MySQL** e criar o a base **vivoapi** com o usuário **root** e senha **password** (ou subir imagem **mysql:5.7** com o Docker e definir environment para os itens anteriores, além da porta **3306**)
- Instalar o **RabbitMQ** com acesso para usuário **guest** e senha **guest** (ou subir imagem **rabbitmq:3.8.4-rc.3-management** com o Docker e definir portas **5672** e **15672**)
- Possuir o **Java 11** e o **Maven** instalados e configurados e rodar o servidor local (ou subir **imagem** deste projeto através do [repositório do DockerHub](https://hub.docker.com/repository/docker/felipekx/vivo-api))

> Para mais definições de como configurar as variáveis de ambiente das imagens, favor verificar arquivo [docker-compose.yml](https://github.com/lfaraujo/teste-api/blob/master/docker-compose.yml)

> Para subir a aplicação dentro do mesmo container das demais imagens, basta alterar o "localhost" contido no [application.properties](https://github.com/lfaraujo/teste-api/blob/master/src/main/resources/application.properties) para os valores contidos em "container_name" dentro do arquivo [docker-compose.yml](https://github.com/lfaraujo/teste-api/blob/master/docker-compose.yml), compilar novamente a aplicação e então executar o comando ***"docker build ."*** dentro do diretório onde está o Dockerfile - a partir disso, basta executar o comando ***"docker-compose up"***

### Observações acerca do ambiente de desenvolvimento utilizado
- Sistema Operacional: Windows 10 x64
- IDE: STS 4
- Docker v19.03.8
- MySQL 5.7
- RabbitMQ 3.8.4

### Explicando a arquitetura utilizada
A aplicação consiste em receber as requisições voltadas ao CDR e direcionar para a fila do gerenciador RabbitMQ, onde há 2 filas:

- Inclusão
- Exclusão

Há um listener na aplicação que contém 2 métodos (cada um apontando para uma das filas), e estes métodos serão responsáveis por chamar os serviços de inclusão/exclusão de registros de CDR. Dessa forma, não haverá concorrência por mais que haja uma grande quantidade de requisições.

Já para as requisições de inclusão de clientes e consultas, são gerenciadas de forma direta pela aplicação. Para auxiliar no registro e atualização de saldos, foram criadas 2 triggers na base de dados (associadas a produto e registro_cdr). 
