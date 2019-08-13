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


create table t_bp_group(
	id int not null auto_increment,
	name varchar(20) not null,
	primary key(id)
);

create table t_business_partner(
	id varchar(10) not null,
	group_id int not null,
	name_1 varchar(60) not null,
	name_2 varchar(60),
	name_3 varchar(60),
	name_4 varchar(60),
	keyword_1 varchar(20),
	keyword_2 varchar(20),
	created_by varchar(32) not null default '',
	created_ts timestamp not null default current_timestamp,
	changed_by varchar(32) not null default '',
	changed_ts timestamp not null default current_timestamp,
	primary key(id),
	constraint fk_business_partner_group_id foreign key(group_id) references t_bp_group(id) on delete cascade
);

create table t_address_type(
	id int not null auto_increment,
	name varchar(20),
	primary key(id)
);

create table t_address(
	id int not null auto_increment,
	bp_id varchar(10) not null,
	type_id int not null,
	street varchar(60),
	house_num int,
	postcode varchar(10),
	city varchar(40),
	country_iso varchar(2),
	po_box int,
	po_box_pc varchar(10),
	valid_from date not null,
	valid_to date,
	primary key(id),
	constraint fk_address_type_id foreign key(type_id) references t_address_type(id) on delete cascade,
	constraint fk_address_bp_id foreign key(bp_id) references t_business_partner(id) on delete cascade
);

create table t_telno(
	id int not null auto_increment,
	bp_id varchar(10) not null,
	number varchar(20),
	description varchar(40),
	primary key(id),
	constraint fk_telno_bp_id foreign key(bp_id) references t_business_partner(id) on delete cascade
);

create table t_id_number(
	id int not null auto_increment,
	short_name varchar(10),
	description varchar(60),
	primary key(id)
);

create table t_bp_id_number(
	bp_id varchar(10) not null,
	num_id int not null,
	id_num_value varchar(40),
	primary key(bp_id,num_id),
	constraint fk_bp_id_number_bp_id foreign key(bp_id) references t_business_partner(id) on delete cascade,
	constraint fk_bp_id_number_num_id foreign key(num_id) references t_id_number(id) on delete cascade
);

create table t_payment_term(
	id int not null auto_increment,
	short_name varchar(10),
	name varchar(40),
	primary key(id)
);

create table t_payment_method(
	id int not null auto_increment,
	short_name varchar(10),
	name varchar(40),
	primary key(id)
)


create table t_account_type(
	id int not null,
	type_name varchar(20),
	primary key(id)
);

create table t_chart_of_accounts(
	id int not null auto_increment,
	account varchar(20) not null,
	acct_text varchar(40),
	
);

