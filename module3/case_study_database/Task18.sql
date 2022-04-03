/**
  18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).
 */
-- tạo store procedure


CREATE OR REPLACE VIEW find_customer_have_contract_in_2021 AS
SELECT DISTINCT customer_id
FROM contract
WHERE year(date_signed) = 2021
   OR year(date_end) = 2021;

DELETE cu, c, cd
FROM  contract c
         INNER JOIN customer cu ON cu.customer_id = c.customer_id
         INNER JOIN contract_detail cd ON c.contract_id = cd.contract_id
WHERE c.customer_id NOT IN (SELECT customer_id FROM find_customer_have_contract_in_2021);


SELECT cu.customer_id
FROM customer cu
INNER JOIN contract c ON cu.customer_id = c.customer_id
WHERE cu.customer_id NOT IN (SELECT customer_id FROM find_customer_have_contract_in_2021);
