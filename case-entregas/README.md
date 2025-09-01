# Sistema de Gerenciamento de Entregas com Drones

Este é um sistema de gerenciamento de entregas utilizando drones, desenvolvido com Spring Boot e PostgreSQL.

## Pré-requisitos

- Java 17 ou superior
- Docker e Docker Compose
- Maven (ou use o wrapper do projeto ./mvnw)

## Configuração do Ambiente

### 1. Banco de Dados

O projeto utiliza PostgreSQL executando em container Docker. Para iniciar o banco de dados:

```bash
docker-compose up -d
```

Isso irá:
- Criar um container PostgreSQL na porta 5432
- Criar o banco de dados `entregas_db`
- Configurar usuário `postgres` com senha `postgres`

### 2. Configuração da Aplicação

O arquivo `application.properties` já está configurado com as credenciais corretas do banco de dados. As tabelas serão criadas automaticamente pelo Hibernate quando a aplicação iniciar.

### 3. Executando a Aplicação

Para executar o projeto, você pode usar o Maven wrapper incluído:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Ou se preferir usar o Maven instalado no sistema:

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em: http://localhost:8080

## Endpoints da API

### Drones
- POST /drone - Cadastrar novo drone
- GET /drone - Listar drones (com filtros opcionais)
- DELETE /drone/{id} - Remover drone

### Pedidos
- POST /pedido - Criar novo pedido
- GET /pedido - Listar pedidos (com filtros opcionais)
- DELETE /pedido/{id} - Remover pedido

### Entregas
- POST /entrega - Criar nova entrega
- GET /entrega - Listar entregas (com filtros opcionais)
- DELETE /entrega/{id} - Remover entrega
- GET /entrega/pendentes - Listar entregas pendentes
- POST /entrega/processar - Processar entregas pendentes

## Testes

Para executar os testes unitários:

```bash
./mvnw test
```

## Parando o Ambiente

Para parar o banco de dados:

```bash
docker-compose down
```

Para remover também os volumes persistentes:

```bash
docker-compose down -v
```

## Troubleshooting

### Problemas comuns:

1. **Porta 5432 em uso**
   - Verifique se não há outra instância do PostgreSQL rodando
   - Altere a porta no docker-compose.yml se necessário

2. **Erro de conexão com o banco**
   - Verifique se o container está rodando: `docker ps`
   - Logs do container: `docker logs postgres`
   - Verifique as credenciais no application.properties

3. **Erro ao executar os testes**
   - Certifique-se que o banco está rodando
   - Limpe o projeto: `./mvnw clean`
