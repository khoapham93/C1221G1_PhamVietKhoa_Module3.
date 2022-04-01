USE furama_manager;
SELECT c.customer_id, c.name, c.gender, count(c.customer_id) AS total_of_booking
FROM customer c
         INNER JOIN contract c2 ON c.customer_id = c2.customer_id
GROUP BY c.customer_id;