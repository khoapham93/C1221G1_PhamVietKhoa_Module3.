/**
16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.
 */

USE furama_manager;
-- create view
CREATE OR REPLACE VIEW find_employee_have_contract AS
SELECT em.employee_id, count(em.employee_id) AS number_of_contract_signed
FROM employee em
         LEFT JOIN contract c ON em.employee_id = c.employee_id
WHERE (c.date_signed BETWEEN '2019-01-01' AND '2021-12-31')
   OR (c.date_end BETWEEN '2019-01-01' AND '2021-12-31')
GROUP BY em.employee_id
HAVING number_of_contract_signed > 0;

-- delete employee not in view
set SQL_safe_updates = 0;
DELETE
FROM employee
WHERE employee_id NOT IN (SELECT employee_id FROM find_employee_have_contract);
set SQL_safe_updates = 1;
