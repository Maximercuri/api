CREATE TABLE medicos (
    "id" SERIAL PRIMARY KEY,
    "nombre" VARCHAR(100) NOT NULL,
    "email" VARCHAR(100) NOT NULL UNIQUE,
    "documento" VARCHAR(8) NOT NULL UNIQUE,
    "especialidad" VARCHAR(100) NOT NULL,
    "calle" VARCHAR(100) NOT NULL,
    "numero" VARCHAR(20),
    "complemento" VARCHAR(100),
    "barrio" VARCHAR(100) NOT NULL,
    "ciudad" VARCHAR(100) NOT NULL
);

