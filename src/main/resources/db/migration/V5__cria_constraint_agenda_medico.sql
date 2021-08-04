alter table agenda 
       add constraint fk_medico 
       foreign key (medico_id) 
       references pessoa(id)