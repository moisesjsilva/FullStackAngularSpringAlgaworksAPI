create table pessoa(
    id bigint(20) primary key auto_increment,
    nome varchar(120) not null,
    ativo boolean default true,
    logradouro varchar(150) not null,
    numero varchar(20) not null,
    complemento varchar(50),
    bairro varchar(120) not null,
    cep varchar(10) not null,
    cidade varchar(180) not null,
    estado varchar(50) not null
)engine=InnoDB default charset=utf8;

insert 
     into pessoa()
values(null,'Moisés Americo da Silva',true,'Av. Bertioga','112','Casa','Jardim América IV','13.228-120','Várzea Paulista','São Paulo'),
      (null,'Andre Pacheco Rosa',true,'Rua Beltrão Coutinho','97','Casa','Jardim América IV','13.225-450','Várzea Paulista','São Paulo'),
      (null,'Maria de Fátima Cavalcante',true,'Rua Santiago','128','Casa','Jardim América IV','13.223-388','Várzea Paulista','São Paulo'),
      (null,'Sandra Adriana da Silva',true,'Rua Malibu','88','Casa','Jardim América IV','13.227-310','Várzea Paulista','São Paulo'),
      (null,'Amauri Pedroso',true,'Av. Bertioga','112','Casa','Jardim América IV','13.228-120','Várzea Paulista','São Paulo'),
      (null,'Cornelio Corno',true,'Rua Beltrão Coutinho','97','Casa','Jardim América IV','13.225-450','Várzea Paulista','São Paulo'),
      (null,'Miraculo Pedro',true,'Rua Santiago','128','Casa','Jardim América IV','13.223-388','Várzea Paulista','São Paulo'),
      (null,'Santinha Bertonho Cristina',true,'Rua Malibu','88','Casa','Jardim América IV','13.227-310','Várzea Paulista','São Paulo'),
      (null,'Saulo de Almeida',true,'Av. Bertioga','112','Casa','Jardim América IV','13.228-120','Várzea Paulista','São Paulo'),
      (null,'Noventino Decimo',true,'Rua Beltrão Coutinho','97','Casa','Jardim América IV','13.225-450','Várzea Paulista','São Paulo'),
      (null,'Ciro Libanes',true,'Rua Santiago','128','Casa','Jardim América IV','13.223-388','Várzea Paulista','São Paulo'),
      (null,'Yanna Yongk',true,'Rua Malibu','88','Casa','Jardim América IV','13.227-310','Várzea Paulista','São Paulo'),
      (null,'Isabele Martiins',true,'Av. Bertioga','112','Casa','Jardim América IV','13.228-120','Várzea Paulista','São Paulo'),
      (null,'Paulo Adamastor',true,'Rua Beltrão Coutinho','97','Casa','Jardim América IV','13.225-450','Várzea Paulista','São Paulo'),
      (null,'Isaque Mendonça',true,'Rua Santiago','128','Casa','Jardim América IV','13.223-388','Várzea Paulista','São Paulo'),
      (null,'Maria Cristina',true,'Rua Malibu','88','Casa','Jardim América IV','13.227-310','Várzea Paulista','São Paulo');