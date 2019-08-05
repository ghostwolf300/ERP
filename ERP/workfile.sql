select * from t_user;
select * from t_role;
select * from t_user_role;
select * from t_auth_object;
select * from t_role_auth_object;
select * from t_material;

delete from t_user where id='hannu.hanhi';

alter table t_user add column pw_changed date not null default '9999-12-31';
alter table t_user add column created_ts timestamp not null default current_timestamp;
alter table t_user add column created_by varchar(32) not null default '';
alter table t_user add column changed_ts timestamp not null default current_timestamp on update current_timestamp;
alter table t_user add column changed_by varchar(32) not null default '';

delete from t_user where id<>'ville.susi';

update t_user set pw_changed='2019-01-01' where id='aku.ankka';

alter table t_user alter column created_ts drop default;
alter table t_user alter column changed_ts drop default;
alter table t_user change changed_ts changed_ts timestamp not null default current_timestamp;

alter table t_user_role drop foreign key fk_t_user_role_user_id;
alter table t_user_role drop foreign key fk_t_user_role_role_id;

alter table t_user_role add constraint fk_t_user_role_user_id foreign key(user_id) references t_user(id) on delete cascade;
alter table t_user_role add constraint fk_t_user_role_role_id foreign key(role_id) references t_role(id) on delete cascade;

delete from t_role;
insert into t_role (id,name,description) values(10,'ROLE_ADMIN','Admin role');
insert into t_role (id,name,description) values(20,'ROLE_USER','Generic user');
insert into t_role (id,name,description) values(30,'ROLE_SALES_WORKER','Sales worker');
insert into t_role (id,name,description) values(35,'ROLE_SALES_MANAGER','Sales manager');
insert into t_role (id,name,description) values(40,'ROLE_LOGISTICS_WORKER','Logistics worker');
insert into t_role (id,name,description) values(45,'ROLE_LOGISTICS_MANAGER','Logistics manager');
insert into t_role (id,name,description) values(50,'ROLE_PURCHASING_WORKER','Purchasing worker');
insert into t_role (id,name,description) values(55,'ROLE_PURCHASING_MANAGER','Purchasing manager');
insert into t_role (id,name,description) values(60,'ROLE_FINANCE_WORKER','Finance worker');
insert into t_role (id,name,description) values(65,'ROLE_FINANCE_MANAGER','Finance manager');
insert into t_role (id,name,description) values(70,'ROLE_CREDIT_CONTROLLER','Credit controller');
insert into t_role (id,name,description) values(900,'ROLE_INITIAL_PW','Temporary initial pw role');

insert into t_user_role (user_id,role_id) values('ville.susi',10);
insert into t_user_role (user_id,role_id) values('aku.ankka',20);
insert into t_user_role (user_id,role_id) values('aku.ankka',30);

select t_role.id from t_role left join t_user_role on t_role.id=t_user_role.role_id;
select t_role.id from t_role left join t_user_role on t_role.id=t_user_role.role_id where user_id<>'aku.ankka' or user_id is null group by t_role.id;
select t_role.id from t_role left join t_user_role on t_role.id=t_user_role.role_id where user_id='aku.ankka' group by t_role.id;

delete from t_user_role where user_id='aku.ankka';

insert into t_auth_object (name) values ('OBJ_USER');

insert into t_auth_object (name) values ('OBJ_USER_ROLE');
insert into t_auth_object (name) values ('OBJ_ROLE');
insert into t_auth_object (name) values ('OBJ_ROLE_AUTH_OBJECT');
insert into t_auth_object (name) values ('OBJ_AUTH_OBJECT');

insert into t_role_auth_object(role_id,object_id,read_rights,update_rights) values (10,1,true,true);
insert into t_role_auth_object(role_id,object_id,read_rights,update_rights) values (10,2,true,true);
insert into t_role_auth_object(role_id,object_id,read_rights,update_rights) values (20,3,true,true);
insert into t_role_auth_object(role_id,object_id,read_rights,update_rights) values (20,4,true,true);
insert into t_role_auth_object(role_id,object_id,read_rights,update_rights) values (20,1,true,true);
insert into t_role_auth_object(role_id,object_id,read_rights,update_rights) values (35,2,true,true);
insert into t_role_auth_object(role_id,object_id,read_rights,update_rights) values (30,5,true,true);

alter table t_role_auth_object change read_access read_rights boolean default true;
alter table t_role_auth_object change write_access update_rights boolean default false;
alter table t_role_auth_object add column create_rights boolean default false;
alter table t_role_auth_object add column delete_rights boolean default false;

select o.id,o.name from t_auth_object o where o.id not in (select object_id from t_role_auth_object where role_id=10);

insert into t_uom(name,iso_code) values('Piece','PC');
insert into t_uom(name,iso_code) values('Kilogram','KG');
insert into t_uom(name,iso_code) values('Meter','MR');
insert into t_uom(name,iso_code) values('Centimeter','CM');
insert into t_uom(name,iso_code) values('Millimeter','MM');

insert into t_material_type(short_name,name) values('PRCH','Purchased');
insert into t_material_type(short_name,name) values('PROD','Produced');
insert into t_material_type(short_name,name) values('RAW','Raw');

insert into t_material_group(name) values('Discount');
insert into t_material_group(name) values('Base');
insert into t_material_group(name) values('Enhanced');
insert into t_material_group(name) values('Luxury');

insert into t_material(id,legacy_id,name,ean_13,type_id,base_uom_id,created_by,created_ts,changed_by,changed_ts)
values('T000000010','TEST01','Testituote A','0123456789111',1,1,'ville.susi','2019-08-05 09:00:00','ville.susi','2019-08-05 09:00:00');

insert into t_material(id,legacy_id,name,ean_13,type_id,base_uom_id,created_by,created_ts,changed_by,changed_ts)
values('T000000020','TEST02','Testituote B','0123456789222',2,1,'ville.susi','2019-08-05 09:00:00','ville.susi','2019-08-05 09:00:00');

insert into t_material(id,legacy_id,name,ean_13,type_id,base_uom_id,created_by,created_ts,changed_by,changed_ts)
values('T000000030','TEST03','Testituote C','0123456789333',3,1,'ville.susi','2019-08-05 09:00:00','ville.susi','2019-08-05 09:00:00');

delete from t_material;

alter table t_material change column gross_weight gross_weight double default 0.0;
alter table t_material change column net_weight net_weight double default 0.0;

update t_material set wt_uom_id=2;
alter table t_material change column wt_uom_id wt_uom_id int not null default 2;

update t_material set dim_uom_id=5;
alter table t_material change column dim_uom_id dim_uom_id int not null default 5;

alter table t_material change column length length double default 0.0;
alter table t_material change column width width double default 0.0;
alter table t_material change column height height double default 0.0;

update t_material set gross_weight=0;
update t_material set net_weight=0,width=0,lenght=0,height=0;