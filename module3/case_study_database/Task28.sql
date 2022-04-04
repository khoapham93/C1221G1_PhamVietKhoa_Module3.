/**
  28.	Tạo Stored Procedure sp_xoa_dich_vu_va_hd_room
  để tìm các dịch vụ được thuê bởi khách hàng với loại dịch vụ là “Room” từ đầu năm 2015 đến hết năm 2019
  để xóa thông tin của các dịch vụ đó (tức là xóa các bảng ghi trong bảng dich_vu)
  và xóa những hop_dong sử dụng dịch vụ liên quan (tức là phải xóa những bản gi trong bảng hop_dong)
  và những bảng liên quan khác.
 */
USE furama_manager;

ALTER TABLE contract
    DROP FOREIGN KEY contract_ibfk_3;
ALTER TABLE contract
    ADD CONSTRAINT fk_facility_id FOREIGN KEY (facility_id) REFERENCES facility (facility_id) ON DELETE CASCADE;

ALTER TABLE contract_detail
    DROP FOREIGN KEY fk_contract_id;
ALTER TABLE contract_detail
    ADD CONSTRAINT fk_contract_id FOREIGN KEY (contract_id) REFERENCES contract (contract_id) ON DELETE CASCADE;

DROP PROCEDURE IF EXISTS sp_xoa_dich_vu_va_hd_room;
DELIMITER //
CREATE PROCEDURE sp_xoa_dich_vu_va_hd_room()
BEGIN
    DELETE f
    FROM facility f
             INNER JOIN contract c ON f.facility_id = c.facility_id
             INNER JOIN contract_detail cd ON c.contract_id = cd.contract_id
    WHERE f.facility_name LIKE '%Room%'
      AND c.facility_id IN
          (SELECT contract.facility_id
           FROM contract
           WHERE year(contract.date_signed) BETWEEN 2015 AND 2019
          );
END //
DELIMITER ;

CALL sp_xoa_dich_vu_va_hd_room();