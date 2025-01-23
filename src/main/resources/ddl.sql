-- users table
create table t_users
(
    id bigint not null,
    name varchar(30) not null,
    email varchar(50) not null,
    password varchar(255) not null,
    created_at datetime not null,
    updated_at datetime not null,
    is_active tinyint(1) not null,
    PRIMARY KEY (id)
) engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;