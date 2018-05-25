drop table utente;

create table utente(
nome varchar2(30) not null,
cognome varchar2(30) not null,
username varchar2(10),
constraint p_username primary key(username));
