drop table if exists PLANES;
drop table if exists REPORTS;

CREATE TABLE PLANES (
    id identity,
    model VARCHAR(255) not null,
    manufacturer VARCHAR(255) not null,
    capacity INT,
    maxSpeed INT,
    airline VARCHAR(255) not null,
    fuelCapacity DOUBLE,
    PRIMARY KEY (id)
);

CREATE TABLE REPORT (
    id INTEGER PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    news_site VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL,
    published_at DATE NOT NULL,
    updated_at DATE NOT NULL
);

INSERT INTO PLANES(model, manufacturer, capacity, maxSpeed, airline, fuelCapacity)
VALUES ('Model1','Manufacturer1', 200, 750, 'Airline1', 10000.5),
       ('Model2','Manufacturer2', 250, 800, 'Airline2', 15000.5),
       ('Model3','Manufacturer3', 150, 700, 'Airline3', 12000.5),
       ('Model4','Manufacturer4', 180, 650, 'Airline4', 9000.5),
       ('Model5','Manufacturer5', 230, 750, 'Airline5', 11000.5),
       ('Model5','Manufacturer5', 230, 750, 'Airline5', 11000.5),
       ('Model5','Manufacturer5', 230, 750, 'Airline5', 11000.5);