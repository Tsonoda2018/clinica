alter table agenda 
       add constraint fk_paciente 
       foreign key (paciente_id) 
       references pessoa(id);
    
    alter table endereco 
       add constraint fk_endereco 
       foreign key (pessoa_id) 
       references pessoa(id);
    
    alter table pessoa 
       add constraint fk_especialidade 
       foreign key (especialidade_id) 
       references especialidade(id);