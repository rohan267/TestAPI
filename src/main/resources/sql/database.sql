CREATE TABLE Hero ( 
    id IDENTITY int, 
       name varchar(40), 
       description varchar(500), 
       health int default 0, 
       armour int default 0, 
       shield int default 0, 
       real_name varchar(40), 
       age int, 
       height decimal(10,2), 
       affiliation varchar(40), 
       base_of_operations varchar(100), 
       difficulty int, 
       url varchar(50) 
)


CREATE TABLE Ability(
	id IDENTITY,
	name varchar(40),
	description varchar(500),
	is_ultimate boolean,
	url varchar(50),
	hero_id int
)