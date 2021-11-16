CREATE DATABASE databasename;

CREATE TABLE users (
  UserID int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(50) NOT NULL,
  PRIMARY KEY (UserID)
);

CREATE TABLE messages (
  MessageID int(11) NOT NULL AUTO_INCREMENT,
  Content varchar(50) NOT NULL,
  UserID int(11) NOT NULL,
  PRIMARY KEY (MessageID),
  FOREIGN KEY (UserID) REFERENCES `users` (`UserID`)
);

