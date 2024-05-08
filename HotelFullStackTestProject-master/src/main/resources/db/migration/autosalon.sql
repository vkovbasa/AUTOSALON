
CREATE TABLE Sales (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    orderID BIGINT,
    dateSale DATE,
    salePrice DECIMAL(10, 2),
    FOREIGN KEY (orderID) REFERENCES Orders(id)
);

CREATE TABLE Positions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    salary DOUBLE,
    responsibilities TEXT
);

CREATE TABLE Orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    carID BIGINT,
    clientID BIGINT,
    employeeID BIGINT,
    dateOrder DATE,
    orderStatus VARCHAR(255),
    FOREIGN KEY (carID) REFERENCES Cars(id),
    FOREIGN KEY (clientID) REFERENCES Clients(id),
    FOREIGN KEY (employeeID) REFERENCES Employees(id)
);

CREATE TABLE Employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(255),
    dateOfBirth DATE,
    phone VARCHAR(20),
    positionID BIGINT,
    FOREIGN KEY (positionID) REFERENCES Positions(id)
);

CREATE TABLE Clients (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(255),
    address TEXT,
    phone VARCHAR(20),
    email VARCHAR(255)
);

CREATE TABLE CarCategories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    categoryName VARCHAR(255),
    categoryDescription TEXT
);

CREATE TABLE Cars (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(255),
    model VARCHAR(255),
    dateOfIssue DATE,
    price DECIMAL(10, 2),
    categoryID BIGINT,
    additionalInfo TEXT,
    FOREIGN KEY (categoryID) REFERENCES CarCategories(id)
);