-- Users of the application
CREATE TABLE users (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username     VARCHAR(50) NOT NULL UNIQUE,
    email        VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role         VARCHAR(20) NOT NULL DEFAULT 'USER',
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Books represent the products in the store
CREATE TABLE books (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title        VARCHAR(150) NOT NULL,
    author       VARCHAR(100) NOT NULL,
    isbn         VARCHAR(20) NOT NULL UNIQUE,
    course       VARCHAR(50),
    subject      VARCHAR(80),
    price        DECIMAL(10,2) NOT NULL CHECK (price > 0),
    stock        INT NOT NULL CHECK (stock >= 0),
    description  VARCHAR(1000),
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Simple admin view support later
CREATE INDEX idx_books_subject  ON books(subject);
CREATE INDEX idx_books_course   ON books(course);
