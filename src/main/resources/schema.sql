create table inquiry
(
    id int not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    contents varchar(500) not null,
    created datetime not null,
    primary key(id)
);
