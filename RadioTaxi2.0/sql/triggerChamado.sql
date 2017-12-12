-- triggers --

-- Trigger Log de Chamado

create table logChamado(
	login varchar(20), 
	data varchar(11), 
	Hora varchar(11),
	operacao varchar2(20),
	num_boleto number(11),
	cod_conveniada number(5)
);

create or replace trigger TRG_AFT_INS_UPD_DEL_CHAMADO
after insert or update or delete on chamado
for each row
begin
	if inserting then
		insert into logChamado (login, data, Hora, operacao, num_boleto, cod_conveniada)
        select user, sysdate as data, to_char(sysdate, 'hh24:mi:ss') as "Time", 'insert', :new.num_boleto, :new.cod_conveniada from dual;
	end if;
	
	if deleting then
		insert into logChamado (login, data, Hora, operacao, num_boleto, cod_conveniada)
        select user, sysdate as data, to_char(sysdate, 'hh24:mi:ss') as "Time", 'delete', :old.num_boleto, :old.cod_conveniada from dual;
	end if;
		
	if updating then
		insert into logChamado (login, data, Hora, operacao, num_boleto, cod_conveniada)
        select user, sysdate as data, to_char(sysdate, 'hh24:mi:ss') as "Time", 'update', :new.num_boleto, :new.cod_conveniada from dual;
	end if;
	
end TRG_AFT_INS_UPD_DEL_CHAMADO;

