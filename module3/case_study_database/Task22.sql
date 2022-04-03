/**
  22.	Thông qua khung nhìn v_nhan_vien thực hiện cập nhật địa chỉ thành “Liên Chiểu”
  đối với tất cả các nhân viên được nhìn thấy bởi khung nhìn này.
 */

USE furama_manager;
UPDATE v_nhan_vien
SET address = replace(address, 'Hải Châu', 'Liên Chiểu');
