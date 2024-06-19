-- Inserción de datos en la tabla de Usuarios
INSERT INTO Users (user_id, name, email, phone) VALUES
('U001', 'Alice Johnson', 'alice@example.com', '555-0101'),
('U002', 'Bob Smith', 'bob@example.com', '555-0102'),
('U003', 'Carol Williams', 'carol@example.com', '555-0103');

-- Inserción de datos en la tabla de Paquetes
INSERT INTO Packages (sender_id, receiver_id, citySender, addressSender, cityRec, addressRec, weight, height, length, depth, status, price) VALUES
('U001', 'U002', 'Gijon', '123 Sender St', 'Madrid', '456 Receiver Rd', 2.5, 10.0, 20.0, 15.0, 'Pending', 100),
('U002', 'U003', 'Madrid', '789 Sender Ave', 'Valencia', '101 Receiver Blvd', 3.0, 15.0, 25.0, 10.0, 'Shipped', 150);

-- Inserción de datos en la tabla de Oficinas
INSERT INTO Offices (city, address) VALUES
('Gijon', '321 Office St'),
('Madrid', '654 Office Rd');

-- Inserción de datos en la tabla de Almacenes
INSERT INTO Warehouses (city, address) VALUES
('Valencia', '987 Warehouse St'),
('Sevilla', '321 Warehouse Rd');

-- Inserción de datos en la tabla de Vehículos
INSERT INTO Vehicles (capacity, current_location) VALUES
(1000.0, 'Gijon'),
(800.0, 'Madrid');

-- Inserción de datos en la tabla de Rutas
INSERT INTO Routes (origin, destination, waypoints) VALUES
('Gijon', 'Madrid', 'Oviedo,Leon,Segovia'),
('Madrid', 'Valencia', 'Cuenca,Requena');

-- Inserción de datos en la tabla de Envios
INSERT INTO Shipments (package_id, route_id, vehicle_id, pickup_date, delivery_date) VALUES
(1, 1, 1, '2024-06-01 08:00:00', '2024-06-02 18:00:00'),
(2, 2, 2, '2024-06-03 09:00:00', '2024-06-04 17:00:00');

-- Inserción de datos en la tabla de Seguimiento de Paquetes
INSERT INTO Package_Tracking (package_id, location, status, timestamp) VALUES
(1, 'Oviedo', 'In Transit', '2024-06-01 12:00:00'),
(1, 'Leon', 'In Transit', '2024-06-01 16:00:00'),
(1, 'Madrid', 'Delivered', '2024-06-02 18:00:00'),
(2, 'Cuenca', 'In Transit', '2024-06-03 13:00:00'),
(2, 'Valencia', 'Delivered', '2024-06-04 17:00:00');

-- Inserción de datos en la tabla de Intentos de Entrega
INSERT INTO Delivery_Attempts (package_id, attempt_number, status, timestamp) VALUES
(1, 1, 'Success', '2024-06-02 18:00:00'),
(2, 1, 'Success', '2024-06-04 17:00:00');

