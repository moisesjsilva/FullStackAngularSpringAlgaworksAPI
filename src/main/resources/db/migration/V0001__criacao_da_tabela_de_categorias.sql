create table categoria(
      id bigint(20) primary key auto_increment,
      nome varchar(50) not null
)engine=InnoDB default charset=utf8;

insert into 
	categoria(nome) 
values
	('Lazer'),
	('Alimentação'),
	('Supermercado'),
	('Farmácia'),
	('Outros');