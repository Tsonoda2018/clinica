    create table agenda (
       data_livre date not null,
        id integer not null,
        horario_fim time,
        horario_inicio time,
        medico_id integer not null,
        paciente_id integer,
        primary key (data_livre, id, medico_id)
    )