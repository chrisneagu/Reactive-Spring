drop table if exists SPACECRAFTS;
drop table if exists REPORTS;

CREATE TABLE SPACECRAFTS (
    id identity,
    model VARCHAR(255) not null,
    manufacturer VARCHAR(255) not null,
    capacity INT,
    maxSpeed INT,
    company VARCHAR(255) not null,
    fuelCapacity DOUBLE,
    PRIMARY KEY (id)
);

CREATE TABLE REPORTS (
    id identity,
    title VARCHAR(251) NOT NULL,
    url VARCHAR(201) NOT NULL,
    image_url VARCHAR(201) NOT NULL,
    news_site TEXT NOT NULL,
    summary TEXT NOT NULL,
    published_at VARCHAR(255) NOT NULL,
    updated_at VARCHAR(255) NOT NULL,
    details VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO SPACECRAFTS(model, manufacturer, capacity, maxSpeed, company, fuelCapacity)
VALUES ('Soyuz 71S','Soyuz', 2, 586800, 'NASA', 254213),
       ('Starship','SpaceX', 5, 470000, 'SpaceX', 250000),
       ('Arktika-M','RosKosmos', 4, 540000, 'Sojuz', 200423.5)