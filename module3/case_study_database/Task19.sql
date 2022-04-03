/**
19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi.
 */

USE furama_manager;
UPDATE service_include,
    (
        SELECT si.service_include_id
        FROM service_include si
                 INNER JOIN contract_detail cd ON si.service_include_id = cd.service_include_id
                 INNER JOIN contract c ON cd.contract_id = c.contract_id
        WHERE (year(c.date_signed) = 2020 OR year(c.date_end) = 2020)
        GROUP BY cd.service_include_id
        HAVING sum(cd.amount) >= 10
    ) AS selet_by_id
SET service_include.`price` = service_include.`price` * 2
WHERE service_include.service_include_id = selet_by_id.service_include_id;
