# Documentação do Projeto - Sistema de Entregas com Drones

## Estrutura do Projeto

O projeto está organizado nas seguintes camadas:

- **Controller**: Camada de apresentação/API REST
- **Service**: Camada de regras de negócio
- **Repository**: Camada de acesso a dados
- **Entity**: Camada de modelos/entidades

### Entidades Principais
- Drone
- Entrega 
- Pedido

## Endpoints da API

### Drone Controller
```http
GET /drones - Lista todos os drones
GET /drones/{id} - Busca drone por ID
POST /drones - Cria novo drone
PUT /drones/{id} - Atualiza drone existente
DELETE /drones/{id} - Remove drone
```

### Entrega Controller  
```http
GET /entregas - Lista todas as entregas
GET /entregas/{id} - Busca entrega por ID
POST /entregas - Cria nova entrega
PUT /entregas/{id} - Atualiza entrega existente 
DELETE /entregas/{id} - Remove entrega
```

### Pedido Controller
```http
GET /pedidos - Lista todos os pedidos
GET /pedidos/{id} - Busca pedido por ID
POST /pedidos - Cria novo pedido
PUT /pedidos/{id} - Atualiza pedido existente
DELETE /pedidos/{id} - Remove pedido
```

## Collection Postman

### Exemplo de JSON para requests:

```json
// Criar/Atualizar Drone
{
  "modelo": "Drone X1000",
  "capacidadeCarga": 10.5,
  "autonomiaBateria": 120,
  "status": "DISPONIVEL"
}

// Criar/Atualizar Entrega
{
  "pedidoId": 1,
  "droneId": 1,
  "status": "PENDENTE",
  "dataEntrega": "2025-08-31T10:00:00"
}

// Criar/Atualizar Pedido
{
  "cliente": "Nome do Cliente",
  "endereco": "Rua Example, 123",
  "items": [
    {
      "produto": "Item 1",
      "quantidade": 2
    }
  ],
  "status": "NOVO"
}
```

## Instruções de Uso

1. Clone o repositório
2. Configure o banco de dados no application.properties
3. Execute o projeto usando Maven: `mvn spring-boot:run`
4. A API estará disponível em `http://localhost:8080`

### Tratamento de Erros

O sistema implementa tratamento de exceções para:
- Recursos não encontrados (404)
- Requisições inválidas (400)
- Erros internos do servidor (500)

### Regras de Negócio Principais

- Validação de capacidade de carga dos drones
- Verificação de disponibilidade de drones
- Controle de status das entregas
- Validação de pedidos

## Histórico do Projeto

1. Implementação inicial das entidades básicas
2. Criação das camadas repository, service e controller
3. Implementação do CRUD básico
4. Adição de tratamento de erros
5. Desenvolvimento dos endpoints REST
6. Testes unitários
7. Documentação da API

## Testes

O projeto inclui testes unitários para as camadas:
- DroneService
- EntregaService
- PedidoService

Execute os testes com: `mvn test`

## Dependências Principais

- Spring Boot
- Spring Data JPA
- Spring Web
- H2 Database (desenvolvimento)
- Maven
