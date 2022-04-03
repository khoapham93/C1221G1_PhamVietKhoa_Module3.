/**
  20.	Hiển thị thông tin của tất cả các nhân viên và khách hàng có trong hệ thống,
  thông tin hiển thị bao gồm
  id (ma_nhan_vien, ma_khach_hang), ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi.
 */

USE furama_manager;
 SELECT employee_id as id, `name`, email, phone, birthday, address, "employee" as `type` from employee
 UNION ALL
 SELECT customer_id as id, `name`, email, phone, birthday, address, "customer" as `type` from customer

