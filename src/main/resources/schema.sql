CREATE TABLE class (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE,
    email VARCHAR(255),
    active BOOLEAN,
    student_class_id BIGINT,
    FOREIGN KEY (student_class_id) REFERENCES class(id)
);
