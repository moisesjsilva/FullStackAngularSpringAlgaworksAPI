create table amazon_app(
    id bigint(20) primary key auto_increment,
    aws_access_key_id varchar(60) not null,
    aws_secret_key varchar(60) not null,
    aws_bucket varchar(60) not null
) engine=InnoDB default charset=utf8;