/**
  18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).
 */

-- edit foreign key
USE furama_manager;
ALTER TABLE contract
    MODIFY customer_id int,
    DROP FOREIGN KEY contract_ibfk_2;
ALTER TABLE contract
    ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE SET NULL;

-- delete customer
set SQL_SAFE_UPDATES = 0;
DELETE cu
FROM customer cu
         INNER JOIN contract c ON cu.customer_id = c.customer_id
WHERE (year(c.date_signed) < 2021 OR year(c.date_end) < 2021);
set SQL_SAFE_UPDATES = 1;
