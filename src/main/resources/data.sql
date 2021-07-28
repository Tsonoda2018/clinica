insert into especialidade(nome) values ('Geriatria');
insert into especialidade(nome) values ('Oftalmologia');
insert into especialidade(nome) values ('Oncologia');
insert into especialidade(nome) values ('Dermatologia');
insert into especialidade(nome) values ('Infectologia');

insert into pessoa (dtype, cpf, data_nascimento, nome, crm, especialidade_id) values ('Medico','120', '1998-05-20','AARAO ANDRADE NAPOLEAO','194528', 2);
insert into pessoa (dtype, cpf, data_nascimento, nome, crm, especialidade_id) values ('Medico','121', '1965-06-21','ABDALA JORGE LAUAND NETO','61071', 1);
insert into pessoa (dtype, cpf, data_nascimento, nome, crm, especialidade_id) values ('Medico','123', '1975-03-20','AABEL CARVALHO DE OLIVEIRA JUNIOR','194052', 3);
insert into pessoa (dtype, cpf, data_nascimento, nome, crm, especialidade_id) values ('Medico','124', '1999-04-10','Marcela Almeida','2203', 3);

insert into pessoa (dtype, cpf, data_nascimento, nome) values ('Paciente','123', '1982-03-20','Camila Fernandes');