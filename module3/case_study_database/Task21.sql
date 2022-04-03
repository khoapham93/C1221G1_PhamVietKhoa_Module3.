/**
  21.	Tạo khung nhìn có tên là v_nhan_vien để lấy được thông tin của tất cả các nhân viên
  có địa chỉ là “Hải Châu” và đã từng lập hợp đồng cho một hoặc nhiều khách hàng bất kì
  với ngày lập hợp đồng là “12/12/2019”.
 */
USE furama_manager;
CREATE VIEW v_nhan_vien AS
SELECT employee_id, `name`, phone, address
FROM employee
WHERE address LIKE '%Hải Châu%'
and exists (SELECT employee_id FROM contract WHERE date_signed = '2019-12-12')