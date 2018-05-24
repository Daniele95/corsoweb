drop table statistica;

create table statistica(
id int,
scelta varchar2( 30 ) not null,
voti int not null,
constraint p_idmusica primary key( id ) );

-- valore, nome, voto
insert into statistica values( 1, 'Rock', 0 );
insert into statistica values( 2, 'Ambient', 0 );
insert into statistica values( 3, 'Progressive', 0 );
insert into statistica values( 4, 'Pop', 0 );
insert into statistica values( 5, 'Nessuno', 0 );