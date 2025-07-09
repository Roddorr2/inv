```markdown
# Tai Loy Inventory Management System Prototype

## 📖 Table of Contents

- [Introduction](#introduction)
- [🎯 Key Features](#key-features)
- [🛠️ Tech Stack](#tech-stack)
- [⚙️ Prerequisites](#️prerequisites)
- [🚀 Getting Started](#-getting-started)
  - [Backend Setup (Java Spring Boot)](#backend-setup-java-spring-boot)
  - [Frontend Setup (Angular)](#frontend-setup-angular)
  - [Database Setup](#database-setup)
- [🔧 Usage](#-usage)
- [🤝 Contributing](#-contributing)

## Introduction

Welcome to the Tai Loy Inventory Management System Prototype! This project is a web-based system designed to modernize and streamline inventory management for Tai Loy, a Peruvian school supplies store. The primary goal is to provide an efficient, optimized, and user-friendly alternative to Tai Loy's current on-premise software, which can be intricate, outdated, and difficult to learn.

This prototype emulates key inventory management functionalities, focusing on clear administration and comprehensive inventory tracking.

## 🎯 Key Features

This system offers a range of features to manage Tai Loy's inventory and administrative tasks effectively:

*   **User Management:** Add and manage system users.
*   **Role Management:** Define and assign roles to users, controlling access and permissions.
*   **Product Management:** Add, update, and categorize products.
    *   **Categories & Sub-categories:** Organize products into a hierarchical structure.
*   **Supplier Management:** Maintain a database of suppliers.
*   **Branch Management:** Manage different Tai Loy store branches.
*   **Purchase Order Management:**
    *   Create new purchase orders.
    *   Review existing purchase orders.
    *   Accept, reject, or cancel purchase orders.
*   **Dispatch Management:**
    *   Create new dispatches.
    *   Review existing dispatches.
    *   Accept, reject, or cancel dispatches.
*   **Warehouse Tracking:** Accepted or rejected purchase orders and dispatches are logged in the warehouse table.
*   **Audit Functionality:** Records every administrative action (e.g., user creation, product updates, supplier additions) for accountability and tracking.
*   **Inventory Movement View:** Provides a real-time visualization of inventory changes, including who made the changes and the quantities involved.

## 🛠️ Tech Stack

This project is built using a microservices architecture:

*   **Backend:** Java (JDK 21) with Spring Framework & Spring Boot
    *   **Build Tool:** Apache Maven
*   **Frontend:** Angular (Version 19)
    *   **Build Tool:** Angular CLI
*   **Database:** Microsoft SQL Server

## ⚙️ Prerequisites

Before you begin, ensure you have the following installed on your system:

*   **Java Development Kit (JDK):** Version 21 or higher.
*   **Apache Maven:** For building and running the backend.
*   **Node.js and npm:** For managing Angular dependencies and running the frontend. (Angular 19 typically requires Node.js versions like 18.x or 20.x. Check Angular's official documentation for specific version compatibility).
*   **Angular CLI:** `npm install -g @angular/cli@19` (or match the version in your frontend project's `package.json`).
*   **Microsoft SQL Server:** An accessible instance of SQL Server.

## 🚀 Getting Started

This project consists of two separate repositories: one for the backend and one for the frontend. You will need to clone and set up both.

### Backend Setup (Java Spring Boot)

1.  **Clone the backend repository:**
    ```bash
    git clone <your-backend-repository-url>
    cd <backend-repository-directory>
    ```
2.  **Configure Database Connection:**
    *   Locate the database configuration file (usually `src/main/resources/application.properties` or `application.yml`).
    *   Update the database URL, username, and password to connect to your SQL Server instance.
    ```properties
    # Example for application.properties
    spring.datasource.url=jdbc:sqlserver://<your_server_address>:<port>;databaseName=<your_database_name>;encrypt=true;trustServerCertificate=true; # Adjust encrypt/trustServerCertificate based on your SQL Server config
    spring.datasource.username=<your_username>
    spring.datasource.password=<your_password>
    spring.jpa.hibernate.ddl-auto=update # Or 'validate' if you manage schema manually after initial setup
    ```
3.  **Build the project:**
    ```bash
    mvn clean install
    ```
4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The backend service should now be running. Check the console output for the port number (typically 8080).

### Frontend Setup (Angular)

1.  **Clone the frontend repository:**
    ```bash
    git clone <your-frontend-repository-url>
    cd <frontend-repository-directory>
    ```
2.  **Install dependencies:**
    ```bash
    npm install
    ```
3.  **Configure API Endpoint:**
    *   The frontend will need to know where the backend API is running. This is typically configured in an environment file (e.g., `src/environments/environment.ts`).
    *   Update the API base URL if necessary (e.g., `apiUrl: 'http://localhost:8080/api'`).
4.  **Run the application:**
    ```bash
    ng serve
    ```
    Navigate to `http://localhost:4200/` (or the port specified in the Angular CLI output). The application should be running in your browser.

### Database Setup

1.  **Create a new database** in your SQL Server instance for this application.
2.  **Run the `data.sql` script:**
    *   This script contains initial insertions required for the application to function correctly.
    *   Use a SQL Server management tool (like SQL Server Management Studio - SSMS, Azure Data Studio, or `sqlcmd`) to connect to your database and execute the contents of `data.sql`.
    *   **Note:** Ensure the backend application's `spring.jpa.hibernate.ddl-auto` property is set appropriately. For initial setup, `create` or `update` can help generate tables based on your entities. After the initial setup and running `data.sql`, you might want to change it to `validate` or manage schema migrations more explicitly if needed.

## 🔧 Usage

Once both the backend and frontend are running and the database is set up:

1.  Open your web browser and navigate to the frontend URL (typically `http://localhost:4200`).
2.  You should see the login page or main dashboard of the Tai Loy Inventory Management System.
3.  Use the system to:
    *   Manage users, roles, products, categories, suppliers, and branches.
    *   Create and process purchase orders and dispatches.
    *   View audit logs and inventory movements.

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/YourFeature`).
3.  Make your changes.
4.  Commit your changes (`git commit -m 'Add some feature'`).
5.  Push to the branch (`git push origin feature/YourFeature`).
6.  Open a Pull Request.
