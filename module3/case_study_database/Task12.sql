/**
12.	Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng),
  so_dien_thoai (khách hàng), ten_dich_vu, so_luong_dich_vu_di_kem
  (được tính dựa trên việc sum so_luong ở dich_vu_di_kem),
  tien_dat_coc của tất cả các dịch vụ đã từng được khách hàng đặt vào 3 tháng cuối năm 2020
  nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021.
 */

USE furama_manager;
SELECT c.contract_id,
       e.name                      AS name_employee,
       cu.name                     AS customer_name,
       cu.phone                    AS customer_phone,
       f.facility_name,
       coalesce(sum(cd.amount), 0) AS total_service_used,
       c.deposit
FROM contract c
         LEFT JOIN customer cu ON c.customer_id = cu.customer_id
         LEFT JOIN employee e ON c.employee_id = e.employee_id
         LEFT JOIN facility f ON c.facility_id = f.facility_id
         LEFT JOIN contract_detail cd ON c.contract_id = cd.contract_id
WHERE (c.date_signed BETWEEN '2020-10-01' AND '2020-12-31')
   OR (c.date_end BETWEEN '2020-10-01' AND '2020-12-31')
    AND f.facility_id NOT IN
        (SELECT contract.facility_id
         FROM contract
         WHERE (contract.date_signed BETWEEN '2021-01-01' AND '2021-06-30')
            OR (contract.date_end BETWEEN '2021-01-01' AND '2021-06-30'))
GROUP BY c.contract_id;
