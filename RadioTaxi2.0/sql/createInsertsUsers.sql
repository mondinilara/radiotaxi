drop table estado cascade constraints;
drop table bairro cascade constraints;
drop table cidade cascade constraints;
drop table cep cascade constraints;
drop table marca cascade constraints;
drop table modelo cascade constraints;
drop table pessoa cascade constraints;
drop table pessoa_fisica cascade constraints;
drop table veiculo cascade constraints;
drop table chamado cascade constraints;
drop table chamado_detalhe cascade constraints;
drop table estado_civil cascade constraints;
drop table motorista cascade constraints;
drop sequence num_Chamado_seq;

create table ESTADO(
	nom_UF char(2) primary key,
	nom_estado varchar2(30)
);

create table CIDADE(
	num_municipio number(5),
	nom_UF char(2),
	nom_municipio varchar2(50),
	primary key (num_municipio, nom_UF),
	foreign key (nom_UF) references ESTADO
);

create table BAIRRO(
	num_municipio number(5),
	nom_UF char(2),
        nom_bairro varchar2(50),
	primary key(num_municipio, nom_UF)
);

create table CEP(
	num_municipio number(5),
        num_CEP number(8),
	nom_UF char(2),
	nom_logradouro varchar2(60),
	primary key (num_municipio, num_CEP, nom_UF),
	foreign key (num_municipio, nom_UF) references BAIRRO(num_municipio, nom_UF)
);

create table MARCA(
	cod_marca_veiculo number(4) primary key,
	nom_marca_veiculo varchar2(30)
);

create table MODELO(
	cod_modelo_veiculo number(4),
	cod_marca_veiculo number(4),
	cod_tipo_veiculo number(4),
	nom_modelo_veiculo varchar2(30),
	primary key (cod_marca_veiculo, cod_tipo_veiculo, cod_modelo_veiculo),
	foreign key (cod_marca_veiculo) references marca
);

create table PESSOA(
	num_pessoa number(5) primary key,
	nom_email varchar2(60)
);

Create table VEICULO(
	num_veiculo number(4) primary key,
	cod_marca_veiculo number(4),
	cod_tipo_veiculo number(4),
	cod_modelo_veiculo number(4),
	num_prefixo_taxi number(6),
	num_placa varchar2(7),
	num_chassi varchar2(21),
	nom_proprietario varchar2(60),
	num_ano_fabricacao number(2),
	num_ano_modelo number(2),
	desc_cor varchar2(20),
	num_pessoa number(5),
	foreign key (num_pessoa) references PESSOA,
	foreign key (cod_marca_veiculo, cod_tipo_veiculo, cod_modelo_veiculo) references MODELO (cod_marca_veiculo, cod_tipo_veiculo, cod_modelo_veiculo),
	foreign key (num_pessoa) references pessoa
);

create table ESTADO_CIVIL(
	cod_estado_civil number(1) primary key,
	des_estado_civil varchar2(20)
);

create table PESSOA_FISICA(
	num_pessoa_pf number(5) primary key,
	nom_Pessoa varchar2(60),
	num_Cpf number(11),
	num_documento_identidade varchar2(20),
	num_orgao_emisor_docto_identil varchar2(60),
	dat_nascimento date,
	idt_sexo char(1),
	cod_estado_civil number(1) references ESTADO_CIVIL,
	foreign key (num_pessoa_pf) references PESSOA(num_pessoa)
);

create table Motorista(
	num_pessoa_pf number(5),
	num_carteira_habilitacao number(12),
	idt_tipo_categoria_habilitacao char(3),
	dat_validade_habilitacao date,
	dat_primeira_habilitacao date,
	num_conta_bancaria number,
	num_banco number,
	num_agencia number,
	foreign key (num_pessoa_pf) references PESSOA_FISICA
);

Create table CHAMADO(
	cod_conveniada number(5),
	num_boleto number(11),
	num_chamado number(5),
	dat_abertura_chamado date,
	dat_agenda_corrida date,
	num_pessoa_atendente number(5),
	num_veiculo number(4),
	cod_centro_custo number(5),
	num_contato varchar2(40),
	num_tel_DDI_contato number(3),
	num_tel_DDD_contato number(3),
	num_tel_contato number(9),
	primary key (cod_conveniada, num_boleto),
	foreign key (num_veiculo) references VEICULO,
	foreign key (num_pessoa_atendente) references pessoa_fisica(num_pessoa_pf)
);

create table CHAMADO_DETALHE(
	num_Chamado_seq number(5),
	cod_conveniada number(5),
	num_boleto number(5),
	num_CEP number(8),
	des_localizacao varchar2(4000),
	idt_origem_destino number(5),
	num_municipio number(5),
	nom_UF char(2),
	primary key(num_Chamado_seq, cod_conveniada, num_boleto),
	foreign key (cod_conveniada, num_boleto) references CHAMADO(cod_conveniada, num_boleto),
	foreign key (num_CEP, num_municipio, nom_UF) references CEP(num_CEP, num_municipio, nom_UF)
);

create sequence num_Chamado_seq start with 1;

--inserts---
insert into estado values ('MG', 'Minas Gerais');
insert into estado values ('SP', 'Sao Paulo');
insert into estado values ('RJ', 'Rio de Janeiro');

