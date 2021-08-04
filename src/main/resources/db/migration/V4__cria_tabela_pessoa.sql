create table pessoa (
       dtype varchar(31) not null,
        id integer auto_increment,
        cpf varchar(255),
        data_nascimento date,
        nome varchar(255),
        crm varchar(255),
        especialidade_id integer,
        primary key (id)
    )