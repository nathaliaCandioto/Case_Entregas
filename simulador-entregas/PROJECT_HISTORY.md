# Histórico do Projeto - Sistema de Entregas com Drones

## Prompts e Desenvolvimento

### 1. Substituição de Tipo em Drone
**Prompt:** Substituir as referências de pesoSuportado na entidade Drone de Integer para Double
- Análise mostrou que o atributo já estava como Double
- Verificação em outros arquivos para garantir consistência

### 2. Correção do Método ListDrone
**Prompt:** Corrigir o método ListDrone
- Identificado erro de sintaxe e lógica
- Implementada correção para busca com filtros
- Adicionado suporte para busca sem filtros
- Melhorada validação de parâmetros

### 3. Ordenação de Pedidos
**Prompt:** Ordenar pedidosPendentes pelo statusPedido em ordem crescente
- Implementada ordenação usando comparator
- Ajustado para usar ordinal do enum

### 4. Ordenação por Prioridade
**Prompt:** Refazer ordenação pelo atributo prioridadePedido
- Implementada ordenação por prioridade do pedido

### 5. Testes Unitários
**Prompt:** Fazer toda cobertura de teste unitário necessária
- Implementados testes para DroneService
- Implementados testes para EntregaService
- Implementados testes para PedidoService
- Corrigidos problemas nos testes de processamento de entregas

### 6. Configuração do Ambiente
**Prompt:** Atualizar README com instruções de execução
- Criado docker-compose.yml para PostgreSQL
- Atualizado README com instruções detalhadas
- Configurado application.properties
- Documentados endpoints e troubleshooting

## Decisões Técnicas

### Arquitetura
- Uso de Spring Boot
- Arquitetura em camadas (Controller, Service, Repository)
- Banco de dados PostgreSQL em container Docker

### Padrões de Código
- Uso de enums para status e prioridades
- Validações em camada de serviço
- Testes unitários com Mockito

### Configurações
- PostgreSQL 15 (Alpine)
- Volumes persistentes para dados
- Healthcheck no container
- Hibernate com auto-update para schema

## Melhorias Implementadas

### Código
- Validações de parâmetros
- Ordenação de pedidos por prioridade
- Correção de bugs em ListDrone
- Cobertura de testes

### DevOps
- Docker Compose para banco de dados
- Documentação detalhada
- Instruções de troubleshooting

## Estrutura do Projeto

```
case-entregas/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/caseDTI/entregas/case_entregas/
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   └── test/
│       └── java/
├── docker-compose.yml
└── README.md
```

## Endpoints

### Drones
- POST /drone - Cadastrar drone
- GET /drone - Listar drones
- DELETE /drone/{id} - Remover drone

### Pedidos
- POST /pedido - Criar pedido
- GET /pedido - Listar pedidos
- DELETE /pedido/{id} - Remover pedido

### Entregas
- POST /entrega - Criar entrega
- GET /entrega - Listar entregas
- DELETE /entrega/{id} - Remover entrega
- GET /entrega/pendentes - Listar pendentes
- POST /entrega/processar - Processar entregas

## Configurações

### Banco de Dados
```yaml
postgres:
  database: entregas_db
  user: postgres
  password: postgres
  port: 5432
```

### Application Properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/entregas_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

## Status do Projeto

### Completo
- ✅ Estrutura básica
- ✅ CRUD de entidades
- ✅ Testes unitários
- ✅ Documentação
- ✅ Docker Compose

### Pendente
- Testes de integração
- CI/CD
- Documentação da API (Swagger)
- Logs estruturados
- Métricas e monitoramento

## Comandos Úteis

### Desenvolvimento
```bash
./mvnw spring-boot:run
./mvnw test
```

### Docker
```bash
docker-compose up -d
docker-compose down
```
