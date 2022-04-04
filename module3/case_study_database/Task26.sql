/**
  26.	Tạo Trigger có tên tr_cap_nhat_hop_dong khi cập nhật ngày kết thúc hợp đồng,
  cần kiểm tra xem thời gian cập nhật có phù hợp hay không,
  với quy tắc sau: Ngày kết thúc hợp đồng phải lớn hơn ngày làm hợp đồng ít nhất là 2 ngày.
  Nếu dữ liệu hợp lệ thì cho phép cập nhật,
  nếu dữ liệu không hợp lệ thì in ra thông báo
  “Ngày kết thúc hợp đồng phải lớn hơn ngày làm hợp đồng ít nhất là 2 ngày” trên console của database.
Lưu ý: Đối với MySQL thì sử dụng SIGNAL hoặc ghi log thay cho việc ghi ở console.

 */

USE furama_manager;
DELIMITER //
CREATE TRIGGER tr_cap_nhat_hop_dong
    BEFORE UPDATE
    ON contract
    FOR EACH ROW
BEGIN
    IF datediff(NEW.date_end, NEW.date_signed) < 2 THEN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT ='date end of contract must be greater than date signed at least 2 days!';
    END IF;
END //
DELIMITER ;

UPDATE contract set date_end = '2021-05-27' WHERE contract_id = 12;