insert into cidade values (1, 'MG', 'Uberlandia');
insert into cidade values (2, 'MG', 'Uberaba');
insert into cidade values (3, 'MG', 'Ouro Preto');

insert into bairro values (1, 'MG', 'Fundinho');
insert into bairro values (2, 'MG', 'Tabajaras');
insert into bairro values (3, 'MG', 'Santa Monica');

insert into cep values (1, 38400234, 'MG', 'Goncalves dias');
insert into cep values (1, 38408946, 'MG', 'Joao Naves de Avila');
insert into cep values (1, 38408240, 'MG', 'Joao Catanduva');

insert into marca values (1, 'FIAT');
insert into marca values (2, 'BMW');
insert into marca values (3, 'RENAULT');
insert into marca values (4, 'CITROEN');

insert into modelo values (1, 1, 1, 'mobi');
insert into modelo values (2, 1, 1, 'palio');
insert into modelo values (3, 2, 2, 'x6');
insert into modelo values (4, 3, 1, 'sandero');
insert into modelo values (5, 4, 1, 'c3');
insert into modelo values (6, 4, 2, 'c4');

insert into pessoa values (1, 'lara.mondini@hotmail.com');
insert into pessoa values (2, 'leandro-EBC@hotmail.com');
insert into pessoa values (3, 'humberto.razente@gmail.com');

insert into veiculo values (1, 1, 1, 1, 32, 12, 123, 'Leandro', 14, 03, 'branco', 2);
insert into veiculo values (2, 2, 2, 3, 99, 56, 789, 'Humberto', 16, 09, 'branco', 3);
insert into veiculo values (3, 4, 1, 5, 23, 93, 456, 'Lara', 20, 10, 'preto', 1);

insert into estado_civil values (1, 'solteiro');
insert into estado_civil values (2, 'viuvo');
insert into estado_civil values (3, 'casado');

insert into pessoa_fisica values (1, 'danielle', 12555728491, 111, SSPMG, '01/02/1999', 'f', 1);
insert into pessoa_fisica values (2, 'jean', 12547639102, 653, PCMG, '31/10/1997', 'm', 1);
insert into pessoa_fisica values (3, 'alex', 84736273847, 879, SSPMG, '10/10/1996', 'm', 1);

insert into motorista values (1, 763827453891, B, 20/10/2019, 20/04/2002, 5678, 31, 45);
insert into motorista values (2, 736452900938, B, 05/12/2020, 31/05/2003, 5674, 44, 90);
insert into motorista values (3, 465722238957, B, 08/10/2018, 02/07/1997, 3744, 77, 65);

insert into chamado values (1, 1, 4456, '03/12/2017', '10/12/2017', 1, 1, 1001, 'Beatriz', 55, 34, 999793291);
--insert into chamado values (2, 2, 2356, '10/11/2017', '10/11/2017', 1, 1, 1001, 'Zuleica', 55, 34, 998664256);
--insert into chamado values (3, 3, 4478, '23/12/2017', '24/12/2017', 1, 1, 1001, 'Giovana', 55, 34, 996553593);

--insert into chamado_detalhe values (NUM_CHAMADO_SEQ.nextVal, 1, 1, 38400234, 'udi', 1, 1, 'MG');
--insert into chamado_detalhe values (NUM_CHAMADO_SEQ.nextVal, 2, 2, 38408946, 'udi', 1, 1, 'MG');
--insert into chamado_detalhe values (NUM_CHAMADO_SEQ.nextVal, 3, 3, 38408240, 'udi', 1, 1, 'MG');

--select num_chamado_seq.currval from dual;

commit;

--ATENDENTE 
create user atendenteUser IDENTIFIED BY oracle default tablespace users temporary tablespace temp quota 100m on users;

create role atendente;
grant select on num_Chamado_seq to atendente;	
grant create session to atendenteUser;
grant insert, update on chamado to atendente;
grant insert, update on chamado_detalhe to atendente;

--seleciona dados tabela motorista, exceto numero da conta e nro da agencia
create or replace view motoristaView as
select m.num_pessoa_pf, m.num_carteira_habilitacao, 
m.idt_tipo_categoria_habilitacao,
m.dat_validade_habilitacao,
m.dat_primeira_habilitacao,
m.num_banco
from motorista m;

grant select on motoristaView to atendente;
grant select on estado to atendente;
grant select on bairro to atendente;
grant select on cidade to atendente;
grant select on cep to atendente;
grant select on marca to atendente;
grant select on modelo to atendente;
grant select on pessoa to atendente;
grant select on veiculo to atendente;
grant select on chamado to atendente;
grant select on chamado_detalhe to atendente;
grant select on estado_civil to atendente;
grant select on pessoa_fisica to atendente;

grant atendente to atendenteUser;

--GERENTE
create user gerenteUser IDENTIFIED BY oracle default tablespace users temporary tablespace temp quota 100m on users;

create role gerente;

declare
    cursor c1 is select table_name from user_tables;
    cmd varchar2(200);
begin
    for c in c1 loop
        cmd := 'GRANT SELECT, UPDATE, DELETE, INSERT ON '||c.table_name||' TO GERENTE';
        execute immediate cmd;
    end loop;
end;

grant create session to gerenteUser;
grant select on num_Chamado_seq to gerente;	
grant gerente to gerenteUser;
