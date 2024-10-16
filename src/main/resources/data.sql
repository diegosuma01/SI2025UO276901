-- Inserción de ciudades
INSERT INTO "City" ("city") VALUES
("Gijón"),
("Valencia"),
("Madrid"),
("Sevilla"),
("Burgos Warehouse"),
("Zaragoza Warehouse"),
("Valladolid Warehouse"),
("Toledo Warehouse"),
("Cáceres Warehouse");

-- iNSERCION DE PAQUETES
INSERT INTO "Packages" ("citySender", "addressSender", "cityRec", "addressRec", "weight", "height", "length", "depth", "status", "actual_location", "price", "name_sender", "email_sender", "phone_sender", "name_rec", "email_rec", "phone_rec") VALUES
("Gijón", "Calle A, 10", "Valencia", "Plaza B, 5", 2.5, 0.3, 0.4, 0.2, "READY FOR DELIVERY", 2, 100, "Diego", "diego@uniovi.es", "123456789", "Laura", "laura@ovi.es", "928732629"),
('Gijón', 'Calle Mayor, 12', 'Madrid', 'Calle Gran Vía, 1', 3.2, 0.4, 0.5, 0.3, 'IN TRANSIT', 5, 150, 'Jorge', 'jorge@gijon.com', '698745123', 'Ana', 'ana@madrid.com', '698745987'),
('Gijón', 'Avenida del Mar, 34', 'Madrid', 'Plaza Mayor, 7', 4.5, 0.5, 0.6, 0.35, 'IN TRANSIT', 7, 180, 'Lucía', 'lucia@uniovi.es', '634578912', 'Pedro', 'pedro@madrid.com', '692341235'),
('Madrid', 'Calle Arenal, 3', 'Valencia', 'Avenida del Puerto, 15', 2.7, 0.3, 0.4, 0.25, 'IN TRANSIT', 8, 120, 'Luis', 'luis@madrid.com', '623987456', 'Elena', 'elena@valencia.com', '691234567'),
('Valencia', 'Calle Colon, 5', 'Gijón', 'Calle Jovellanos, 22', 3.0, 0.35, 0.4, 0.28, 'IN TRANSIT', 6, 130, 'David', 'david@valencia.com', '643567890', 'Pablo', 'pablo@gijon.com', '698741258'),
('Sevilla', 'Calle Sierpes, 10', 'Madrid', 'Calle Alcalá, 4', 5.0, 0.6, 0.7, 0.4, 'IN TRANSIT', 9, 200, 'Maria', 'maria@sevilla.com', '689741235', 'Carlos', 'carlos@madrid.com', '692134567'),
('Madrid', 'Calle Serrano, 28', 'Sevilla', 'Calle Feria, 8', 4.1, 0.45, 0.55, 0.3, 'IN TRANSIT', 9, 170, 'Carmen', 'carmen@madrid.com', '628745632', 'Rafael', 'rafael@sevilla.com', '693412789'),
('Gijón', 'Avenida Costa, 15', 'Sevilla', 'Calle Betis, 12', 6.5, 0.7, 0.8, 0.5, 'IN TRANSIT', 5, 250, 'Alberto', 'alberto@gijon.com', '632784512', 'Lola', 'lola@sevilla.com', '697541236'),
('Valencia', 'Avenida Blasco Ibáñez, 60', 'Madrid', 'Calle Preciados, 9', 2.8, 0.35, 0.45, 0.3, 'READY FOR DELIVERY', 3, 110, 'Paula', 'paula@valencia.com', '638745623', 'Javier', 'javier@madrid.com', '695412345'),
('Gijón', 'Calle Los Moros, 25', 'Valencia', 'Calle Xátiva, 6', 3.3, 0.45, 0.55, 0.35, 'IN TRANSIT', 5, 140, 'Alejandro', 'alejandro@uniovi.es', '628745123', 'Marta', 'marta@valencia.com', '699123456'),
('Sevilla', 'Calle Triana, 17', 'Valencia', 'Calle Caballeros, 2', 4.7, 0.5, 0.6, 0.4, 'IN TRANSIT', 9, 210, 'Antonio', 'antonio@sevilla.com', '699784512', 'Isabel', 'isabel@valencia.com', '690345671'),
('Gijón', 'Calle Asturias, 5', 'Madrid', 'Calle Princesa, 40', 2.6, 0.3, 0.4, 0.25, 'IN TRANSIT', 7, 125, 'Sonia', 'sonia@gijon.com', '691234567', 'Diego', 'diego@madrid.com', '698745321'),
('Madrid', 'Calle Fuencarral, 20', 'Valencia', 'Plaza Ayuntamiento, 12', 3.8, 0.5, 0.6, 0.4, 'READY FOR DELIVERY', 2, 135, 'Mario', 'mario@madrid.com', '693456789', 'Nuria', 'nuria@valencia.com', '699874512'),
('Sevilla', 'Calle San Jacinto, 9', 'Gijón', 'Avenida Constitución, 14', 5.3, 0.6, 0.7, 0.5, 'IN TRANSIT', 5, 240, 'Paco', 'paco@sevilla.com', '691235478', 'Beatriz', 'beatriz@gijon.com', '698745689'),
('Valencia', 'Calle del Mar, 22', 'Sevilla', 'Calle Arfe, 7', 4.2, 0.45, 0.55, 0.35, 'IN TRANSIT', 3, 160, 'Cristina', 'cristina@valencia.com', '699874521', 'Alfonso', 'alfonso@sevilla.com', '690123487'),
('Gijón', 'Avenida Schultz, 50', 'Madrid', 'Calle Bailén, 12', 3.1, 0.4, 0.5, 0.3, 'IN TRANSIT', 5, 145, 'Juan', 'juan@gijon.com', '687451236', 'Pilar', 'pilar@madrid.com', '698745987'),
('Madrid', 'Calle Alcalá, 15', 'Valencia', 'Calle San Vicente, 30', 2.4, 0.3, 0.4, 0.25, 'IN TRANSIT', 8, 115, 'Esteban', 'esteban@madrid.com', '634785123', 'Sara', 'sara@valencia.com', '692315478'),
('Valencia', 'Calle Sagunto, 18', 'Gijón', 'Calle Corrida, 3', 3.6, 0.45, 0.55, 0.4, 'IN TRANSIT', 6, 155, 'Carlos', 'carlos@valencia.com', '623145789', 'Lidia', 'lidia@gijon.com', '691234587'),
('Sevilla', 'Calle Reyes Católicos, 19', 'Madrid', 'Calle Velázquez, 4', 4.9, 0.55, 0.65, 0.4, 'IN TRANSIT', 9, 195, 'Manuel', 'manuel@sevilla.com', '698745632', 'Ana', 'ana@madrid.com', '692341298'),
('Madrid', 'Calle Orense, 10', 'Sevilla', 'Calle Feria, 15', 4.0, 0.45, 0.55, 0.35, 'IN TRANSIT', 9, 175, 'Mónica', 'monica@madrid.com', '698745123', 'Jaime', 'jaime@sevilla.com', '699874523'),
('Valencia', 'Calle Alboraya, 5', 'Madrid', 'Calle Goya, 25', 3.5, 0.4, 0.5, 0.3, 'IN TRANSIT', 8, 140, 'Eva', 'eva@valencia.com', '634578951', 'Raúl', 'raul@madrid.com', '699874521'),
('Gijón', 'Calle Instituto, 13', 'Sevilla', 'Calle Alfonso XII, 3', 6.0, 0.65, 0.75, 0.5, 'IN TRANSIT', 5, 230, 'Daniel', 'daniel@gijon.com', '698745321', 'Rocío', 'rocio@sevilla.com', '690123569'),
('Madrid', 'Calle Atocha, 30', 'Valencia', 'Calle Ruzafa, 18', 2.9, 0.35, 0.45, 0.3, 'IN TRANSIT', 8, 125, 'Irene', 'irene@madrid.com', '699874523', 'Alberto', 'alberto@valencia.com', '690123457'),
('Valencia', 'Avenida Reino, 11', 'Gijón', 'Calle Cabrales, 7', 3.7, 0.45, 0.55, 0.35, 'IN TRANSIT', 6, 160, 'Fernando', 'fernando@valencia.com', '699874569', 'Nerea', 'nerea@gijon.com', '691234512'),
('Sevilla', 'Calle Pagés del Corro, 24', 'Madrid', 'Calle Toledo, 22', 5.2, 0.6, 0.7, 0.45, 'IN TRANSIT', 9, 205, 'Lourdes', 'lourdes@sevilla.com', '690123487', 'Luis', 'luis@madrid.com', '698745123'),
('Gijón', 'Calle Asturias, 30', 'Madrid', 'Avenida América, 4', 3.0, 0.4, 0.5, 0.3, 'IN TRANSIT', 7, 150, 'Patricia', 'patricia@gijon.com', '692134567', 'Sergio', 'sergio@madrid.com', '699874523'),
('Madrid', 'Calle Goya, 40', 'Sevilla', 'Avenida Menéndez Pelayo, 6', 4.6, 0.5, 0.6, 0.35, 'IN TRANSIT', 9, 185, 'Ángel', 'angel@madrid.com', '699874521', 'Sara', 'sara@sevilla.com', '690123456'),
('Valencia', 'Calle Guillem de Castro, 13', 'Sevilla', 'Calle San Fernando, 17', 4.3, 0.5, 0.6, 0.4, 'IN TRANSIT', 8, 165, 'Marina', 'marina@valencia.com', '691234569', 'Tomás', 'tomas@sevilla.com', '699874512'),
('Sevilla', 'Calle Tetuán, 29', 'Valencia', 'Calle Paz, 26', 5.1, 0.6, 0.7, 0.5, 'IN TRANSIT', 9, 220, 'Susana', 'susana@sevilla.com', '690123478', 'Pablo', 'pablo@valencia.com', '691234587'),
('Gijón', 'Avenida Galicia, 21', 'Valencia', 'Calle Colón, 8', 3.2, 0.4, 0.5, 0.35, 'IN TRANSIT', 6, 145, 'Rubén', 'ruben@gijon.com', '691234589', 'Elisa', 'elisa@valencia.com', '699874512'),
('Madrid', 'Calle Princesa, 50', 'Sevilla', 'Calle Alfonso XIII, 10', 4.7, 0.55, 0.65, 0.4, 'IN TRANSIT', 9, 195, 'Tomás', 'tomas@madrid.com', '698745623', 'Lucía', 'lucia@sevilla.com', '690123487'),
('Valencia', 'Calle Ángel Guimerá, 16', 'Gijón', 'Calle Uría, 11', 3.4, 0.45, 0.55, 0.3, 'IN TRANSIT', 5, 150, 'Óscar', 'oscar@valencia.com', '690123456', 'Alicia', 'alicia@gijon.com', '691234589'),
('Sevilla', 'Calle Adriano, 14', 'Madrid', 'Calle de la Cruz, 6', 4.8, 0.55, 0.65, 0.4, 'IN TRANSIT', 9, 190, 'Roberto', 'roberto@sevilla.com', '690123487', 'Teresa', 'teresa@madrid.com', '698745125'),
('Gijón', 'Avenida Constitución, 12', 'Valencia', 'Calle Jorge Juan, 18', 3.9, 0.5, 0.6, 0.35, 'IN TRANSIT', 6, 160, 'Alba', 'alba@gijon.com', '699874512', 'Isabel', 'isabel@valencia.com', '690123478'),
('Madrid', 'Calle Sagasta, 20', 'Gijón', 'Avenida Schultz, 50', 2.9, 0.35, 0.45, 0.3, 'IN TRANSIT', 7, 140, 'Silvia', 'silvia@madrid.com', '698745623', 'Roberto', 'roberto@gijon.com', '690123456'),
('Valencia', 'Calle Sueca, 10', 'Sevilla', 'Calle Imagen, 5', 5.4, 0.65, 0.75, 0.5, 'IN TRANSIT', 9, 230, 'Miguel', 'miguel@valencia.com', '690123589', 'Estefanía', 'estefania@sevilla.com', '691234567'),
('Gijón', 'Calle Doctor Fleming, 23', 'Madrid', 'Calle Goya, 8', 2.7, 0.35, 0.45, 0.3, 'IN TRANSIT', 7, 125, 'Víctor', 'victor@gijon.com', '698745632', 'Clara', 'clara@madrid.com', '690123478'),
('Sevilla', 'Calle Baños, 20', 'Valencia', 'Calle Guillem de Castro, 15', 4.5, 0.6, 0.7, 0.45, 'IN TRANSIT', 9, 210, 'Begoña', 'begona@sevilla.com', '690123569', 'Rafael', 'rafael@valencia.com', '691234578'),
('Valencia', 'Avenida Aragón, 32', 'Madrid', 'Calle Génova, 14', 3.1, 0.4, 0.5, 0.3, 'IN TRANSIT', 8, 145, 'Andrés', 'andres@valencia.com', '691234567', 'María', 'maria@madrid.com', '690123589'),
('Gijón', 'Calle Corrida, 12', 'Sevilla', 'Avenida Kansas City, 2', 6.2, 0.7, 0.8, 0.5, 'IN TRANSIT', 7, 245, 'Raquel', 'raquel@gijon.com', '698745123', 'Enrique', 'enrique@sevilla.com', '690123456'),
('Madrid', 'Calle Princesa, 25', 'Valencia', 'Calle Don Juan de Austria, 7', 2.5, 0.3, 0.4, 0.25, 'READY FOR DELIVERY', 2, 115, 'Cristina', 'cristina@madrid.com', '690123458', 'Sara', 'sara@valencia.com', '691234587'),
('Gijón', 'Calle Pelayo, 10', 'Madrid', 'Calle Alcalá, 22', 3.0, 0.4, 0.5, 0.3, 'REGISTERED', 1, 120, 'Sergio', 'sergio@gijon.com', '698745123', 'Andrea', 'andrea@madrid.com', '691234567'),
('Madrid', 'Calle Goya, 50', 'Valencia', 'Calle Colon, 15', 2.6, 0.35, 0.4, 0.25, 'READY FOR DELIVERY', 2, 110, 'Isabel', 'isabel@madrid.com', '692341235', 'David', 'david@valencia.com', '690987654'),
('Sevilla', 'Calle Feria, 12', 'Valencia', 'Calle del Mar, 19', 5.4, 0.6, 0.7, 0.45, 'REGISTERED', 4, 200, 'Rocío', 'rocio@sevilla.com', '690345678', 'Elena', 'elena@valencia.com', '690345671'),
('Valencia', 'Calle San Vicente, 14', 'Madrid', 'Calle Atocha, 9', 3.5, 0.45, 0.5, 0.3, 'READY FOR DELIVERY', 3, 135, 'Carlos', 'carlos@valencia.com', '698745632', 'Luis', 'luis@madrid.com', '699874521'),
('Gijón', 'Calle Asturias, 15', 'Valencia', 'Avenida del Puerto, 10', 2.8, 0.35, 0.45, 0.25, 'REGISTERED', 1, 120, 'Alberto', 'alberto@gijon.com', '699874512', 'Marta', 'marta@valencia.com', '691234589'),
('Madrid', 'Calle Princesa, 20', 'Sevilla', 'Avenida Menéndez Pelayo, 22', 4.2, 0.5, 0.6, 0.35, 'REGISTERED', 3, 180, 'Raúl', 'raul@madrid.com', '690874521', 'Lola', 'lola@sevilla.com', '691234567'),
('Sevilla', 'Calle Sierpes, 15', 'Madrid', 'Calle Serrano, 12', 5.0, 0.6, 0.7, 0.4, 'READY FOR DELIVERY', 3, 210, 'Javier', 'javier@sevilla.com', '691234598', 'Lucía', 'lucia@madrid.com', '692345671'),
('Valencia', 'Calle Ruzafa, 18', 'Gijón', 'Calle Corrida, 22', 3.9, 0.5, 0.6, 0.35, 'REGISTERED', 2, 145, 'Álvaro', 'alvaro@valencia.com', '690987654', 'Clara', 'clara@gijon.com', '691234578'),
('Gijón', 'Avenida Castilla, 7', 'Sevilla', 'Calle San Fernando, 16', 4.5, 0.55, 0.65, 0.4, 'REGISTERED', 1, 190, 'Juan', 'juan@gijon.com', '699874523', 'Rafael', 'rafael@sevilla.com', '690345687'),
('Madrid', 'Calle Orense, 9', 'Valencia', 'Calle Sueca, 13', 3.2, 0.4, 0.5, 0.3, 'READY FOR DELIVERY', 2, 150, 'Teresa', 'teresa@madrid.com', '692345678', 'Esther', 'esther@valencia.com', '691234569'),
('Sevilla', 'Calle Pagés del Corro, 27', 'Madrid', 'Calle Arenal, 5', 5.3, 0.6, 0.7, 0.45, 'REGISTERED', 4, 220, 'Ana', 'ana@sevilla.com', '690987623', 'Miguel', 'miguel@madrid.com', '692341257'),
('Valencia', 'Avenida Aragón, 33', 'Madrid', 'Calle de la Cruz, 4', 3.7, 0.45, 0.55, 0.3, 'REGISTERED', 2, 130, 'Laura', 'laura@valencia.com', '691345678', 'Ignacio', 'ignacio@madrid.com', '690345681'),
('Gijón', 'Calle Cabrales, 11', 'Madrid', 'Calle Velázquez, 28', 3.0, 0.4, 0.5, 0.3, 'REGISTERED', 1, 140, 'Pablo', 'pablo@gijon.com', '690987456', 'Raquel', 'raquel@madrid.com', '691234987'),
('Madrid', 'Calle Preciados, 17', 'Valencia', 'Avenida Guillem de Castro, 25', 2.9, 0.35, 0.45, 0.3, 'READY FOR DELIVERY', 2, 125, 'Luis', 'luis@madrid.com', '691234598', 'Marta', 'marta@valencia.com', '690123458'),
('Sevilla', 'Calle Adriano, 22', 'Valencia', 'Calle Caballeros, 8', 5.0, 0.6, 0.7, 0.4, 'REGISTERED', 4, 230, 'Francisco', 'francisco@sevilla.com', '690987654', 'Eva', 'eva@valencia.com', '690345612'),
('Valencia', 'Calle del Mar, 21', 'Madrid', 'Avenida América, 9', 3.4, 0.45, 0.5, 0.3, 'REGISTERED', 2, 150, 'Manuel', 'manuel@valencia.com', '692345678', 'Carmen', 'carmen@madrid.com', '691234576'),
('Gijón', 'Avenida Schultz, 18', 'Sevilla', 'Calle Feria, 9', 6.0, 0.7, 0.8, 0.5, 'REGISTERED', 1, 220, 'Miguel', 'miguel@gijon.com', '690874521', 'Isabel', 'isabel@sevilla.com', '691234598'),
('Madrid', 'Calle Toledo, 10', 'Gijón', 'Calle Instituto, 23', 2.5, 0.3, 0.4, 0.25, 'READY FOR DELIVERY', 1, 115, 'Sofía', 'sofia@madrid.com', '691234567', 'Sara', 'sara@gijon.com', '690987654'),
('Sevilla', 'Calle Betis, 14', 'Gijón', 'Avenida de la Costa, 12', 5.7, 0.6, 0.7, 0.45, 'READY FOR DELIVERY', 1, 240, 'María', 'maria@sevilla.com', '690123458', 'Nerea', 'nerea@gijon.com', '691234589'),
('Valencia', 'Avenida Reino de Valencia, 5', 'Madrid', 'Calle Sagasta, 32', 2.8, 0.35, 0.45, 0.3, 'READY FOR DELIVERY', 3, 120, 'Jorge', 'jorge@valencia.com', '690987123', 'Esther', 'esther@madrid.com', '691345678');


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
INSERT INTO "Vehicles" ("capacity", "current_location", "type") VALUES
(15, 'Gijón', 'V'), 
(18, 'Gijón', 'V'), 
(14, 'Valencia', 'V'), 
(16, 'Valencia', 'V'), 
(17, 'Madrid', 'V'), 
(19, 'Madrid', 'V'), 
(12, 'Sevilla', 'V'), 
(16, 'Sevilla', 'V'), 
(150, 'Gijón', 'T'), 
(130, 'Gijón', 'T'), 
(160, 'Burgos Warehouse', 'T'), 
(170, 'Burgos Warehouse', 'T'), 
(140, 'Burgos Warehouse', 'T'), 
(110, 'Burgos Warehouse', 'T'), 
(170, 'Zaragoza Warehouse', 'T'), 
(160, 'Zaragoza Warehouse', 'T'), 
(150, 'Valladolid Warehouse', 'T'), 
(140, 'Valladolid Warehouse', 'T'), 
(130, 'Valladolid Warehouse', 'T'), 
(160, 'Valladolid Warehouse', 'T'), 
(170, 'Toledo Warehouse', 'T'), 
(160, 'Toledo Warehouse', 'T'), 
(180, 'Toledo Warehouse', 'T'), 
(130, 'Cáceres Warehouse', 'T'), 
(160, 'Cáceres Warehouse', 'T');


