DROP TABLE IF EXISTS students;
CREATE TABLE IF NOT EXISTS students (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT ,
                                        name TEXT,
                                        age INTEGER,
                                        phone TEXT,
                                        email TEXT
);

INSERT INTO students (name, age, phone, email)
VALUES ('alex', 35, '010-1234-5678', 'alex@gmail.com');

INSERT INTO students (name, age, phone, email)
VALUES ('brad', 35, '010-1234-5678', 'brad@gmail.com');

INSERT INTO students (name, age, phone, email)
VALUES ('chad', 35, '010-1234-5678', 'chad@gmail.com');