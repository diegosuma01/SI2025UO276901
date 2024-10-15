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
("Gijón", "Calle A, 10", "Valencia", "Plaza B, 5", 2.5, 0.3, 0.4, 0.2, "IN TRANSIT", 2, 100, "Diego", "diego@uniovi.es", "123456789", "Laura", "laura@ovi.es", "928732629"),
("Valencia", "Calle Z, 33", "Madrid", "Avenida Q, 44", 3.2, 0.4, 0.55, 0.3, "DELIVERING", 1, 110, "Isabel", "isabel@val.es", "999876543", "Pedro", "pedro@mad.es", "988123456"),
("Sevilla", "Calle X, 19", "Gijón", "Plaza Y, 9", 2.1, 0.32, 0.38, 0.25, "REGISTERED", 2, 95, "Lucía", "lucia@sev.es", "987123654", "Carlos", "carlos@uniovi.es", "678321123"),
("Gijón", "Avenida L, 21", "Madrid", "Calle M, 16", 4.0, 0.42, 0.5, 0.3, "IN TRANSIT", 3, 130, "Fernando", "fernando@uniovi.es", "111222333", "Elena", "elena@mad.es", "999654321"),
("Madrid", "Calle P, 17", "Valencia", "Plaza R, 12", 1.5, 0.2, 0.3, 0.12, "READY FOR DELIVERY", 4, 70, "Ana", "ana@mad.es", "555666777", "Marta", "marta@val.es", "876543210"),
("Valencia", "Calle E, 22", "Sevilla", "Avenida F, 18", 3.8, 0.35, 0.45, 0.28, "DELIVERING", 1, 120, "Pablo", "pablo@val.es", "777888999", "Alberto", "alberto@sev.es", "765432189"),
("Sevilla", "Calle T, 29", "Madrid", "Calle U, 25", 2.3, 0.3, 0.4, 0.18, "REGISTERED", 2, 105, "Raúl", "raul@sev.es", "654789123", "Rosa", "rosa@mad.es", "987654321"),
("Madrid", "Calle V, 31", "Gijón", "Plaza W, 14", 4.5, 0.45, 0.6, 0.35, "IN TRANSIT", 3, 140, "Beatriz", "beatriz@mad.es", "321987654", "Andrés", "andres@uniovi.es", "654321987"),
("Gijón", "Avenida N, 27", "Valencia", "Calle O, 9", 2.0, 0.28, 0.36, 0.22, "IN TRANSIT", 4, 90, "Rocío", "rocio@uniovi.es", "876543210", "Luis", "luis@val.es", "123456789"),
("Valencia", "Plaza S, 30", "Sevilla", "Calle T, 6", 1.9, 0.25, 0.33, 0.14, "REGISTERED", 1, 85, "Clara", "clara@val.es", "234567890", "Miguel", "miguel@sev.es", "765432123"),
("Sevilla", "Calle Q, 11", "Gijón", "Calle P, 13", 3.6, 0.4, 0.52, 0.3, "IN TRANSIT", 2, 115, "Sergio", "sergio@sev.es", "890123456", "Sonia", "sonia@uniovi.es", "765987432"),
("Gijón", "Calle A, 5", "Madrid", "Avenida B, 10", 2.8, 0.3, 0.4, 0.25, "DELIVERING", 3, 100, "Tomás", "tomas@uniovi.es", "999555444", "Elena", "elena@mad.es", "876321456"),
("Madrid", "Calle Z, 19", "Valencia", "Calle Y, 17", 1.7, 0.22, 0.34, 0.12, "REGISTERED", 4, 75, "Mario", "mario@mad.es", "654321789", "Laura", "laura@val.es", "876543219"),
("Valencia", "Calle K, 24", "Gijón", "Calle J, 21", 2.9, 0.33, 0.43, 0.2, "IN TRANSIT", 1, 110, "Carmen", "carmen@val.es", "123789456", "David", "david@uniovi.es", "654987321"),
("Sevilla", "Avenida C, 14", "Madrid", "Calle D, 20", 4.1, 0.42, 0.55, 0.3, "READY FOR DELIVERY", 2, 135, "Lorena", "lorena@sev.es", "432187654", "Raquel", "raquel@mad.es", "321654987"),
("Madrid", "Calle H, 12", "Sevilla", "Calle G, 22", 1.6, 0.21, 0.33, 0.11, "READY FOR DELIVERY", 3, 85, "Julia", "julia@mad.es", "987654321", "Marcos", "marcos@sev.es", "765432987"),
("Gijón", "Calle E, 28", "Valencia", "Calle F, 7", 3.4, 0.36, 0.48, 0.3, "REGISTERED", 4, 105, "Daniel", "daniel@uniovi.es", "876543212", "Sara", "sara@val.es", "987321654"),
("Valencia", "Plaza L, 15", "Sevilla", "Avenida M, 18", 2.2, 0.3, 0.4, 0.25, "IN TRANSIT", 1, 95, "Lucas", "lucas@val.es", "432198765", "Pilar", "pilar@sev.es", "654987123"),
("Sevilla", "Calle O, 33", "Madrid", "Avenida P, 27", 4.3, 0.43, 0.6, 0.35, "DELIVERING", 2, 140, "Nuria", "nuria@sev.es", "765432101", "Antonio", "antonio@mad.es", "432156789");


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
(15, "Gijón", "V"),
(10, "Valencia", "V"),
(12, "Madrid", "V"),
(11, "Sevilla", "V"),
(10, "Gijón", "T"),
(15, "Valencia", "T"),
(12, "Madrid", "T"),
(11, "Sevilla", "T");


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
(4, 3, 550);    


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
