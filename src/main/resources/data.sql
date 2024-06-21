-- Inserción de usuarios
INSERT INTO "Users" ("user_id", "name", "email", "phone") VALUES
("u1", "Juan Perez", "juan@example.com", "555-1234"),
("u2", "Ana García", "ana@example.com", "555-5678"),
("u3", "Carlos López", "carlos@example.com", "555-9876"),
("u4", "Laura Martínez", "laura@example.com", "555-4321");

-- Inserción de ciudades
INSERT INTO "City" ("city") VALUES
("Gijón"),
("Valencia"),
("Madrid"),
("Sevilla"),
("Burgos Warehouse"),
("Zaragoza Warehouse"),
("Valladolid Warehouse"),
("Cuenca Warehouse"),
("Toledo Warehouse"),
("Cáceres Warehouse"),
("Mérida Warehouse"),
("Ciudad Real Warehouse");

-- Inserción de paquetes
INSERT INTO "Packages" ("sender_id", "receiver_id", "citySender", "addressSender", "cityRec", "addressRec", "weight", "height", "length", "depth", "status", "actual_location", "price") VALUES
("u1", "u2", "Gijón", "Calle A, 10", "Valencia", "Plaza B, 5", 2.5, 0.3, 0.4, 0.2, "IN TRANSIT", 2, 100),
("u3", "u4", "Madrid", "Avenida C, 20", "Sevilla", "Calle D, 15", 1.8, 0.25, 0.35, 0.15, "RECIEVED", 3, 80);

-- Inserción de oficinas
INSERT INTO "Offices" ("city_id", "address") VALUES
(1, "Calle A, 10"),
(2, "Plaza B, 5"),
(3, "Avenida C, 20"),
(4, "Calle D, 15");

-- Inserción de almacenes
INSERT INTO "Warehouses" ("city_id", "address") VALUES
(5, "Almacén Calle E, 5"),
(6, "Almacén  Plaza F, 2"),
(7, "Almacén Avenida G, 10"),
(8, "Almacén Calle H, 7");

-- Inserción de vehículos
INSERT INTO "Vehicles" ("capacity", "current_location") VALUES
(15.0, "Gijón"),
(10.5, "Valencia"),
(12.0, "Madrid"),
(11.5, "Sevilla");


-- Inserción de rutas
INSERT INTO "Routes" ("origin", "destination", "distance") VALUES
(1, 2, 600),    -- Distancia entre Gijón (ID: 1) y Valencia (ID: 2) en kilómetros
(1, 3, 400),    -- Distancia entre Gijón (ID: 1) y Madrid (ID: 3) en kilómetros
(1, 4, 900),    -- Distancia entre Gijón (ID: 1) y Sevilla (ID: 4) en kilómetros
(2, 1, 600),    -- Distancia entre Valencia (ID: 2) y Gijón (ID: 1) en kilómetros
(2, 3, 350),    -- Distancia entre Valencia (ID: 2) y Madrid (ID: 3) en kilómetros
(2, 4, 540),    -- Distancia entre Valencia (ID: 2) y Sevilla (ID: 4) en kilómetros
(3, 1, 400),    -- Distancia entre Madrid (ID: 3) y Gijón (ID: 1) en kilómetros
(3, 2, 350),    -- Distancia entre Madrid (ID: 3) y Valencia (ID: 2) en kilómetros
(3, 4, 550),    -- Distancia entre Madrid (ID: 3) y Sevilla (ID: 4) en kilómetros
(4, 1, 900),    -- Distancia entre Sevilla (ID: 4) y Gijón (ID: 1) en kilómetros
(4, 2, 540),    -- Distancia entre Sevilla (ID: 4) y Valencia (ID: 2) en kilómetros
(4, 3, 550);    -- Distancia entre Sevilla (ID: 4) y Madrid (ID: 3) en kilómetros


