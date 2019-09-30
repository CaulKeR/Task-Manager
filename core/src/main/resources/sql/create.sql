create database `task_manager`
    character set 'utf8mb4'
    collate 'utf8mb4_general_ci';

create table `task_manager`.`person`
(
    `id`          integer(10) unsigned not null auto_increment unique,
    `first_name`  varchar(33)          not null,
    `last_name`   varchar(33)          not null,
    `patronymic`  varchar(33),
    `login`       varchar(33),
    `password`    varchar(33),
    `delete_date` date default null,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table `task_manager`.`task`
(
    `id`          integer(10) unsigned not null auto_increment unique,
    `title`       varchar(200)         not null,
    `person_id`   integer(10) unsigned not null,
    `status`      enum ('not started', 'in progress', 'done') default 'not started',
    `delete_date` date                                        default null,
    primary key (`id`),
    key `person_fk_idx` (`person_id`),
    constraint `person_fk` foreign key (`person_id`) references `task_manager`.`person` (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table `task_manager`.`task_log`
(
    `id`         integer(10) unsigned not null auto_increment unique,
    `task_id`    integer(10) unsigned not null,
    `spent_time` integer(6) unsigned,
    `comment`    varchar(400),
    primary key (`id`),
    key `task_fk_idx` (`task_id`),
    constraint `task_fk` foreign key (`task_id`) references `task_manager`.`task` (`id`)
) engine = InnoDB
  default charset = utf8mb4;