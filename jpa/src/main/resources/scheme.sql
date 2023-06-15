DROP TABLE IF EXISTS students;

CREATE TABLE students
(
    age   integer,
    id    integer,
    email varchar(255),
    name  varchar(255),
    phone varchar(255),
    primary key (id)
);