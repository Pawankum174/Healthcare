-- V3__add_admin_doctor_nurse.sql

-- Insert predefined roles
INSERT INTO roles (name) VALUES 
    ('ADMIN'),
    ('DOCTOR'),
    ('NURSE');

-- Example admin user
INSERT INTO users (username, password, role) VALUES
    ('admin1', '$2a$10$hashedPasswordHere', 'ADMIN');

-- Example doctor user
INSERT INTO users (username, password, role) VALUES
    ('doctor1', '$2a$10$hashedPasswordHere', 'DOCTOR');

-- Example nurse user
INSERT INTO users (username, password, role) VALUES
    ('nurse1', '$2a$10$hashedPasswordHere', 'NURSE');