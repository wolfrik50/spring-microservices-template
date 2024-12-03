CREATE DATABASE IF NOT EXISTS review_system_db;
USE review_system_db;

CREATE TABLE IF NOT EXISTS users (
    u_id VARCHAR(255) PRIMARY KEY,
    u_name VARCHAR(255) NOT NULL,
    u_email VARCHAR(255) NOT NULL,
    u_about TEXT
);

