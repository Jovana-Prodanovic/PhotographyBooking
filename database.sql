psql "postgresql://postgres:postgres@localhost:5432/"
CREATE USER bookinguser WITH PASSWORD 'secret';
CREATE DATABASE bookingdb OWNER bookinguser;
\q