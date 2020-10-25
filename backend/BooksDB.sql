Drop database if exists librery;
Create database librery;
use librery;

create table books (
id int primary key auto_increment,
author varchar(45) not null,
title varchar(25) not null,
category varchar(20) not null,
descri varchar (500) not null
);

insert into books values (1,"Juan Bartol López",
"Los pájaros de la cascada", "Relato",
"Trata sobre los relatos de Juan B. mientras veía unos pájaros"),
(2,"Alfredo Bartol López",
"Los panda de la montañaa", "Relato",
"En proceso"),
(3,"Cristian Bartol López",
"Los guerreros sangre fría", "Historia",
"En proceso");

SELECT * FROM books;