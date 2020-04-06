CREATE DATABASE web_cars
    WITH
    OWNER = cars_web
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


CREATE TABLE customer
(
    id SERIAL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(50),
    CONSTRAINT customer_pk PRIMARY KEY(id),
    CONSTRAINT customer_email_uniqe UNIQUE (email)
);

INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'David','Adams', 'admin@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'John','Doe',    'john@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'Ajay','Rao',    'ajay@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'Mary','Public', 'mary@job4j.com' );
INSERT INTO customer (password, first_name, last_name, email) VALUES('password', 'Maxwell','Dixon','max@job4j.com' );


CREATE TABLE cars_brand (
   id            SERIAL,
   name          CHARACTER VARYING(100),
   CONSTRAINT cars_brand_pk PRIMARY KEY (id)
);
INSERT INTO cars_brand (name) VALUES ('Hyundai');
INSERT INTO cars_brand (name) VALUES ('Toyota');
INSERT INTO cars_brand (name) VALUES ('KIA');

CREATE TABLE cars_model (
   id            SERIAL,
   brand_id      INTEGER REFERENCES cars_brand (id),
   name          CHARACTER VARYING(100),
CONSTRAINT cars_model_pk PRIMARY KEY (id)
);
INSERT INTO cars_model (brand_id, name) VALUES (1 , 'Solaris');
INSERT INTO cars_model (brand_id, name) VALUES (1 , 'Starex');
INSERT INTO cars_model (brand_id, name) VALUES (1 , 'Tucson');
INSERT INTO cars_model (brand_id, name) VALUES (2 , 'Camry');
INSERT INTO cars_model (brand_id, name) VALUES (2 , 'RAV4');
INSERT INTO cars_model (brand_id, name) VALUES (2 , 'Land Cruiser Prado');
INSERT INTO cars_model (brand_id, name) VALUES (3 , 'Rio');
INSERT INTO cars_model (brand_id, name) VALUES (3 , 'Optima');
INSERT INTO cars_model (brand_id, name) VALUES (3 , 'Soul');


CREATE TABLE cars_body_type (
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_body_type_pk PRIMARY KEY (id)
);
INSERT INTO cars_body_type (name) VALUES ('sedan');
INSERT INTO cars_body_type (name) VALUES ('chitchback');
INSERT INTO cars_body_type (name) VALUES ('wagon');
INSERT INTO cars_body_type (name) VALUES ('SUV');
INSERT INTO cars_body_type (name) VALUES ('minivan');


CREATE TABLE cars_transmission(
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_transmission_pk PRIMARY KEY (id)
);
INSERT INTO cars_transmission(name) VALUES ('auto');
INSERT INTO cars_transmission(name) VALUES ('manual');
INSERT INTO cars_transmission(name) VALUES ('robot');
INSERT INTO cars_transmission(name) VALUES ('variable speed drive');


CREATE TABLE cars_engine_type(
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_engine_type_pk PRIMARY KEY (id)
);
INSERT INTO cars_engine_type(name) VALUES ('petrol');
INSERT INTO cars_engine_type(name) VALUES ('diesel');
INSERT INTO cars_engine_type(name) VALUES ('gas');


CREATE TABLE cars_drive_unit(
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_drive_unit_pk PRIMARY KEY (id)
);
INSERT INTO cars_drive_unit(name) VALUES ('front');
INSERT INTO cars_drive_unit(name) VALUES ('rear');
INSERT INTO cars_drive_unit(name) VALUES ('full');


CREATE TABLE cars_heating (
   id            SERIAL,
   name          CHARACTER VARYING(100),
CONSTRAINT cars_heating_pk PRIMARY KEY (id)
);
INSERT INTO cars_heating (name) VALUES ('front seats');
INSERT INTO cars_heating (name) VALUES ('mirrors');
INSERT INTO cars_heating (name) VALUES ('rear window');
INSERT INTO cars_heating (name) VALUES ('steering wheel');

CREATE TABLE cars_photos (
   id              SERIAL,
   ad_id           INTEGER,
CONSTRAINT cars_photos_pk PRIMARY KEY (id) --REFERENCES cars_ad(id)
);

INSERT INTO cars_photos(id) VALUES(0);


