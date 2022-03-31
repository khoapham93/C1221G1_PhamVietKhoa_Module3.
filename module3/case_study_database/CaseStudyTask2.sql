use furama_manager;
select *
from employee
where `name` REGEXP '^[HKT]\.'
    and length(`name`) <= 15;