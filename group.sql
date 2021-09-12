create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
insert into people (name) values ('Vasya');
insert into people (name) values ('Vanya');
insert into people (name) values ('Anton');
insert into people (name) values ('Valera');
insert into devices (name, price) values ('Comp', 50000);
insert into devices (name, price) values ('Phone', 30000);
insert into devices (name, price) values ('Pleer', 10000);
insert into devices (name, price) values ('Refrigirator', 30000);
insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 3);
insert into devices_people (device_id, people_id) values (4, 2);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (4, 1);

select p.name, avg(d.price) from devices as d join devices_people as dp on d.id = dp.device_id join people as p on dp.people_id = p.id group by p.name;
select p.name, avg(d.price) from devices as d join devices_people as dp on d.id = dp.device_id join people as p on dp.people_id = p.id group by p.name having avg(d.price) > 5000;