drop table if exists items;

create table if not exists items
(
    id   serial primary key,
    name varchar(2000)
);
