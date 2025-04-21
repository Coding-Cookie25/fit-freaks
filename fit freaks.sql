-- -----------------------------------------------------
-- Schema for FitFreaks Gym Management System
-- -----------------------------------------------------

-- Create the database
CREATE DATABASE IF NOT EXISTS fitfreaks;
USE fitfreaks;

-- -----------------------------------------------------
-- Table: users
-- Stores user login credentials
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Sample user (for testing; change or remove in production)
INSERT INTO users (username, password) VALUES 
('admin', 'admin123'); -- Note: Plain text for demo only

-- -----------------------------------------------------
-- Table: trainers
-- Stores trainer profiles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS trainers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialty VARCHAR(100),
    experience INT
);

-- Sample trainers
INSERT INTO trainers (name, specialty, experience) VALUES 
('Raj Sharma', 'Weight Training', 5),
('Pooja Mehta', 'Yoga', 3),
('Amit Verma', 'Cardio and HIIT', 4);
