/**
  17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond,
  chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán trong năm 2021 là lớn hơn 10.000.000 VNĐ.
 */

USE furama_manager;

CREATE VIEW find_customer_platinum AS
SELECT c.customer_id,
       c.name,
       ct.value,
       sum(f.rental_fee + coalesce(cd.amount * si.price, 0)) AS tong_tien
FROM customer c
         LEFT JOIN customer_type ct ON c.customer_type_id = ct.customer_type_id
         LEFT JOIN contract ctr ON c.customer_id = ctr.customer_id
         LEFT JOIN facility f ON ctr.facility_id = f.facility_id
         LEFT JOIN contract_detail cd ON ctr.contract_id = cd.contract_id
         LEFT JOIN service_include si ON cd.service_include_id = si.service_include_id
WHERE (year(ctr.date_signed) = 2021 OR year(ctr.date_end) = 2021)
  AND ct.value = 'Platinum'
GROUP BY c.customer_id;


SET SQL_SAFE_UPDATES = 0;
UPDATE customer
SET customer_type_id = 1
WHERE customer_id IN (SELECT customer_id
                      FROM find_customer_platinum
                      WHERE tong_tien > 10000000);
SET SQL_SAFE_UPDATES = 1;

SELECT *
FROM find_customer_platinum;