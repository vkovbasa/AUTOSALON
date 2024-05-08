-- Створення бази даних
CREATE DATABASE IF NOT EXISTS autosalon;

-- Використання новоствореної бази даних
USE autosalon;

-- Створення таблиці "Автомобілі"
CREATE TABLE IF NOT EXISTS Cars
(
    CarID          INT AUTO_INCREMENT PRIMARY KEY,
    Brand          VARCHAR(255),
    Model          VARCHAR(255),
    Year           INT,
    Price          DECIMAL(10, 2),
    Category       VARCHAR(100),
    AdditionalInfo TEXT
);

-- Створення таблиці "Клієнти"
CREATE TABLE IF NOT EXISTS Clients
(
    ClientID  INT AUTO_INCREMENT PRIMARY KEY,
    LastName  VARCHAR(255),
    FirstName VARCHAR(255),
    Address   VARCHAR(255),
    Phone     VARCHAR(15),
    Email     VARCHAR(255)
);

-- Створення таблиці "Продажі"
CREATE TABLE IF NOT EXISTS Sales
(
    SaleID    INT AUTO_INCREMENT PRIMARY KEY,
    CarID     INT,
    ClientID  INT,
    SaleDate  DATE,
    SalePrice DECIMAL(10, 2),
    FOREIGN KEY (CarID) REFERENCES Cars (CarID),
    FOREIGN KEY (ClientID) REFERENCES Clients (ClientID)
);

-- Створення таблиці "Співробітники"
CREATE TABLE IF NOT EXISTS Employees
(
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    LastName   VARCHAR(255),
    FirstName  VARCHAR(255),
    Position   VARCHAR(255),
    Salary     DECIMAL(10, 2)
);

-- Створення таблиці "Замовлення"
CREATE TABLE IF NOT EXISTS Orders
(
    OrderID     INT AUTO_INCREMENT PRIMARY KEY,
    CarID       INT,
    ClientID    INT,
    EmployeeID  INT,
    OrderDate   DATE,
    OrderStatus VARCHAR(50),
    FOREIGN KEY (CarID) REFERENCES Cars (CarID),
    FOREIGN KEY (ClientID) REFERENCES Clients (ClientID),
    FOREIGN KEY (EmployeeID) REFERENCES Employees (EmployeeID)
);

-- Створення таблиці "Категорії автомобілів"
CREATE TABLE IF NOT EXISTS CarCategories
(
    CategoryID          INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName        VARCHAR(100),
    CategoryDescription TEXT
);
