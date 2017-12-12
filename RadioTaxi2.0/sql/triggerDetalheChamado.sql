-- triggers --

--Trigger Log detalhes chamado
create table logDetalheChamado(login varchar2(20), data date, hora varchar(11), 
    operacao varchar2(20), num_Chamado_seq number(5), 
    cod_conveniada number(5), num_boleto number(5));
    
create or replace trigger detalhe_chamado_log
after insert or update or delete on chamado_detalhe
for each row
begin
    if inserting then
        insert into logDetalheChamado (login, data, hora, operacao, num_Chamado_seq, cod_conveniada, num_boleto)
            select user, sysdate, to_char(sysdate, 'hh24:mi:ss') as "Time", 'insert',:new.num_Chamado_seq, :new.cod_conveniada, :new.num_boleto from dual;
    end if;
    
    if deleting then
        insert into logDetalheChamado (login, data, hora, operacao, num_Chamado_seq, cod_conveniada, num_boleto)
            select user, sysdate, to_char(sysdate, 'hh24:mi:ss') as "Time", 'delete',:old.num_Chamado_seq, :old.cod_conveniada, :old.num_boleto from dual;
    end if;
    
    if updating then
        insert into logDetalheChamado (login, data, hora, operacao, num_Chamado_seq, cod_conveniada, num_boleto)
            select user, sysdate, to_char(sysdate, 'hh24:mi:ss') as "Time", 'update',:new.num_Chamado_seq, :new.cod_conveniada, :new.num_boleto from dual;
    end if;
end;
