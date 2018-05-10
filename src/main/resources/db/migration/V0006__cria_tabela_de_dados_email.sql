create table email(
    id bigint(20) primary key auto_increment,
    host varchar(50) not null,
    port integer not null,
    username varchar(60) not null unique,
    password varchar(255) not null,
    tag varchar(10) not null unique

)engine=InnoDB default charset=utf8;


insert into email() values(null,"smtp.gmail.com",587,"seuemail@gmail.com","suasenha@gmail.com","default");