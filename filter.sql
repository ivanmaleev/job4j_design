select p.name, p.price, t.name from product as p join type as t on p.type_id = t.id where t.name = 'СЫР';
select p.name, p.price from product as p where p.name like '%мороженое%';
select p.name, p.price from product as p where expired_date >= curren_date;
select p.name, p.price from product order by p.price desc limit 1;
select t.name, count(p.id) from product as p join type as t on p.type_id = t.id group by t.name;
select p.name, p.price, t.name from product as p join type as t on p.type_id = t.id where t.name = 'СЫР' or t.name = 'МОЛОКО'; 
select t.name, count(p.id) from product as p join type as t on p.type_id = t.id group by t.name having count(p.id) < 10;
select p.name, t.name from product as p join type as t on p.type_id = t.id;