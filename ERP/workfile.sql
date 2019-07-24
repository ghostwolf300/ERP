select * from t_user;
select * from t_role;
select * from t_user_role;

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

insert into t_role (id,name,description) values(10,'ROLE_ADMIN','Admin role');
insert into t_role (id,name,description) values(20,'ROLE_USER','Generic user');
insert into t_role (id,name,description) values(30,'ROLE_INITIAL_PW','Temporary initial pw role');

insert into t_user_role (user_id,role_id) values('ville.susi',10);
insert into t_user_role (user_id,role_id) values('aku.ankka',20);