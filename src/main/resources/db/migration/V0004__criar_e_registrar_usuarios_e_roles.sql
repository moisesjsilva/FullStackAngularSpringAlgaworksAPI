create table perfil(
    id bigint(20) auto_increment primary key,
    nome varchar(30) not null,
    sigla varchar(3) not null unique
)engine=InnoDB default charset=utf8;

insert into 
       perfil() 
values
     (null,"Administrador","ADM"),
     (null,"Conferente","CFT"),
     (null,"Gerente","GRT"),
     (null,"Faturista","FAT");
     
create table role(
     id bigint(20) auto_increment primary key,
     role varchar(50) not null
)engine=InnoDB default charset=utf8;

insert into 
       role()
values
     (null,"ROLE_ADMIN"),
     (null,"ROLE_CATEGORIA_CREATE"),
     (null,"ROLE_CATEGORIA_UPDATE"),
     (null,"ROLE_CATEGORIA_DELETE"),
     (null,"ROLE_CATEGORIA_LIST"),
     (null,"ROLE_ENDERECO_CREATE"),
     (null,"ROLE_ENDERECO_UPDATE"),
     (null,"ROLE_ENDERECO_DELETE"),
     (null,"ROLE_ENDERECO_LIST"),
     (null,"ROLE_LANCAMENTO_CREATE"),
     (null,"ROLE_LANCAMENTO_UPDATE"),
     (null,"ROLE_LANCAMENTO_DELETE"),
     (null,"ROLE_LANCAMENTO_LIST"),
     (null,"ROLE_PESSOA_CREATE"),
     (null,"ROLE_PESSOA_UPDATE"),
     (null,"ROLE_PESSOA_DELETE"),
     (null,"ROLE_PESSOA_LIST");
     
create table usuario(
     id bigint(20) auto_increment primary key,
     username varchar(100) not null unique,
     password varchar(255) not null,
     enable boolean default true,
     perfil_id bigint(20) not null,
     constraint foreign key (perfil_id) references perfil(id)

)engine=InnoDB default charset=utf8;   
 
create table perfil_role(
     id bigint(20) auto_increment primary key,
     role_id bigint(20) not null,
     perfil_id bigint(20) not null,
     constraint foreign key (role_id) references role(id),
     constraint foreign key (perfil_id) references perfil(id)     
)engine=InnoDB default charset=utf8;
     
insert into 
       usuario()
values
     (null,"jsilva.moises@gmail.com","$2a$10$ByJWNf8J1x/itV6NbAxxquuXbKsZtpktVXaEsHcvYW46jpF/XPeT.",true,1),
     (null,"admin","$2a$10$ByJWNf8J1x/itV6NbAxxquuXbKsZtpktVXaEsHcvYW46jpF/XPeT.",true,1),
     (null,"andre","$2a$10$ByJWNf8J1x/itV6NbAxxquuXbKsZtpktVXaEsHcvYW46jpF/XPeT.",true,2),
     (null,"maria","$2a$10$ByJWNf8J1x/itV6NbAxxquuXbKsZtpktVXaEsHcvYW46jpF/XPeT.",true,3),
     (null,"sandra","$2a$10$ByJWNf8J1x/itV6NbAxxquuXbKsZtpktVXaEsHcvYW46jpF/XPeT.",true,4),
     (null,"cosme","$2a$10$ByJWNf8J1x/itV6NbAxxquuXbKsZtpktVXaEsHcvYW46jpF/XPeT.",true,4);
     
insert into perfil_role()
values(null,1,1),(null,2,2),(null,3,2),(null,6,2),(null,7,2),(null,2,3),(null,5,3),(null,10,3);
     
     
     
     
     
     