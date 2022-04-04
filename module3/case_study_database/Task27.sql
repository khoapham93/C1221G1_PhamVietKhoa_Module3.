/**
  27.	Tạo Function thực hiện yêu cầu sau:
a.	Tạo Function func_dem_dich_vu: Đếm các dịch vụ đã được sử dụng với tổng tiền là > 2.000.000 VNĐ.
b.	Tạo Function func_tinh_thoi_gian_hop_dong: Tính khoảng thời gian dài nhất
  tính từ lúc bắt đầu làm hợp đồng đến lúc kết thúc hợp đồng mà khách hàng đã thực hiện thuê dịch vụ
  (lưu ý chỉ xét các khoảng thời gian dựa vào từng lần làm hợp đồng thuê dịch vụ,
  không xét trên toàn bộ các lần làm hợp đồng). Mã của khách hàng được truyền vào như là 1 tham số của function này.
 */
 USE furama_manager;
DROP FUNCTION IF EXISTS func_dem_dich_vu;

DELIMITER //
CREATE FUNCTION func_dem_dich_vu() RETURNS int
BEGIN
    SELECT count(select_facility.facility_id) INTO @count_facility FROM
    (SELECT f.facility_id, f.facility_name, count(f.facility_id) as time_of_use FROM facility f
        INNER JOIN contract ctr on f.facility_id = ctr.facility_id
    GROUP BY f.facility_id,f.rental_fee
    HAVING time_of_use* f.rental_fee > 2000000) as select_facility ;
RETURN @count_facility;
END //
DELIMITER ;