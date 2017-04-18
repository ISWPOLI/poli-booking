DROP DATABASE polibooking;
DROP USER polibooking;

CREATE USER polibooking WITH PASSWORD 'polibooking';
CREATE DATABASE polibooking;
GRANT ALL PRIVILEGES ON DATABASE polibooking TO polibooking;
ALTER DATABASE postgres SET TIMEZONE TO 'America/Bogota';