-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-08-05 08:39:15.783
-- Alternative HSQLDB schema with different identity syntax
-- tables

-- Table: lesson_type
CREATE TABLE lesson_type (
                             id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                             type_name varchar(20) NOT NULL,
                             price decimal(10,2) NOT NULL,
                             description varchar(100) NOT NULL,
                             duration_minutes int NOT NULL,
                             CONSTRAINT lesson_type_ak_1 UNIQUE (type_name),
                             CONSTRAINT lesson_type_pk PRIMARY KEY (id)
);

-- Table: time_slot
CREATE TABLE time_slot (
                           id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                           start_datetime timestamp NOT NULL,
                           end_datetime timestamp NOT NULL,
                           is_available boolean NOT NULL,
                           CONSTRAINT time_slot_pk PRIMARY KEY (id)
);

-- Table: customer
CREATE TABLE customer (
                        id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                        first_name varchar(50) NOT NULL,
                        last_name varchar(50) NOT NULL,
                        phone varchar(50) NOT NULL,
                        email varchar(100) NOT NULL,
                        role varchar(10) NOT NULL,
                        CONSTRAINT customer_ak_1 UNIQUE (phone),
                        CONSTRAINT customer_pk PRIMARY KEY (id)
);
-- Table: booking
CREATE TABLE booking (
                         id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                         booking_date TIMESTAMP NOT NULL,
                         status VARCHAR(20) NOT NULL,
                         time_slot_id INTEGER NOT NULL,
                         lesson_type_id INTEGER NOT NULL,
                         customer_id INTEGER NOT NULL,
                         CONSTRAINT booking_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: booking_lesson_type (table: booking)
ALTER TABLE booking ADD CONSTRAINT booking_lesson_type
    FOREIGN KEY (lesson_type_id)
        REFERENCES lesson_type (id);

-- Reference: booking_time_slot (table: booking)
ALTER TABLE booking ADD CONSTRAINT booking_time_slot
    FOREIGN KEY (time_slot_id)
        REFERENCES time_slot (id);

-- Reference: booking_user (table: booking)
ALTER TABLE booking ADD CONSTRAINT booking_customer
    FOREIGN KEY (customer_id)
        REFERENCES customer (id);

-- Add default values after table creation
ALTER TABLE lesson_type
    ALTER COLUMN duration_minutes SET DEFAULT 120;

ALTER TABLE customer
    ALTER COLUMN role SET DEFAULT 'customer';

-- Add the default value
ALTER TABLE booking
    ALTER COLUMN status SET DEFAULT 'pending';

-- Add the check constraints
ALTER TABLE booking
    ADD CONSTRAINT status_check
        CHECK (status IN ('pending', 'confirmed', 'cancelled', 'completed'));

ALTER TABLE customer
    ADD CONSTRAINT role_check
        CHECK (role IN ('admin', 'customer'));


-- End of file.