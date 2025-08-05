-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-08-05 08:39:15.783

-- tables
-- Table: booking
CREATE TABLE booking (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    booking_date timestamp  NOT NULL,
    status varchar(20)  NOT NULL DEFAULT pending CHECK (status IN ('pending', 'confirmed', 'cancelled', 'completed')),
    time_slot_id int  NOT NULL,
    lesson_type_id int  NOT NULL,
    user_id int  NOT NULL,
    CONSTRAINT booking_pk PRIMARY KEY (id)
);

-- Table: lesson_type
CREATE TABLE lesson_type (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    type_name varchar(20)  NOT NULL,
    price decimal(10,2)  NOT NULL,
    description varchar(100)  NOT NULL,
    duration_minutes int  NOT NULL DEFAULT 120,
    CONSTRAINT lesson_type_ak_1 UNIQUE (type_name),
    CONSTRAINT lesson_type_pk PRIMARY KEY (id)
);

-- Table: time_slot
CREATE TABLE time_slot (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    created_by_user_id int  NOT NULL,
    lesson_type_id int  NOT NULL,
    "date" date  NOT NULL,
    start_datetime timestamp  NOT NULL,
    end_datetime timestamp  NOT NULL,
    is_available boolean  NOT NULL,
    max_bookings int  NOT NULL DEFAULT 1,
    CONSTRAINT time_slot_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user" (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    first_name varchar(50)  NOT NULL,
    last_name varchar(50)  NOT NULL,
    phone varchar(50)  NOT NULL,
    email varchar(100)  NOT NULL,
    role varchar(10)  NOT NULL DEFAULT user CHECK (role IN ('admin', 'user')),
    CONSTRAINT customer_ak_1 UNIQUE (phone),
    CONSTRAINT id PRIMARY KEY (id)
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

-- Reference: time_slot_lesson_type (table: time_slot)
ALTER TABLE time_slot ADD CONSTRAINT time_slot_lesson_type
    FOREIGN KEY (lesson_type_id)
    REFERENCES lesson_type (id);

-- Reference: time_slot_user (table: time_slot)
ALTER TABLE time_slot ADD CONSTRAINT time_slot_user
    FOREIGN KEY (created_by_user_id)
    REFERENCES "user" (id);

-- End of file.

