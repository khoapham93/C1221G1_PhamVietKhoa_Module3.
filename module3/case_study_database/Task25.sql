/**
  25.	Tạo Trigger có tên tr_xoa_hop_dong khi xóa bản ghi trong bảng hop_dong
  thì hiển thị tổng số lượng bản ghi còn lại có trong bảng hop_dong ra giao diện console của database.
Lưu ý: Đối với MySQL thì sử dụng SIGNAL hoặc ghi log thay cho việc ghi ở console.
 */
USE furama_manager;

CREATE TABLE contract_log
(
    id              int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    delete_time     datetime,
    contract_remain int
);


ALTER TABLE contract_detail
    MODIFY contract_id int;
ALTER TABLE contract_detail
    DROP FOREIGN KEY fk_contract_id;
ALTER TABLE contract_detail
    ADD CONSTRAINT fk_contract_id FOREIGN KEY (contract_id) REFERENCES contract (contract_id) ON DELETE SET NULL;

DELIMITER //
CREATE TRIGGER tr_xoa_hop_dong
    AFTER DELETE
    ON contract
    FOR EACH ROW
BEGIN
     SELECT COUNT(contract_id) INTO @contract_remain  FROM contract;
     INSERT INTO contract_log (delete_time,contract_remain) VALUE (now(),@contract_remain);
END;
DELIMITER ;

DELETE FROM contract WHERE contract_id =13;

SELECT *
FROM contract_log;