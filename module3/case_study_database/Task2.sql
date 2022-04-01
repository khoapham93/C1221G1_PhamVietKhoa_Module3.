USE furama_manager;
SELECT employee_id,name,birthday,id_card
FROM employee
WHERE `name` REGEXP '^[HKT]'
  AND length(`name`) <= 15;