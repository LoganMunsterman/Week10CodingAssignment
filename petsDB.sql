create database if not exists pets;

use pets;

drop table if exists cats;

create table cats (
	id int(10) not null auto_increment,
	name varchar(30) not null,
	attitude varchar(30) not null,
	gender varchar(10) not null,
	primary key(id)
);