/** Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau.
Học viên sử dụng theo 3 cách khác nhau để thực hiện yêu cầu trên.
*/
USE furama_manager;
-- cach 1
SELECT DISTINCT `name`
FROM customer;
-- cach 2
SELECT `name`
FROM customer
UNION
SELECT `name`
FROM customer;
-- cach 3
SELECT `name`
FROM customer
GROUP BY `name`;
