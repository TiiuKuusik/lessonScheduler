-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-08-05 08:39:15.783
-- Alternative HSQLDB schema with different identity syntax
-- tables
-- Table: booking
CREATE TABLE booking (
                         id INTEGER IDENTITY PRIMARY KEY,
                         booking_date TIMESTAMP NOT NULL,
                         status VARCHAR(20) NOT NULL,
                         time_slot_id INTEGER NOT NULL,
                         lesson_type_id INTEGER NOT NULL,
                         user_id INTEGER NOT NULL
);

-- Table: lesson_type
CREATE TABLE lesson_type (
                             id INTEGER IDENTITY PRIMARY KEY,
                             type_name varchar(20) NOT NULL,
                             price decimal(10,2) NOT NULL,
                             description varchar(100) NOT NULL,
                             duration_minutes int NOT NULL,
                             CONSTRAINT lesson_type_ak_1 UNIQUE (type_name)
);

-- Table: time_slot
CREATE TABLE time_slot (
                           id INTEGER IDENTITY PRIMARY KEY,
                           start_datetime timestamp NOT NULL,
                           end_datetime timestamp NOT NULL,
                           is_available boolean NOT NULL,
);

-- Table: user
CREATE TABLE "user" (
                        id INTEGER IDENTITY PRIMARY KEY,
                        first_name varchar(50) NOT NULL,
                        last_name varchar(50) NOT NULL,
                        phone varchar(50) NOT NULL,
                        email varchar(100) NOT NULL,
                        role varchar(10) NOT NULL,
                        CONSTRAINT customer_ak_1 UNIQUE (phone)
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
ALTER TABLE booking ADD CONSTRAINT booking_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id);

-- Add default values after table creation
ALTER TABLE lesson_type
    ALTER COLUMN duration_minutes SET DEFAULT 120;

ALTER TABLE "user"
    ALTER COLUMN role SET DEFAULT 'user';

-- Add the default value
ALTER TABLE booking
    ALTER COLUMN status SET DEFAULT 'pending';

-- Add the check constraints
ALTER TABLE booking
    ADD CONSTRAINT status_check
        CHECK (status IN ('pending', 'confirmed', 'cancelled', 'completed'));

ALTER TABLE "user"
    ADD CONSTRAINT role_check
        CHECK (role IN ('admin', 'user'));


-- End of file.