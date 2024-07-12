DROP TABLE consultas;
CREATE TABLE consultas (
    "id" bigserial PRIMARY KEY,
    "medico_id" bigint not null,
    "paciente_id" bigint not null,
    "horario" TIMESTAMP NOT NULL,

    constraint "fk_consultas_medico_id" foreign key ("medico_id") references medicos("id"),
    constraint "fk_consultas_paciente_id" foreign key ("paciente_id") references pacientes("id")
);
