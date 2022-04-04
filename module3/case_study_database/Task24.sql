/**
  24.	Tạo Stored Procedure sp_them_moi_hop_dong dùng để thêm mới vào bảng hop_dong
  với yêu cầu sp_them_moi_hop_dong phải thực hiện kiểm tra tính hợp lệ của dữ liệu bổ sung,
  với nguyên tắc không được trùng khóa chính và đảm bảo toàn vẹn tham chiếu đến các bảng liên quan.
 */
USE furama_manager;
DROP PROCEDURE IF EXISTS sp_them_moi_hop_dong;

DELIMITER //
CREATE PROCEDURE sp_them_moi_hop_dong(IN in_id int,
                                      IN in_date_signed date,
                                      IN in_date_end date,
                                      IN in_deposit double,
                                      IN in_employee_id int,
                                      IN in_customer_id int,
                                      IN in_facility_id int)

BEGIN
    IF exists(SELECT contract_id FROM contract WHERE contract_id = in_id)
    THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'contract id is exists';
    END IF;

    IF datediff(in_date_signed,in_date_end) > 0
    THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'date end must be greater than date signed!';
    END IF;
    IF NOT exists(SELECT employee_id FROM employee WHERE employee_id = in_employee_id)
    THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'employee id is exists';
    END IF;
    IF NOT exists(SELECT customer_id FROM customer WHERE customer_id = in_customer_id)
    THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'customer id is exists';
    END IF;

    IF NOT exists(SELECT facility_id FROM facility WHERE facility_id = in_facility_id)
    THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'facility id is exists';
    END IF;

    INSERT INTO contract (contract_id,
                          date_signed,
                          date_end,
                          deposit,
                          employee_id,
                          customer_id,
                          facility_id)
        VALUE (in_id,
               in_date_signed,
               in_date_end,
               in_deposit,
               in_employee_id,
               in_customer_id,
               in_facility_id);
END //
DELIMITER ;

CALL sp_them_moi_hop_dong(15, '2021-01-01', '2020-01-01', 500000, 1, 2, 1);