-- Eliminación de tablas si existen
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Packages;
DROP TABLE IF EXISTS Offices;
DROP TABLE IF EXISTS Warehouses;
DROP TABLE IF EXISTS Vehicles;
DROP TABLE IF EXISTS Routes;
DROP TABLE IF EXISTS Shipments;
DROP TABLE IF EXISTS Package_Tracking;
DROP TABLE IF EXISTS Delivery_Attempts;
DROP TABLE IF EXISTS Rates;
DROP TABLE IF EXISTS Carreras;

-- Creación de la tabla de Usuarios
CREATE TABLE Users (
    user_id String PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15)
);

-- Creación de la tabla de Paquetes con las 3 dimensiones
CREATE TABLE Packages (
    package_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id VARCHAR(10),
    receiver_id VARCHAR(10),
    citySender VARCHAR(100),
    addressSender TEXT,
    cityRec VARCHAR(100),
    addressRec TEXT,
    weight DECIMAL(10, 2),
    height DECIMAL(10, 2),
    length DECIMAL(10, 2),
    depth DECIMAL(10, 2),
    status VARCHAR(50),
    price INT,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);

-- Creación de la tabla de Oficinas
CREATE TABLE Offices (
    office_id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100),
    address TEXT
);

-- Creación de la tabla de Almacenes
CREATE TABLE Warehouses (
    warehouse_id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100),
    address TEXT
);

-- Creación de la tabla de Vehículos
CREATE TABLE Vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    capacity DECIMAL(10, 2),
    current_location VARCHAR(100)
);

-- Creación de la tabla de Rutas
CREATE TABLE Routes (
    route_id INT AUTO_INCREMENT PRIMARY KEY,
    origin VARCHAR(100),
    destination VARCHAR(100),
    waypoints TEXT
);

-- Creación de la tabla de Envios
CREATE TABLE Shipments (
    shipment_id INT AUTO_INCREMENT PRIMARY KEY,
    package_id INT,
    route_id INT,
    vehicle_id INT,
    pickup_date DATETIME,
    delivery_date DATETIME,
    FOREIGN KEY (package_id) REFERENCES Packages(package_id),
    FOREIGN KEY (route_id) REFERENCES Routes(route_id),
    FOREIGN KEY (vehicle_id) REFERENCES Vehicles(vehicle_id)
);

-- Creación de la tabla de Seguimiento de Paquetes
CREATE TABLE Package_Tracking (
    tracking_id INT AUTO_INCREMENT PRIMARY KEY,
    package_id INT,
    location VARCHAR(100),
    status VARCHAR(50),
    timestamp DATETIME,
    FOREIGN KEY (package_id) REFERENCES Packages(package_id)
);

-- Creación de la tabla de Intentos de Entrega
CREATE TABLE Delivery_Attempts (
    attempt_id INT AUTO_INCREMENT PRIMARY KEY,
    package_id INT,
    attempt_number INT,
    status VARCHAR(50),
    timestamp DATETIME,
    FOREIGN KEY (package_id) REFERENCES Packages(package_id)
);