# Finance Tracker Ultimate

Finance Tracker Ultimate is a backend application designed to help users manage their personal finances effectively. This system enables the tracking of financial transactions, categorization of expenses, and generation of insightful reports. Built with **Java** and **Spring Framework**.

---

## Features

1. **User Management**:
   - Allows creation, retrieval, updating, and deletion of user profiles.
   - Tracks user-specific information such as name, email, account details, and balance.

2. **Category Management**:
   - Enables users to define and manage custom financial categories.
   - Each category includes a name, description, and an optional icon.

3. **Transaction Management**:
   - Records financial transactions with details like amount, type (e.g., income, expense), and associated categories.
   - Ensures data validation through business rules (e.g., categories are only allowed for expense-type transactions).

4. **Report Generation**:
   - Generates financial summaries and insights to help users better understand their spending and saving patterns.

---

## Data Model

### User Entity
Represents a user in the system with the following attributes:
- `id` (int): Unique identifier.
- `firstName` (String): User's first name.
- `lastName` (String): User's last name.
- `email` (String): Contact email.
- `accountName` (String): Unique account name.
- `balance` (double): Current balance in the user's account.

### Category Entity
Defines financial categories with attributes:
- `id` (int): Unique identifier.
- `name` (String): Name of the category.
- `description` (String): Description of the category.
- `icon` (String): Icon representing the category.

### Transaction Entity
Tracks individual financial transactions:
- `id` (int): Unique identifier.
- `amount` (double): Transaction amount.
- `type` (enum): Type of transaction (e.g., income, expense).
- `category` (CategoryEntity): Associated category (if applicable).
- `createdOn` (LocalDateTime): Timestamp of transaction creation.
- `user` (UserEntity): User who made the transaction.
- Validates that categories are only assigned to transactions of type **expense**.

---

## API Endpoints

### Users
- `POST /api/users`: Create a new user.
- `GET /api/users/{id}`: Retrieve user details by ID.
- `GET /api/users`: Retrieve all users.
- `PUT /api/users/{id}`: Update user details by ID.
- `DELETE /api/users/{id}`: Delete a user by ID.

### Categories
- `POST /api/categories`: Create a new category.
- `GET /api/categories/{id}`: Retrieve category details by ID.
- `GET /api/categories`: Retrieve all categories.
- `PUT /api/categories/{id}`: Update category details by ID.
- `DELETE /api/categories/{id}`: Delete a category by ID.

### Transactions
- `POST /api/transactions`: Record a new transaction.
- `GET /api/transactions/{id}`: Retrieve transaction details by ID.
- `GET /api/transactions`: Retrieve all transactions.
- `PUT /api/transactions/{id}`: Update transaction details by ID.
- `DELETE /api/transactions/{id}`: Delete a transaction by ID.

### Reports
- `GET /api/reports/{id}`: Generate a financial report for a specific user.

---

## Technologies Used
- **Java 17**: Core language.
- **Spring Boot**: Framework for creating the application.
- **Hibernate/JPA**: For ORM and database interaction.
- **Lombok**: To simplify entity and DTO creation.

---

## Getting Started

1. Clone the repository:  
   ```bash
   git clone https://github.com/username/financetracker-ultimate.git
   ```
2. Navigate to the project directory.
3. Configure the database connection in `application.properties`.
4. Run the application:  
   ```bash
   ./mvnw spring-boot:run
   ```
5. Access the API endpoints using your favorite API client or a front-end application.

---

## Future Enhancements
- Add authentication and authorization using Spring Security.
- Introduce advanced reporting features (e.g., charts, export options).
- Implement notifications for financial thresholds or reminders.

---

