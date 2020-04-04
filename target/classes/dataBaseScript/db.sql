CREATE TABLE users
(
    username VARCHAR(45) NOT NULL,
    password VARCHAR(60) NOT NULL,
    mobile   VARCHAR(30) NOT NULL,
    enabled  TINYINT     NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

CREATE TABLE user_roles
(
    user_role_id int(11)     NOT NULL AUTO_INCREMENT,
    username     varchar(45) NOT NULL,
    role         varchar(45) NOT NULL,
    PRIMARY KEY (user_role_id),
    UNIQUE KEY uni_username_role (role, username),
    KEY fk_username_idx (username),
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE films
(
    film_id     int(11)     NOT NULL AUTO_INCREMENT,
    name        varchar(60) NOT NULL,
    year        varchar(10) NOT NULL,
    quality     varchar(30) NOT NULL,
    translation varchar(40) NOT NULL,
    continuance varchar(30) NOT NULL,
    date        varchar(20) NOT NULL,
    PRIMARY KEY (film_id)
);

INSERT INTO users(username, password, mobile, enabled)
VALUES ('liza', '1234', '1234567890', true);
INSERT INTO users(username, password, mobile, enabled)
VALUES ('alex', '1234', '9876543210', true);

INSERT INTO user_roles (username, role)
VALUES ('liza', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('liza', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alex', 'ROLE_USER');

# drop table user_roles

# drop table users

# drop table films