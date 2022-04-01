USE furama_manager;
SELECT customer_id, name, birthday, gender, id_card, address
FROM customer
WHERE     ROUND(DATEDIFF(CURDATE(), birthday) / 365, 0) BETWEEN 18 AND 50
AND address REGEXP '(Đà Nẵng|Quảng Trị)';

-- cách 2
USE furama_manager;
SELECT customer_id, name, birthday, gender, id_card, address
FROM customer
WHERE     TIMESTAMPDIFF(YEAR ,birthday, CURDATE()) BETWEEN 18 AND 50
  AND address REGEXP '(Đà Nẵng|Quảng Trị)';