-- 	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu
-- của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021.
USE furama_manager;
SELECT f.facility_id, f.facility_name, f.floor_square, f.maximum_people, f.rental_fee, ft.value
FROM facility AS f
         LEFT JOIN facility_type ft ON f.facility_type_id = ft.facility_type_id
         LEFT JOIN contract c ON f.facility_id = c.facility_id
WHERE (YEAR(c.date_signed) = 2020 OR year(c.date_end) = 2020)
  AND f.facility_id NOT IN
      (SELECT contract.facility_id
       FROM contract
       WHERE (YEAR(contract.date_signed) = 2021 OR year(contract.date_end) = 2021))
GROUP BY f.facility_id;