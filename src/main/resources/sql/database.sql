CREATE TABLE Hero ( 
    id IDENTITY, 
        name varchar(20), 
       description varchar(200), 
       health int default 0, 
       armour int default 0, 
       shield int default 0, 
       real_name varchar(20), 
       age int, 
       height decimal(10,2), 
       affiliation varchar(15), 
       base_of_operations varchar(20), 
       difficulty int, 
       url varchar(50) 
)


CREATE TABLE Ability(
	id IDENTITY,
	name varchar(20),
	description varchar(200),
	is_ultimate boolean,
	url varchar(50),
	hero_id int
)