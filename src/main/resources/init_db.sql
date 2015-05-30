CREATE DATABASE MyProject;

CREATE TABLE users (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  fullname VARCHAR (30),
  email VARCHAR (30) NOT NULL UNIQUE,
  pass VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);