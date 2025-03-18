USE jessie_community;

drop table if exists member CASCADE;
create table member (
    id BIGINT PRIMARY KEY,
    name varchar(255)
);


drop table if exists users cascade;

CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(10) NOT NULL,
    profile_img_url VARCHAR(255)
);