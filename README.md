# Project-SDA

## Overview
Project-SDA is a Spring Boot-based web application for a **Travel Agency** management system. This application allows users to manage travel bookings, explore destinations, and handle customer information. The backend is built using Spring Boot, and the project integrates with a relational database for storing user and booking data.

## Technologies Used
- **Backend**: Spring Boot
- **Database**: SQL Server (via JDBC)
- **Build Tool**: Maven
- **API Framework**: JAX-RS (for RESTful services)

## Setup Instructions

### Prerequisites
Before running the application, ensure you have the following installed:
- **Java 17** (or higher)
- **Maven 3.x** (or higher)
- **SQL Server** (or another database of choice, adjust settings accordingly)

### Running the Application Locally
1. **Clone the repository**:
   ```bash
   git clone https://github.com/Mariammazen147/Project-SDA.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd Project-SDA
   ```

3. **Build the application** using Maven (this will download dependencies and compile the project):
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

   The application should now be running locally on [http://localhost:8080](http://localhost:8080).

### Database Setup
1. **Create a Database** on your SQL Server instance. You can name it `TravelAgency` or any other name you prefer.

2. **Update the `application.properties`** file (located in `src/main/resources/`) to include your database connection details:
   ```properties
   spring.datasource.url=jdbc:sqlserver://localhost;databaseName=TravelAgency
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

   Replace `yourUsername` and `yourPassword` with your actual database credentials.

3. **Create Tables and Sample Data**:
   You can import an initial schema and sample data from a file or manually create tables if needed. (Make sure to define necessary tables like `users`, `bookings`, etc.)

## Features
- **User Management**: Sign up, login, and user profile management.
- **Booking System**: Users can make travel bookings for available destinations.
- **Search & Filters**: Easily search and filter destinations based on criteria such as status and User Id.
- **Admin Dashboard**: Admins can manage user data, destinations, and bookings.

## How to Contribute
1. **Fork the repository** by clicking the "Fork" button on GitHub.
2. **Clone your fork** to your local machine:
   ```bash
   git clone https://github.com/yourUsername/Project-SDA.git
   ```
3. **Create a new branch** for your feature or bug fix:
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. **Make your changes** and test them locally.
5. **Commit your changes**:
   ```bash
   git commit -am 'Add a new feature or fix a bug'
   ```
6. **Push your changes** to your forked repository:
   ```bash
   git push origin feature/your-feature-name
   ```
7. **Create a pull request** (PR) on the original repository to merge your changes.


## Acknowledgments
- **Spring Boot** for providing a comprehensive, easy-to-use framework for Java-based applications.
- **SQL Server** for managing relational data.
- **JAX-RS** for creating RESTful APIs.

### Key Sections to Update:
- **Database Setup**: Make sure your `application.properties` file matches your local database configuration.
- **Database Tables**: If you have a specific schema for your database, include it in the documentation or mention how to import it.
