select * from t_user;

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