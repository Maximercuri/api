CREATE TABLE pacientes (
    "id" SERIAL PRIMARY KEY,
    "nombre" VARCHAR(100) NOT NULL,
    "documento" VARCHAR(8) NOT NULL UNIQUE,
    "email" VARCHAR(100) NOT NULL UNIQUE,
    "telefono" varchar(20) not null,
    "especialidad" VARCHAR(100) NOT NULL,
    "calle" VARCHAR(100) NOT NULL,
    "numero" VARCHAR(20),
    "complemento" VARCHAR(100),
    "barrio" VARCHAR(100) NOT NULL,
    "ciudad" VARCHAR(100) NOT NULL
);