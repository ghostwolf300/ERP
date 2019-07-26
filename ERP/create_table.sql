create table t_user(
	id varchar(10) not null,
	last_name varchar(32) not null,
	first_name varchar(32) not null,
	password varchar(128) not null,
	enabled boolean,
	primary key(id)
);

create table t_role(
	id int not null auto_increment,
	name varchar(32) not null,
	description varchar(255),
	primary key(id)
);

create table t_user_role(
	user_id varchar(10) not null,
	role_id int not null,
	primary key(user_id,role_id),
	constraint fk_t_user_role_user_id foreign key(user_id) references t_user(id) on delete cascade,
	constraint fk_t_user_role_role_id foreign key(role_id) references t_role(id) on delete cascade
);