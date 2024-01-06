-- Create the schema
CREATE SCHEMA y_database;

-- Use the created schema
USE y_database;

-- Create the users table
CREATE TABLE users (
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

-- Create the posts table
CREATE TABLE POSTS (
    POST_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TITLE VARCHAR(255),
    TEXT TEXT,
    AUTHOR_ID BIGINT,
    AUTHOR_USERNAME VARCHAR(255),
    FOREIGN KEY (AUTHOR_ID) REFERENCES USERS(ID)
);

-- Create the follows table
CREATE TABLE follows (
    follower_id BIGINT(20),
    followed_id BIGINT(20),
    PRIMARY KEY (follower_id, followed_id),
    FOREIGN KEY (follower_id) REFERENCES users(id),
    FOREIGN KEY (followed_id) REFERENCES users(id)
);
