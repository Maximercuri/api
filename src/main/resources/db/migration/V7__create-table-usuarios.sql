CREATE TABLE usuarios (
    "id" SERIAL PRIMARY KEY,
    "login" VARCHAR(100) NOT NULL UNIQUE,
    "contraseña" VARCHAR(300) NOT NULL
);