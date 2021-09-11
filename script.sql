create table cars(
	id serial primary key,
	model varchar(255),
	description text,
	hp integer
);
select * from cars;
insert into cars (model, description, hp) values ('Lada','Vesta', 120);
update cars set model = 'Ladada', description = 'Granta', hp = 95;
delete from cars;
select * from cars;