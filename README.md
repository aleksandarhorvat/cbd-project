# CBD Project - Library Management System

## Overview
This is a microservices-based Library Management System that provides a scalable and maintainable solution for managing books, users, and library operations. The project is built using Jakarta EE, Spring Data JPA, and follows microservices architecture principles.

## Project Structure
The project consists of the following microservices:

- **API Gateway**: Entry point for all client requests, handles routing and load balancing
- **Config Server**: Centralized configuration management for all microservices
- **Eureka Server**: Service discovery and registration server
- **Book Service**: Manages book-related operations and inventory
- **User Service**: Handles user management and authentication
- **Library Service**: Manages library operations like borrowing and returning books

## Technology Stack
- Java 21
- Jakarta EE
- Spring Data JPA
- Spring Cloud (Eureka, Config Server)
- Lombok
- RESTful APIs

## Prerequisites
- JDK 21
- Maven
- Docker (optional)

## Getting Started

### Running the Application

1. Start the Config Server:
```bash
cd config-server mvn spring-boot:run
``` 

2. Start the Eureka Server:
```bash
cd eureka-server mvn spring-boot:run
``` 

3. Start the core services:
```bash
cd book-service mvn spring-boot:run
cd user-service mvn spring-boot:run
cd library-service mvn spring-boot:run
``` 

4. Start the API Gateway:
```bash
cd api-gateway mvn spring-boot:run
``` 

The application will be accessible at `http://localhost:8765` (API Gateway endpoint)

## Service Endpoints

### API Gateway
- Base URL: `http://localhost:8765`

### Eureka Server
- Dashboard: `http://localhost:8761`

### Individual Services
- User Service: `http://localhost:8000`
- Book Service: `http://localhost:8001`
- Library Service: `http://localhost:8002`

## Features
- Centralized configuration management
- Service discovery and registration
- Load balancing
- Book management
- User management
- Library operations management

## Database Model

<img width="720" height="407" alt="image" src="https://github.com/user-attachments/assets/a0e30377-5fef-4c97-b2d7-602e5fb771d2" />


## Database Setup

To set up the initial data in the database, add the following rows to the `role` table:

- **Row 1**
    - `id`: 1
    - `name`: ADMIN

- **Row 2**
    - `id`: 2
    - `name`: USER
