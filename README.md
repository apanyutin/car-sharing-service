# 🚗 Car Sharing Service API

Welcome to the Car Sharing Service project! This project aims to automate and enhance the management of car rentals, user management, and payment processing in a car-sharing service.

## Table of Contents
- [Introduction](#-introduction)
- [Features](#-features)
- [Technologies Used](#%EF%B8%8F-technologies-used)
- [API Endpoints](#-api-endpoints)
- [Setup](#-setup)
- [Challenges and Solutions](#-challenges-and-solutions)
- [Postman Collection](#-postman-collection)
- [Conclusion](#-conclusion)

## 🚀 Introduction

The Car Sharing Service automates car rentals, making it easier for customers to browse, rent, and pay for cars. The service also allows managers to oversee inventory, rentals, and payments. It is built using Spring Boot and integrates with Stripe for payments and Telegram for notifications.

## 🌟 Features

### Customer Actions:
- **Register and Login**: New users can register, login, and start renting cars.
- **Browse Cars**: Users can view available cars and their details.
- **Rent a Car**: Users can rent cars, initiating rental transactions.
- **View Rentals**: Users can check active and past rentals.
- **Return Cars**: Users return rented cars, updating rental status.
- **Payments**: Secure payment processing via Stripe.

### Manager Actions:
- **Manage Inventory**: Managers can add, update, or delete cars.
- **View Rentals and Payments**: Access to all rentals and payment history.
- **Telegram Notifications**: Managers receive updates about new rentals, overdue rentals, and successful payments.

## 🛠️ Technologies Used
- **Spring Boot**: Core framework for building REST APIs.
- **Spring MVC**: Facilitates building web applications and RESTful APIs using the MVC pattern.
- **Spring Security**: Secures the application with JWT-based authentication.
- **Spring Data JPA**: Handles interactions with the relational database using Hibernate.
- **MySQL**: Relational database for storing books, users, and order information.
- **Maven**: Builds and manages project dependencies.
- **JUnit 5**: A testing framework for unit and integration tests.
- **Lombok**: Reduces boilerplate code with annotations for getters, setters, constructors, etc.
- **MapStruct**: Simplifies object mapping between DTOs and entities.
- **Liquibase**: Manages database schema changes with version control.
- **Stripe API**: Manages payment processing for rentals.
- **Telegram API**: Sends notifications via a Telegram bot.
- **Swagger/OpenAPI**: Provides an interactive UI for exploring the API documentation.
- **Docker**: Containerization to run the application in isolated environments.

## 📋 API Endpoints

| Method | Endpoint               | Description                                                |
|--------|------------------------|------------------------------------------------------------|
| POST   | `/api/auth/register`    | Register a new user                                        |
| POST   | `/api/auth/login`       | Login and receive JWT                                      |
| GET    | `/api/users/me`         | Get current user's profile                                 |
| PUT    | `/api/users/me`         | Update profile info                                        |
| PUT    | `/api/users/{id}/role`  | Update user role (manager only)                            |
| POST   | `/api/cars`             | Add a new car (manager only)                               |
| GET    | `/api/cars`             | Get a list of cars                                         |
| GET    | `/api/cars/{id}`        | Get detailed info of a specific car                        |
| PUT    | `/api/cars/{id}`        | Update car details (manager only)                          |
| DELETE | `/api/cars/{id}`        | Delete a car (manager only)                                |
| POST   | `/api/rentals`          | Create a new rental                                        |
| GET    | `/api/rentals`          | Get rentals based on user ID and rental status             |
| POST   | `/api/rentals/{id}/return`| Return a rented car                                        |
| POST   | `/api/payments`         | Create a payment session through Stripe                    |
| GET    | `/api/payments`         | Get payment history based on user ID                       |

## 💻 Setup

### Prerequisites
- **Java 17+**
- **Maven**
- **MySQL**
- **Docker** (for containerization)

### Local Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/car-sharing-service.git
   cd car-sharing-service
   ```

2. **Set up MySQL**:
   Create a database and configure `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/car_sharing
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. **Build and run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the API**:
   Go to `http://localhost:8080/api/swagger-ui/index.html`.

### Running with Docker

1. **Build Docker image**:
   ```bash
   docker build -t car-sharing-service .
   ```

2. **Start the service**:
   ```bash
   docker-compose up
   ```

## 🔍 Challenges and Solutions

- **Efficient Inventory Management**: Tracking real-time car availability and handling overbookings.
- **Stripe Integration**: Implemented Stripe to handle secure payments and notifications for failed transactions.
- **Telegram Notifications**: Built an integrated Telegram notification system for admins using the Telegram API.

## 🧾 Postman Collection

You can test the API endpoints using Postman. A collection of the requests is provided for your convenience. [Download the Postman collection here](https://github.com/apanyutin/car-sharing-service/blob/master/сar-sharing-service.postman_collection.json) and import it into your Postman client.

## 📎 Conclusion

The Car Sharing Service API provides a solid foundation for managing car rentals, payments, and user interactions in a car-sharing business. With its scalable architecture and integrations, it's designed to meet the demands of modern car-sharing platforms. This backend API was developed as a commissioned project for the online auto parts store https://avp.com.ua.
