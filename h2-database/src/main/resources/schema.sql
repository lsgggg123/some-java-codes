drop table if exists user_table;
create table user_table
(
    `id`           int not null auto_increment,
    `name`         varchar(16) not null comment 'name',
    `age`          tinyint not null comment 'age',
    `nick_name`    varchar(16) not null comment 'nickname',
    `gmt_create`   datetime default current_timestamp,
    `gmt_modified` datetime default current_timestamp on update current_timestamp,
    primary key (`id`)
);