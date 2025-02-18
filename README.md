# Spring Boot Kafka Example

A Spring Boot application demonstrating Apache Kafka integration with Avro serialization and Schema Registry.

## Prerequisites

- Java 17
- Maven
- Docker and Docker Compose

## Project Structure

This project consists of two Spring Boot applications:

1. Producer Application (Port: 8086)
   - Produces Kafka messages
   - Contains the test endpoint for message generation
   - Located in `/producer` module

2. Consumer Application (Port: 8080)
   - Consumes Kafka messages
   - Located in `/projector` module

## Setup

1. Start Kafka infrastructure using Docker Compose:
```bash
docker compose up -d
```

This will start:
- Zookeeper
- Kafka Broker
- Schema Registry

2. Build the project:
```bash
mvn clean install
```

3. Run both applications with desired profile:
```bash
# Start Producer (Port 8086)
cd producer
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Start Projector (Port 8080)
cd projector
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Configuration

The application uses Spring profiles for different environments:
- `dev` - Development environment
- `uat` - UAT environment

Each profile has its own configuration in:
- `application-dev.properties`
- `application-uat.properties`

## Test API Endpoint

The producer application provides a test endpoint to produce sample messages to Kafka:

```bash
POST http://localhost:8086/send
```

This endpoint will generate and send a test OrderEvent message with the following structure:
- id: Random UUID
- firstName: "firstName"
- lastName: "lastName"
- orderedTime: Current timestamp
- status: "status"

Example curl command:
```bash
curl -X POST http://localhost:8086/send
```

## Testing Tips

To reprocess messages from the beginning of a topic, you can modify the consumer group ID in the application properties. This will treat the consumer as a new consumer group and start reading from the earliest offset:

```yaml
spring:
  kafka:
    consumer:
      group-id: new-consumer-group-name  # Change this value to create a new consumer group
```

## Avro Schemas

The Avro schemas are located in `src/main/resources/avro/`. After building the project, the corresponding Java classes will be generated in the target directory.

## Technologies

- Spring Boot
- Apache Kafka
- Apache Avro
- Confluent Schema Registry
- Docker
- Maven
- Lombok
