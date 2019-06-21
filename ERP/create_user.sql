alter table t_user drop primary key;
alter table t_user change id username varchar(10) not null;
alter table t_user add primary key(id);

alter table t_user modify id varchar(32) not null;
alter table t_user_role modify user_id varchar(32) not null;

select * from t_user;

alter table t_user add column email varchar(254);

insert into t_user(id,last_name,first_name,password,enabled) values('ville.susi','Susi','Ville','$2a$10$GxGH3SscenfIjKSMT/JdBuFDA2DCWOf3auMgyrM9tYg73.vL7O8Eu',true);
update t_user set email='ville.susi@hotmail.com' where id='ville.susi';

insert into t_user(id,last_name,first_name,password,enabled) values('aku.ankka','Aku','Ankka','$2a$10$GxGH3SscenfIjKSMT/JdBuFDA2DCWOf3auMgyrM9tYg73.vL7O8Eu',true);
update t_user set email='aku.ankka@yritys.com' where id='aku.ankka';

insert into t_user(id,last_name,first_name,password,enabled) values('mikki.hiiri','Mikki','Hiiri','$2a$10$GxGH3SscenfIjKSMT/JdBuFDA2DCWOf3auMgyrM9tYg73.vL7O8Eu',true);
update t_user set email='mikki.hiiri@yritys.com' where id='mikki.hiiri';