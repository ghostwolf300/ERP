alter table t_user add column initial_pw boolean;
alter table t_user add column locked boolean;
alter table t_user add column valid_from date not null default '2019-01-01';
alter table t_user add column valid_to date;

alter table t_user modify column initial_pw boolean not null default true;
alter table t_user modify column locked boolean not null default false;

select * from t_user;

update t_user set initial_pw=false;
update t_user set locked=false;