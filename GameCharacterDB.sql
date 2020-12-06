create database if not exists GameCharacter;

use GameCharacter;

drop table if exists Characters;
drop table if exists Factions;

create table Factions (
	id int(10) not null auto_increment,
	faction_name varchar(20) unique not null,
	primary key(id)
		);

create table Characters (
	id int(10) not null auto_increment,
    character_name varChar(25) not null,
    character_class varChar(25) not null,
    faction_id int(10) not null,
    primary key(id),
    foreign key(faction_id) references Factions(id)
		);