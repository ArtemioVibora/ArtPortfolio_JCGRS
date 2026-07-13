CREATE TABLE user (
	user_id         INT PRIMARY KEY AUTO_INCREMENT,
	username        VARCHAR(30) UNIQUE NOT NULL,
	email           VARCHAR(100) UNIQUE NOT NULL,
	password_hash   VARCHAR(255) NOT NULL,
	bio             TEXT,
	created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);