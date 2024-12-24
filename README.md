# Spring Boot Authentication and Product Management

This project implements secure user authentication and product management with Spring Boot 3.4.1. It uses H2 as an in-memory database, JWT for authentication, and WebSockets for real-time pricing updates.

---

## Features

1. **User Authentication**
   - **Sign Up**: Register new users.
   - **Sign In**: Authenticate users and generate JWT access and refresh tokens.
   - **Refresh Token**: Renew access tokens using refresh tokens.

2. **Product Management**
   - Create, view, and list products.

3. **Real-Time Pricing**
   - Simulate real-time product price updates using WebSocket.

4. **Purchase Simulation**
   - Authenticated users can simulate purchasing a product.

---

## Technologies Used

- **Spring Boot 3.4.1**
- **Spring Security with JWT**
- **Spring WebSocket**
- **H2 Database**
- **Java 17**

---

## Installation and Setup

### Prerequisites

- Java 17 or later
- Maven

### Steps to Run Locally

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-name>
