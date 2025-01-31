# Online Food Ordering Backend

## Overview
The Online Food Ordering Backend is a Java Spring-based application that enables users to create restaurants, add food items with customizable ingredients, and place orders. The system includes role-based JWT authentication to manage user access securely.

## Features
- **Role-Based JWT Authentication**: Secure authentication and authorization using JSON Web Tokens (JWT).
- **Restaurant Management**: Users can create and manage their restaurants.
- **Food Management**: Add food items with various customizable ingredients.
- **Cart API (In Progress)**: Users can add items to their cart before placing an order.
- **Order API (In Progress)**: Handles order placement and processing.

## Tech Stack
- **Backend**: Java, Spring Boot
- **Security**: Spring Security with JWT
- **Database**: MySQL
- **Build Tool**: Maven

## Setup Instructions
### Prerequisites
- Java 17+
- MySQL Database
- Maven
- Postman (for API testing)

### Steps to Run
1. Clone the repository:
   ```sh
   git clone https://github.com/Devansh-ds/online-food-ordering-backend.git
   cd online-food-ordering-backend
   ```
2. Configure application properties:
   - Update `src/main/resources/application.properties` with your MySQL credentials.
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints
### Authentication
- `POST /auth/register` - Register a new user
- `POST /auth/login` - Authenticate user and retrieve JWT token

### Restaurant Management
- `POST /restaurants` - Create a restaurant
- `GET /restaurants` - Get all restaurants

### Food Management
- `POST /foods` - Add food item with customizable ingredients
- `GET /foods` - Get all available food items

### Upcoming APIs (In Progress)
- `Cart API`
- `Order API`

## Contribution
Feel free to contribute by opening a pull request or reporting issues.

## License
MIT License