-- Inserción de waypoints
INSERT INTO "Waypoints" ("route_id", "city_id") VALUES
(1, 5),    -- Ruta ID: 1 pasa por el almacén ID: 5 (Burgos Warehouse)
(1, 6),    -- Ruta ID: 1 pasa por el almacén ID: 6 (Zaragoza Warehouse)
(2, 6),    -- Ruta ID: 2 pasa por el almacén ID: 6 (Zaragoza Warehouse)
(2, 7),    -- Ruta ID: 2 pasa por el almacén ID: 7 (Valladolid Warehouse)
(3, 7),    -- Ruta ID: 3 pasa por el almacén ID: 7 (Valladolid Warehouse)
(3, 8),    -- Ruta ID: 3 pasa por el almacén ID: 8 (Cuenca Warehouse)
(4, 8),    -- Ruta ID: 4 pasa por el almacén ID: 8 (Cuenca Warehouse)
(4, 9),    -- Ruta ID: 4 pasa por el almacén ID: 9 (Toledo Warehouse)
(5, 9),    -- Ruta ID: 5 pasa por el almacén ID: 9 (Toledo Warehouse)
(5, 10),   -- Ruta ID: 5 pasa por el almacén ID: 10 (Cáceres Warehouse)
(6, 10),   -- Ruta ID: 6 pasa por el almacén ID: 10 (Cáceres Warehouse)
(6, 11),   -- Ruta ID: 6 pasa por el almacén ID: 11 (Mérida Warehouse)
(7, 11),   -- Ruta ID: 7 pasa por el almacén ID: 11 (Mérida Warehouse)
(7, 12),   -- Ruta ID: 7 pasa por el almacén ID: 12 (Ciudad Real Warehouse)
(8, 12),   -- Ruta ID: 8 pasa por el almacén ID: 12 (Ciudad Real Warehouse)
(9, 5),    -- Ruta ID: 9 pasa por el almacén ID: 5 (Burgos Warehouse)
(9, 7),    -- Ruta ID: 9 pasa por el almacén ID: 7 (Valladolid Warehouse)
(10, 6),   -- Ruta ID: 10 pasa por el almacén ID: 6 (Zaragoza Warehouse)
(10, 8),   -- Ruta ID: 10 pasa por el almacén ID: 8 (Cuenca Warehouse)
(11, 7),   -- Ruta ID: 11 pasa por el almacén ID: 7 (Valladolid Warehouse)
(11, 9),   -- Ruta ID: 11 pasa por el almacén ID: 9 (Toledo Warehouse)
(12, 8),   -- Ruta ID: 12 pasa por el almacén ID: 8 (Cuenca Warehouse)
(12, 10);  -- Ruta ID: 12 pasa por el almacén ID: 10 (Cáceres Warehouse)


-- Inserción de envíos
INSERT INTO "Shipments" ("package_id", "route_id", "vehicle_id", "pickup_date", "delivery_date") VALUES
(1, 1, 1, "2024-06-10 08:00:00", "2024-06-11 18:00:00"),
(2, 2, 3, "2024-06-12 09:00:00", "2024-06-12 17:00:00");

-- Inserción de seguimiento de paquetes
INSERT INTO "Package_Tracking" ("package_id", "location", "status", "timestamp") VALUES
(1, "Gijón", "En almacén", "2024-06-10 08:00:00"),
(1, "Madrid", "En tránsito", "2024-06-11 10:00:00"),
(1, "Valencia", "Entregado", "2024-06-11 18:00:00"),
(2, "Madrid", "En almacén", "2024-06-12 09:00:00"),
(2, "Sevilla", "Entregado", "2024-06-12 17:00:00");

-- Inserción de intentos de entrega
INSERT INTO "Delivery_Attempts" ("package_id", "attempt_number", "status", "timestamp") VALUES
(1, 1, "Fallido", "2024-06-11 14:00:00"),
(1, 2, "Exitoso", "2024-06-11 18:00:00"),
(2, 1, "Exitoso", "2024-06-12 17:00:00");
