alter user postgres with password 'postgres';


create table demo_version(
 version integer
);

insert into demo_version values(1);

create table itguys(
first_name	character varying(256),
surname		character varying(256)
);

insert into itguys values('Scott','McNealy');
insert into itguys values('Bill','Gates');
insert into itguys values('Larry','Ellison');
insert into itguys values('Steve','Jobs');
insert into itguys values('Bob','Kahn');
insert into itguys values('Tim','Berners-Lee');
