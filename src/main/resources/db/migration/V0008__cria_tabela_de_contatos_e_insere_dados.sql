create table contato(
     id bigint(20) auto_increment primary key,
     nome varchar(60) not null,
     email varchar(60) not null,
     telefone varchar(80) not null,
     pessoa_id bigint(20) not null,
     constraint foreign key (pessoa_id) references pessoa(id)


)engine=InnoDB default charset=utf8;

insert into 
       contato()
values
     (null,"Moisés Juvenal da Silva","jsilva.moises@gmail.com","11 6958-6985",1);