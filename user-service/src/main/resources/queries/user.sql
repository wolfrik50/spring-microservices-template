CREATE DATABASE IF NOT EXISTS review_system_db;
USE review_system_db;

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    about TEXT
);

