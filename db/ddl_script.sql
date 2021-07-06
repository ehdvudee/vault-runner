create table key (
    key_id integer auto_increment,
    key_store_id integer,
    key_name varchar2(48),
    key_tag varchar2(48),
    primary key(key_id, key_store_id)
);

create table key_store (
    key_store_id integer auto_increment primary key,
    key_store_name varchar2(48)
);

alter table key add constraint key_key_store_id_fk foreign key(key_store_id) references key_store(key_store_id);