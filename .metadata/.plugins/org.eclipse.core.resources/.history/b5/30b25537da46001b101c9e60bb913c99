----- Create database from the application. -----
CREATE DATABASE IF NOT EXISTS springsecuritydb;

USE springsecuritydb;

----- User credentials table. -----
CREATE TABLE USERS (
	user_id INT(50) NOT NULL,
	user_name VARCHAR(100) NOT NULL,
	password VARCHAR(50) NOT NULL,
	enabled boolean,
	PRIMARY KEY(user_id)
);

----- User roles/authorities table. -----
CREATE TABLE USERS_ROLES (
  user_role_id INT(50) NOT NULL,
  user_id INT(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  PRIMARY KEY (user_role_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
)

----- Sample users and their respective roles. -----
INSERT INTO users (user_id, user_name, password, enabled) VALUES (1, 'john', 'john123', true);
INSERT INTO users (user_id, user_name, password, enabled) VALUES (2, 'natalie', 'nat123', true);
INSERT INTO users (user_id, user_name, password, enabled) VALUES (3, 'tom', 'tom123', true);
 
INSERT INTO users_roles (user_role_id, user_id, authority) VALUES (1, 1, 'ROLE_ADMIN');
INSERT INTO users_roles (user_role_id, user_id, authority) VALUES (2, 2, 'ROLE_ADMIN');
INSERT INTO users_roles (user_role_id, user_id, authority) VALUES (3, 3, 'ROLE_ADMIN');

----- Displaying users and users_roles table data. -----
SELECT * FROM users;

SELECT * FROM users_roles;