/**
  9.	Thực hiện thống kê doanh thu theo tháng,
  nghĩa là tương ứng với mỗi tháng trong năm 2021 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.
 */
 USE furama_manager;
SELECT month(c.date_signed) as `month`, sum(f.rental_fee + coalesce(cd.amount * si.price, 0)) AS doanh_thu_2021
FROM contract  c
LEFT JOIN  facility f ON f.facility_id = c.facility_id
LEFT JOIN contract_detail cd ON c.contract_id = cd.contract_id
LEFT JOIN service_include si ON cd.service_include_id = si.service_include_id
WHERE year(c.date_signed) = 2021 OR year(c.date_end) = 2021
GROUP BY month(c.date_signed)
ORDER BY month(c.date_signed);