/**
15.	Hiển thi thông tin của tất cả nhân viên bao gồm
  ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai, dia_chi
  mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021.
 */

USE furama_manager;

SELECT c.employee_id, `name`, al.level_name, p.position_name, phone, address,count(c.employee_id) as number_of_contract_signed
FROM employee em
         INNER JOIN academic_level al ON em.academic_id = al.academic_id
         INNER JOIN position p ON em.position_id = p.position_id
         INNER JOIN contract c ON em.employee_id = c.employee_id
WHERE (c.date_signed BETWEEN '2020-01-01' and '2021-12-31')
OR (c.date_end BETWEEN '2020-01-01' and '2021-12-31')
GROUP BY c.employee_id
HAVING number_of_contract_signed <= 3;
