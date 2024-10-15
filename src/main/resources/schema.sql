-- Eliminación de tablas si existen
DROP TABLE IF EXISTS "Packages";
DROP TABLE IF EXISTS "Offices";
DROP TABLE IF EXISTS "Warehouses";
DROP TABLE IF EXISTS "Vehicles";
DROP TABLE IF EXISTS "Routes";
DROP TABLE IF EXISTS "Shipments";
DROP TABLE IF EXISTS "Package_Tracking";
DROP TABLE IF EXISTS "Delivery_Attempts";
DROP TABLE IF EXISTS "Rates";
DROP TABLE IF EXISTS "Carreras";
DROP TABLE IF EXISTS "City";
DROP TABLE IF EXISTS "Waypoints";


CREATE TABLE IF NOT EXISTS "Packages" (
    "package_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "citySender" TEXT,
    "addressSender" TEXT,
    "cityRec" TEXT,
    "addressRec" TEXT,
    "weight" DECIMAL(10, 2),
    "height" DECIMAL(10, 2),
    "length" DECIMAL(10, 2),
    "depth" DECIMAL(10, 2),
    "status" TEXT,
    "actual_location" INTEGER DEFAULT null,
    "price" INTEGER,
    "name_sender" TEXT,
    "email_sender" TEXT,
    "phone_sender" TEXT,
    "name_rec" TEXT,
    "email_rec" TEXT,
    "phone_rec" TEXT,
    FOREIGN KEY ("actual_location") REFERENCES "City"("city_id"),
    FOREIGN KEY ("citySender") REFERENCES "City"("city"),
    FOREIGN KEY ("cityRec") REFERENCES "City"("city")
);

CREATE TABLE IF NOT EXISTS "Offices" (
    "office_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "city_id" TEXT,
    "address" TEXT,
    FOREIGN KEY ("city_id") REFERENCES "City"("city_id")
);

CREATE TABLE IF NOT EXISTS "Warehouses" (
    "warehouse_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "city_id" INTEGER,
    "address" TEXT,
    FOREIGN KEY ("city_id") REFERENCES "City"("city_id")
);

CREATE TABLE IF NOT EXISTS "Vehicles" (
    "vehicle_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "capacity" INTEGER,
    "current_location" TEXT,
    "type" TEXT
);

CREATE TABLE IF NOT EXISTS "City" (
    "city_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "city" TEXT
);

CREATE TABLE IF NOT EXISTS "Waypoints" (
    "waypoint_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "route_id" INTEGER,
    "city_id" INTEGER,
    FOREIGN KEY ("route_id") REFERENCES "Routes"("route_id"),
    FOREIGN KEY ("city_id") REFERENCES "City"("city_id")
);

CREATE TABLE IF NOT EXISTS "Routes" (
    "route_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "origin" INTEGER,
    "destination" INTEGER,
    "distance" INTEGER,
    FOREIGN KEY ("origin") REFERENCES "City"("city_id"),
    FOREIGN KEY ("destination") REFERENCES "City"("city_id")
);

CREATE TABLE IF NOT EXISTS "Shipments" (
    "shipment_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "package_id" INTEGER,
    "route_id" INTEGER,
    "vehicle_id" INTEGER,
    "pickup_date" date,
    "delivery_date" date,
    FOREIGN KEY ("package_id") REFERENCES "Packages"("package_id"),
    FOREIGN KEY ("route_id") REFERENCES "Routes"("route_id"),
    FOREIGN KEY ("vehicle_id") REFERENCES "Vehicles"("vehicle_id")
);

CREATE TABLE IF NOT EXISTS "Package_Tracking" (
    "tracking_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "package_id" INTEGER,
    "location" TEXT,
    "status" TEXT,
    "timestamp" date,
    FOREIGN KEY ("package_id") REFERENCES "Packages"("package_id")
);

CREATE TABLE IF NOT EXISTS "Delivery_Attempts" (
    "attempt_id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "package_id" INTEGER,
    "attempt_number" INTEGER,
    "status" TEXT,
    "timestamp" date,
    FOREIGN KEY ("package_id") REFERENCES "Packages"("package_id")
);

