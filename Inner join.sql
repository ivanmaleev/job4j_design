create table organization(
    id serial primary key,
    name varchar(255)
);
create table manager(
    id serial primary key,
    FIO varchar(255),
    organization_id int references organization(id)
);
select org.name, man.FIO from organization as org join manager as man on org.id = man.organization_id;
select man.organization_id, man.FIO from organization as org join manager as man on org.id = man.organization_id;
select org.name, man.FIO from organization as org join manager as man on org.id = man.organization_id where man.FIO = 'Vasya';