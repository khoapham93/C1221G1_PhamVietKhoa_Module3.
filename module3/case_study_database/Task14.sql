/**
 14.	Hiển thị thông tin tất cả các Dịch vụ đi kèm chỉ mới được sử dụng một lần duy nhất.
  Thông tin hiển thị bao gồm ma_hop_dong, ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung
  (được tính dựa trên việc count các ma_dich_vu_di_kem).
 */

USE furama_manager;
SELECT c.contract_id, f.facility_name, si.service_include_name,  count(cd.service_include_id) as time_of_use
FROM contract c
INNER JOIN facility f ON c.facility_id = f.facility_id
INNER JOIN contract_detail cd ON c.contract_id = cd.contract_id
INNER JOIN service_include si ON cd.service_include_id = si.service_include_id
GROUP BY cd.service_include_id
HAVING time_of_use = 1;
