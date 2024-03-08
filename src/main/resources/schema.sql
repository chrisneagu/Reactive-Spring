drop table if exists Planes;

CREATE TABLE Planes (
    id identity,
    model VARCHAR(255) not null,
    manufacturer VARCHAR(255) not null,
    capacity INT,
    maxSpeed INT,
    airline VARCHAR(255) not null,
    fuelCapacity DOUBLE,
    PRIMARY KEY (id)
);

INSERT INTO Planes(id, model, manufacturer, capacity, maxSpeed, airline, fuelCapacity)
VALUES (1,'Model1','Manufacturer1', 200, 750, 'Airline1', 10000.5),
       (2,'Model2','Manufacturer2', 250, 800, 'Airline2', 15000.5),
       (3,'Model3','Manufacturer3', 150, 700, 'Airline3', 12000.5),
       (4,'Model4','Manufacturer4', 180, 650, 'Airline4', 9000.5),
       (5,'Model5','Manufacturer5', 230, 750, 'Airline5', 11000.5);