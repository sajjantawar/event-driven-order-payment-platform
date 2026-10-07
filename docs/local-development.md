# Local Development

## Start infrastructure

```bash
docker compose up -d
```

Infrastructure:
- Orders PostgreSQL: localhost:5432
- Inventory PostgreSQL: localhost:5433
- Payments PostgreSQL: localhost:5434
- Kafka: localhost:9092

## Start services

Run each Spring Boot service from its own directory:

```bash
mvn spring-boot:run
```

Ports:
- Order Service: 8081
- Inventory Service: 8082
- Payment Service: 8083

The Angular application and API Gateway will be added in the next phase.
