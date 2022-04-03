/**
  23.	Tạo Stored Procedure sp_xoa_khach_hang dùng để xóa thông tin của một khách hàng nào đó
  với ma_khach_hang được truyền vào như là 1 tham số của sp_xoa_khach_hang.
 */
USE furama_manager;

DELIMITER //
CREATE PROCEDURE sp_xoa_khach_hang(IN id int)
BEGIN
    DELETE FROM customer WHERE customer_id = id;
END //