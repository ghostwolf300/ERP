select * from t_user;
select * from t_role;
select * from t_user_role;
select * from t_auth_object;

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
insert into t_auth_object (name) values ('OBJ_MATERIAL');
insert into t_auth_object (name) values ('OBJ_BUSINESS_PARTNER');
insert into t_auth_object (name) values ('OBJ_CUSTOMER');
insert into t_auth_object (name) values ('OBJ_SUPPLIER');

insert into t_role_auth_object(role_id,object_id,read_access,write_access) values (10,1,true,true);
insert into t_role_auth_object(role_id,object_id,read_access,write_access) values (10,2,true,true);
insert into t_role_auth_object(role_id,object_id,read_access,write_access) values (10,3,true,true);
insert into t_role_auth_object(role_id,object_id,read_access,write_access) values (10,4,true,true);
insert into t_role_auth_object(role_id,object_id,read_access,write_access) values (10,5,true,true);

alter table t_role_auth_object change read_access read_rights boolean default true;
alter table t_role_auth_object change write_access update_rights boolean default false;
alter table t_role_auth_object add column create_rights boolean default false;
alter table t_role_auth_object add column delete_rights boolean default false;




