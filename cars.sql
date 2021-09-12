create table body(
    id serial primary key,
    name varchar(255)
);
create table engine(
    id serial primary key,
    hp int,
    name varchar(255)
);
create table transmission(
    id serial primary key,
    name varchar(255)
);
create table car(
    id serial primary key,
    name varchar(255),
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);
insert into body(name) values ('Sedan');
insert into body(name) values ('Cabrio');
insert into body(name) values ('SUV');
insert into engine(hp, name) values (100, 'TSi');
insert into engine(hp, name) values (150, 'FSi');
insert into engine(hp, name) values (90, 'Tdi');
insert into transmission(name) values ('DSG');
insert into transmission(name) values ('Manual');
insert into transmission(name) values ('torque');
insert into car(name, body_id, engine_id, transmission_id) values ('Audi', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('Skoda', 2, 2, 2);

select c.name, b.name, e.name, t.name from car as c left join body as b on c.body_id = b.id left join engine as e on c.engine_id = e.id left join transmission as t on c.transmission_id = t.id

select b.name from body as b left join car as c on c.body_id = b.id where c.body_id is null

select e.name from engine as e left join car as c on c.body_id = e.id where c.body_id is null

select t.name from transmission as t left join car as c on c.body_id = t.id where c.body_id is null
