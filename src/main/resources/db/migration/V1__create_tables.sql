CREATE TABLE user_table (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE trip_table (
    id UUID PRIMARY KEY,
    from_location VARCHAR(255) NOT NULL,
    to_location VARCHAR(255) NOT NULL,
    trip_date TIMESTAMP NOT NULL,
    human_capacity INTEGER NOT NULL,
    vehicle_capacity INTEGER NOT NULL,
    human_capacity_count INTEGER,
    vehicle_capacity_count INTEGER,
    trip_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);


CREATE TABLE car_type_table (
    id UUID PRIMARY KEY,
    car_type VARCHAR(100) NOT NULL UNIQUE,
    space INTEGER NOT NULL
);

CREATE TABLE ticket_table (
    user_id UUID NOT NULL,
    trip_id UUID NOT NULL,
    user_trip_status VARCHAR(50),
    car_type_id UUID,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, trip_id),
    FOREIGN KEY (user_id) REFERENCES user_table(id),
    FOREIGN KEY (trip_id) REFERENCES trip_table(id),
    FOREIGN KEY (car_type_id) REFERENCES car_type_table(id)
);





