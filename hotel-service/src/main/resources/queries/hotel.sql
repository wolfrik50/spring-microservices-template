DROP DATABASE IF EXISTS review_system_db;



CREATE DATABASE review_system_db
WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE ROLE wulf
WITH
    LOGIN
    NOSUPERUSER
    INHERIT
    NOCREATEDB
    NOCREATEROLE
    NOREPLICATION
    NOBYPASSRLS
    ENCRYPTED PASSWORD 'SCRAM-SHA-256$4096:ToTKtSIx5reJiGr0AQyq2A==$ItviCQDNuAvdwZ+2ihP4L62GfpAOuSDKINeuW/8H7U0=:d7Snq7jchp/8QVL5GIyz9jQFf3bvSKBiAzl2RpFjI4k=';

GRANT TEMPORARY, CONNECT ON DATABASE review_system_db TO PUBLIC;
GRANT ALL ON DATABASE review_system_db TO postgres;
GRANT ALL ON DATABASE review_system_db TO wulf;
GRANT ALL PRIVILEGES ON TABLE hotels TO wulf;

CREATE TABLE hotels (
    h_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    h_name VARCHAR(255) NOT NULL,
    h_location VARCHAR(255),
    h_desc TEXT
);

