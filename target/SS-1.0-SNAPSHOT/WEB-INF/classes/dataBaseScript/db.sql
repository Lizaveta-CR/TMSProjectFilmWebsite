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
    link        varchar(60) NOT NULL,
    name        varchar(60) NOT NULL,
    year        varchar(10) NOT NULL,
    quality     varchar(30) NOT NULL,
    translation varchar(40) NOT NULL,
    continuance varchar(30) NOT NULL,
    date        varchar(20) NOT NULL,
    price       varchar(10) NOT NULL,
    PRIMARY KEY (film_id)
);

CREATE table orders
(
    order_id int(11)      NOT NULL AUTO_INCREMENT,
    username varchar(45)  NOT NULL,
    price    double(5, 2) NOT NULL,
    date     TIMESTAMP(6) NOT NULL,
    PRIMARY KEY (order_id),
    UNIQUE KEY uni_username_order (order_id, username),
    KEY fk_username_idx_order (username),
    CONSTRAINT fk_username_order FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE order_item
(
    order_id int(11) NOT NULL,
    film_id  int(11) NOT NULL,
    PRIMARY KEY (order_id, film_id),
    KEY film_id (film_id),
    CONSTRAINT order_film_ibfk_1
        FOREIGN KEY (order_id) REFERENCES orders (order_id),
    CONSTRAINT order_film_ibfk_2
        FOREIGN KEY (film_id) REFERENCES films (film_id)
);

INSERT INTO users(username, password, mobile, enabled)
VALUES ('liza', '1234', '1234567890', true);
INSERT INTO users(username, password, mobile, enabled)

INSERT INTO user_roles (username, role)
VALUES ('liza', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('liza', 'ROLE_ADMIN');

ALTER TABLE orders
    AUTO_INCREMENT = 1;
# drop table user_roles

# drop table users

# drop table films;

# drop table order_item;

# drop table orders

