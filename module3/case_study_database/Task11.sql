/**
11.	Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng bởi những khách hàng có ten_loai_khach là “Diamond”
  và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”.
 */

USE furama_manager;
SELECT c.customer_id, si.service_include_id, si.service_include_name
FROM customer c
         INNER JOIN customer_type t ON c.customer_type_id = t.customer_type_id
         INNER JOIN contract ct ON c.customer_id = ct.customer_id
         INNER JOIN contract_detail cd ON ct.contract_id = cd.contract_id
         INNER JOIN service_include si ON cd.service_include_id = si.service_include_id
WHERE t.value = 'Diamond'
  AND c.address REGEXP '(Vinh|Quảng Ngãi)';

