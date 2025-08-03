-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-08-03 15:15:05.13

-- tables
-- Table: booking
CREATE TABLE booking (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    customer_id int  NOT NULL,
    lesson_slots_id int  NOT NULL,
    lesson_type_id int  NOT NULL,
    booking_date timestamp  NOT NULL,
    CONSTRAINT booking_pk PRIMARY KEY (id)
);

-- Table: customer
CREATE TABLE customer (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    name varchar(50)  NOT NULL,
    phone varchar(50)  NOT NULL,
    email varchar(100)  NOT NULL,
    CONSTRAINT customer_ak_1 UNIQUE (phone),
    CONSTRAINT id PRIMARY KEY (id)
);

-- Table: lesson_slots
CREATE TABLE lesson_slots (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    "date" date  NOT NULL,
    start_time time  NOT NULL,
    end_time time  NOT NULL,
    is_available boolean  NOT NULL,
    CONSTRAINT lesson_slots_pk PRIMARY KEY (id)
);

-- Table: lesson_type
CREATE TABLE lesson_type (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    type_name varchar(20)  NOT NULL,
    price decimal(10,2)  NOT NULL,
    CONSTRAINT lesson_type_ak_1 UNIQUE (type_name),
    CONSTRAINT lesson_type_pk PRIMARY KEY (id)
);

-- Table: sale
CREATE TABLE sale (
    id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
    booking_id int  NOT NULL,
    sale_price decimal(10,2)  NOT NULL,
    payment_method varchar(20)  NOT NULL,
    CONSTRAINT sale_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: booking_customer (table: booking)
ALTER TABLE booking ADD CONSTRAINT booking_customer
    FOREIGN KEY (customer_id)
    REFERENCES customer (id);

-- Reference: booking_lesson_slot (table: booking)
ALTER TABLE booking ADD CONSTRAINT booking_lesson_slot
    FOREIGN KEY (lesson_slots_id)
    REFERENCES lesson_slots (id);

-- Reference: booking_lesson_type (table: booking)
ALTER TABLE booking ADD CONSTRAINT booking_lesson_type
    FOREIGN KEY (lesson_type_id)
    REFERENCES lesson_type (id);

-- Reference: sale_booking (table: sale)
ALTER TABLE sale ADD CONSTRAINT sale_booking
    FOREIGN KEY (booking_id)
    REFERENCES booking (id);

-- End of file.

