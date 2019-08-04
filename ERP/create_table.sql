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

create table t_auth_object(
	id int not null auto_increment,
	name varchar(32) not null,
	primary key(id)
);

create table t_role_auth_object(
	role_id int not null,
	object_id int not null,
	read_access boolean not null default true,
	write_access boolean not null default false,
	primary key(role_id,object_id),
	constraint fk_t_role_role_id foreign key(role_id) references t_role(id) on delete cascade,
	constraint fk_t_auth_object_object_id foreign key(object_id) references t_auth_object(id) on delete cascade
);

create table t_uom(
	id int not null auto_increment,
	name varchar(20),
	iso_code varchar(4),
	primary key(id)
);

create table t_material_type(
	id int not null auto_increment,
	short_name varchar(4),
	name varchar(20),
	primary key(id)
);

create table t_material_group(
	id int not null auto_increment,
	name varchar(20),
	primary key(id)
);

create table t_material(
	id varchar(20) not null,
	legacy_id varchar(20),
	name varchar(40) not null,
	ean_13 varchar(13),
	type_id int not null,
	group_id int,
	parent_id varchar(20),
	base_uom_id int not null,
	gross_weight double,
	net_weight double,
	wt_uom_id int,
	length double,
	width double,
	height double,
	dim_uom_id int,
	created_by varchar(32) not null default '',
	created_ts timestamp not null default current_timestamp,
	changed_by varchar(32) not null default '',
	changed_ts timestamp not null default current_timestamp,
	primary key(id),
	constraint fk_material_type_id foreign key(type_id) references t_material_type(id),
	constraint fk_material_group_id foreign key(group_id) references t_material_group(id),
	constraint fk_material_base_uom_id foreign key(base_uom_id) references t_uom(id),
	constraint fk_material_wt_uom_id foreign key(wt_uom_id) references t_uom(id),
	constraint fk_material_dim_uom_id foreign key(dim_uom_id) references t_uom(id),
	constraint fk_material_created_by foreign key(created_by) references t_user(id),
	constraint fk_material_changed_by foreign key(changed_by) references t_user(id)
);