CREATE TABLE cars_ad (
   id                    SERIAL,
   cars_brand_id         INTEGER REFERENCES cars_brand(id),
   cars_model_id         INTEGER REFERENCES cars_model(id),
   cars_body_type_id     INTEGER REFERENCES cars_body_type(id),
   cars_transmission_id  INTEGER REFERENCES cars_transmission(id),
   cars_engine_type_id   INTEGER REFERENCES cars_engine_type(id),
   cars_drive_unit_id    INTEGER REFERENCES cars_drive_unit(id),
   mileage               INTEGER,
   description           CHARACTER VARYING(200),
   user_id               INTEGER REFERENCES customer(id),
   photo_id              INTEGER REFERENCES cars_photos DEFAULT 0,
   status                CHARACTER VARYING(5) DEFAULT 'Y',         -- Y - for sale, N - NOT for sale. Can be M - moderator, H - hold, VIP etc.
   inserted_date         TIMESTAMP,
    CONSTRAINT cars_ad_pk PRIMARY KEY (id)
);
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,1,2,1,1,1,1000 ,'Descr-1', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,2,5,2,2,2,20000,'Descr-2', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (1,3,4,3,3,3,30000,'Descr-3', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,4,1,4,1,1,40000,'Descr-4', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,5,4,1,2,3,50000,'Descr-5', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (2,6,4,2,1,3,60000,'Descr-6', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,7,1,3,2,1,70000,'Descr-7', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,8,2,4,3,2,80000,'Descr-8', 1, 0, CURRENT_TIMESTAMP(0));
INSERT INTO cars_ad(cars_brand_id, cars_model_id, cars_body_type_id, cars_transmission_id, cars_engine_type_id, cars_drive_unit_id, mileage, description, user_id, photo_id, inserted_date) VALUES (3,9,3,1,3,2,90000,'Descr-9', 1, 0, CURRENT_TIMESTAMP(0));


CREATE TABLE cars_ad_heating_details (
    id              SERIAL,
    add_id          INTEGER REFERENCES cars_ad(id),
    cars_heating_id INTEGER REFERENCES cars_heating(id),
    CONSTRAINT cars_ad_heating_details_pk PRIMARY KEY (id)
);

INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (1, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (2, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (2, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (3, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (3, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (3, 4);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (4, 4);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (5, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (5, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (5, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (6, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (6, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (7, 3);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (8, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (8, 2);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (8, 4);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (9, 1);
INSERT INTO cars_ad_heating_details (add_id, cars_heating_id) VALUES (9, 4);





CREATE TABLE instructor (
    id          SERIAL,
    first_name  character varying(250) NOT NULL,
    last_name   character varying(250) NOT NULL,
    email       character varying(250) NOT NULL,
    CONSTRAINT instructor_pk PRIMARY KEY(id),
    CONSTRAINT instructor_email_uniqe UNIQUE (email)
);

INSERT INTO instructor (first_name, last_name, email) VALUES ('Alex',  'pupkin', 'Alex@mail.ru');
INSERT INTO instructor (first_name, last_name, email) VALUES ('Ivan',  'Mamkin', 'Ivan@mail.ru');
INSERT INTO instructor (first_name, last_name, email) VALUES ('Pasha', 'Sobin',  'Pash@mail.ru');

CREATE TABLE course (
    id          SERIAL,
    title  character varying(250) NOT NULL,
    instructor_id  INTEGER REFERENCES instructor(id)
);

INSERT INTO course(title, instructor_id) VALUES('Mathematics', 1);
INSERT INTO course(title, instructor_id) VALUES('Phisics', 2);
INSERT INTO course(title, instructor_id) VALUES('English', 2);
INSERT INTO course(title, instructor_id) VALUES('German', 3);


CREATE TABLE employee (
    id          SERIAL,
    first_name  character varying(250) NOT NULL,
    last_name   character varying(250) NOT NULL,
    department  character varying(250) NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY(id)
);

INSERT INTO employee(first_name, last_name, department) VALUES('Alex',  'pupkin', 'Alex@mail.ru');
INSERT INTO employee(first_name, last_name, department) VALUES('Ivan',  'Mamkin', 'Ivan@mail.ru');
INSERT INTO employee(first_name, last_name, department) VALUES('Pasha', 'Sobin',  'Pash@mail.ru');

CREATE TABLE account (
    acct_id     SERIAL,
    acct_no     character varying(250) NOT NULL,
    emp_id      INTEGER REFERENCES employee(id),
    CONSTRAINT account_pk PRIMARY KEY(acct_id),
    CONSTRAINT account_no_uniqe UNIQUE (acct_no)
) ;

INSERT INTO account(acct_no, emp_id) VALUES('123456', 1);
INSERT INTO account(acct_no, emp_id) VALUES('234567', 2);
INSERT INTO account(acct_no, emp_id) VALUES('345678', 2);
INSERT INTO account(acct_no, emp_id) VALUES('456789', 3);



CREATE TABLE company (
    id          SERIAL,
    name        character varying(250) NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY(id)
);

INSERT INTO company(name) VALUES('Adidas');
INSERT INTO company(name) VALUES('Apple');
INSERT INTO company(name) VALUES('Loblaws');

CREATE TABLE product (
    id          SERIAL,
    name        character varying(250) NOT NULL,
    company_id  INTEGER REFERENCES company(id),
    CONSTRAINT product_pk PRIMARY KEY(id)
) ;

INSERT INTO product(name, company_id) VALUES('Sneakers', 1);
INSERT INTO product(name, company_id) VALUES('Slates', 1);
INSERT INTO product(name, company_id) VALUES('Laptop', 2);
INSERT INTO product(name, company_id) VALUES('Phone', 2);
INSERT INTO product(name, company_id) VALUES('Orange', 3);
INSERT INTO product(name, company_id) VALUES('Strawberry', 3);
