# Campus Bookstore Inventory Management System

A full-stack Spring Boot application for managing campus bookstore operations, built as part of CST-339 at Grand Canyon University.

## Overview

This enterprise-class N-Layer web application enables students and staff to browse products, manage shopping carts, and process orders, while providing administrators with comprehensive inventory management capabilities.

## Features

- User registration and secure authentication
- Role-based access control (Customer/Admin)
- Product catalog with search and filtering
- Shopping cart and checkout functionality
- Complete CRUD operations for product management
- Inventory tracking with low-stock alerts
- Admin dashboard with analytics
- Responsive design for mobile and desktop
- Secured REST API endpoints

## Technology Stack

- **Backend:** Spring Boot 3.x, Spring Security, Spring Data JDBC
- **Frontend:** Thymeleaf, Bootstrap 5
- **Database:** MySQL / PostgreSQL
- **Build Tool:** Maven
- **Server:** Embedded Tomcat

## Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.0+ or PostgreSQL 13+

## Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/-------/campus-bookstore.git
cd campus-bookstore
```

### 2. Configure the database
Create a database and update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:----/campus_bookstore
spring.datasource.username=-------
spring.datasource.password=-------
```

### 3. Build and run
```bash
mvn clean install
mvn spring-boot:run
```

The application will be available at `http://localhost:----`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/gcu/bookstore/
│   │       ├── controller/      # MVC Controllers
│   │       ├── model/           # Domain Models
│   │       ├── service/         # Business Logic
│   │       ├── repository/      # Data Access
│   │       └── security/        # Security Config
│   └── resources/
│       ├── templates/           # Thymeleaf Views
│       ├── static/              # CSS, JS, Images
│       └── application.properties
└── test/                        # Tests
```

## API Endpoints

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create product (Admin)
- `PUT /api/products/{id}` - Update product (Admin)
- `DELETE /api/products/{id}` - Delete product (Admin)

All API endpoints require Basic HTTP Authentication.

## Team

- **Eric Engstrom** - Backend Development
- **Aaron Chaussignand** - Frontend Development
- **Brock Penrod** - Security & API Development
- **Alexis Williams** - QA & Documentation

## License

Academic project for CST-339 at Grand Canyon University.