-- Inserción de rutas
INSERT INTO "Routes" ("origin", "destination", "distance") VALUES
(1, 2, 600),
(1, 3, 400), 
(1, 4, 900), 
(2, 1, 600),    
(2, 3, 350),   
(2, 4, 540),    
(3, 1, 400),
(3, 2, 350),    
(3, 4, 550), 
(4, 1, 900), 
(4, 2, 540),  
(4, 3, 550),
(1,1,0),
(2,2,0),
(3,3,0),
(4,4,0);


-- Inserción de waypoints
INSERT INTO "Waypoints" ("route_id", "city_id") VALUES
(1, 5),
(1, 6),
(2, 5),
(2, 7),
(3, 5), 
(3, 7),
(3, 3),
(3, 9),   
(4, 6),    
(4, 5),    
(5, 8),    
(6, 8),   
(6, 3),
(6, 6),   
(7, 7),   
(7, 5),   
(8, 8),   
(9, 9), 
(10, 9),    
(10, 3),    
(10, 7),   
(10, 5), 
(11, 9),   
(11, 3),   
(11, 8),  
(12, 9);  

-- Inserción de envíos
INSERT INTO "Shipments" ("package_id", "route_id", "vehicle_id", "pickup_date", "delivery_date") VALUES
(1, 1, 1, "2024-06-10 08:00:00", "2024-06-11 19:00:00"),
(2, 2, 3, "2024-06-12 09:00:00", "2024-06-12 17:00:00");

-- Inserción de intentos de entrega
INSERT INTO "Delivery_Attempts" ("package_id", "attempt_number", "status", "timestamp") VALUES
(1, 1, "Fail", '2024-06-11 14:00:00'),
(1, 2, "Fail", '2024-06-11 18:00:00');

-- Inserción de seguimiento de paquetes
INSERT INTO "Package_Tracking" ("package_id", "location", "status", "timestamp") VALUES
(1, "Gijón", "REGISTERED", "2024-06-11 18:32:20"),
(1, "MADRID", "IN TRANSIT", "2024-09-11 10:7:04"),
(1, "VALENCIA", "READY FOR DELIVERY", "2024-10-11 12:03:10"),
(2, "Madrid", "IN TRANSIT", "2024-06-11 18:00:00"),
(2, "Sevilla", "RECIEVED", "2024-06-11 18:00:00");
