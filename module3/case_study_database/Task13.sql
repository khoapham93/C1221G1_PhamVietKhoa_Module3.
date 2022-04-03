/**
  13.	Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất bởi các Khách hàng đã đặt phòng.
  (Lưu ý là có thể có nhiều dịch vụ có số lần sử dụng nhiều như nhau).
 */
-- chỉ hiển thị 1 giá trị lớn nhất
USE furama_manager;
SELECT A.service_include_name, max(total)
FROM (
         SELECT si.service_include_name, sum(cd.amount) AS total
         FROM service_include si
                  LEFT JOIN contract_detail cd ON si.service_include_id = cd.service_include_id
         GROUP BY cd.service_include_id
     ) A;

-- hiển thị nhiều giá trị
SELECT si.service_include_name, sum(cd.amount) AS total
FROM service_include si
         LEFT JOIN contract_detail cd ON si.service_include_id = cd.service_include_id
GROUP BY cd.service_include_id
HAVING sum(cd.amount) >= ALL (SELECT sum(amount) FROM contract_detail GROUP BY service_include_id);
