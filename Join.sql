create table departments(
    id serial primary key,
    name varchar(255)
);
create table emploers(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);
insert into departments(name) values ('Programmers');
insert into departments(name) values ('Account');
insert into departments(name) values ('Organize');
insert into departments(name) values ('Sysadmins');
insert into departments(name) values ('Managers');

insert into emploers(name, departments_id) values ('Ivan', 1);
insert into emploers(name, departments_id) values ('Max', 2);
insert into emploers(name, departments_id) values ('Mike', 3);
insert into emploers(name, departments_id) values ('Mindi', 4);
insert into emploers(name, departments_id) values ('Zak', 2);
insert into emploers(name, departments_id) values ('Mack', 1);
insert into emploers(name, departments_id) values ('Dick', 3);
insert into emploers(name, departments_id) values ('Vicky', 4);

select d.name, e.name from departments as d left join emploers as e on d.id = e.departments_id where e.name isnull;

select d.name, e.name from departments as d left join emploers as e on d.id = e.departments_id;
select d.name, e.name from emploers as e right join departments as d on e.departments_id = d.id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender int
);
insert into teens(name, gender) values ('Ivan', 1);
insert into teens(name, gender) values ('Max', 1);
insert into teens(name, gender) values ('Mindi', 0);
insert into teens(name, gender) values ('Vicky', 0);

select t.name as f, t1.name as s from teens as t cross join teens as t1 where t.gender != t1.gender;

