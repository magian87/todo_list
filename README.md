# todo_list
todo_list

CREATE DATABASE  todo

CREATE TABLE task
(
id          bigint generated by default as identity primary key,
description varchar(100) NOT NULL,
status      int NOT NULL
);