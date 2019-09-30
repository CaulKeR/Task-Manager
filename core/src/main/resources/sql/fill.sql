insert into person (first_name, last_name, patronymic, login, password, delete_date)
values ('Kirill', 'Karpuk', 'Aleksandrovich', 'CaulKeR', 'password', null);
insert into person (first_name, last_name, patronymic, login, password, delete_date)
values ('Nikita', 'Nikitov', 'Nikitovich', 'tytor', 'qwerty', null);
insert into person (first_name, last_name, patronymic, login, password, delete_date)
values ('Oleg', 'Olegov', 'Olegovich', 'olezha228', '1234567', null);
insert into person (first_name, last_name, patronymic, login, password, delete_date)
values ('Tsar', 'Gvidon', null, 'voron777', 'parol', null);

insert into task(title, person_id, status, delete_date)
values ('Buy bike', 1, 'in progress', null);
insert into task(title, person_id, status, delete_date)
values ('Go shopping', 1, 'not started', null);
insert into task(title, person_id, status, delete_date)
values ('Visit grandmother', 1, 'done', null);
insert into task(title, person_id, status, delete_date)
values ('Finish project task', 2, 'in progress', null);
insert into task(title, person_id, status, delete_date)
values ('Wash dishes', 2, 'not started', null);
insert into task(title, person_id, status, delete_date)
values ('Make our plan', 2, 'in progress', null);
insert into task(title, person_id, status, delete_date)
values ('Buy glasses for dad', 3, 'done', null);
insert into task(title, person_id, status, delete_date)
values ('Win friend in poker', 5, 'not started', null);

insert into task_log(task_id, spent_time, comment)
values (3, 72, 'She is pretty old lady');
insert into task_log(task_id, spent_time, comment)
values (7, 2, 'Found the best glasses in that town');
insert into task_log(task_id, spent_time, comment)
values (4, null, 'We should work faster then earlier');