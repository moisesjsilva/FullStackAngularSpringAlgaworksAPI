create table contato_email(
     id bigint(20) primary key auto_increment,
     email varchar(40) not null,
     nome varchar(30),
     departamento varchar(60) not null

)engine=InnoDB default charset=utf8;	
	

insert into 
       contato_email()
values
     (null,"jsilva.moises@gmail.com","Moisés Silva","default"),
     (null,"moises.juvenal@gmail.com","Moisés Silva","default"),
     (null,"development.pdd@gmail.com","Moisés Silva","default"),
     (null,"cc.jsilva.moises@gmail.com","Moisés Silva","default");
     