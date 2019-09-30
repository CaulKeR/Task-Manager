# task 1 - Запрос, возвращающий список людей и количество задач, назначенных им

select p.id, p.first_name, p.last_name, p.patronymic, count(t.id)
from person p,
     task t
where p.id = t.person_id
  and p.delete_date is null
  and t.delete_date is null
group by p.id;

# task 2 - Запрос , возвращающий список людей, у которых есть задачи с суммарным временем выполнения больше 100 часов

select p.first_name, p.last_name, p.patronymic
from person p,
     task t,
     task_log tl
where tl.spent_time > 100
  and tl.task_id = t.id
  and t.person_id = p.id
  and p.delete_date is null
  and t.delete_date is null
group by p.id;