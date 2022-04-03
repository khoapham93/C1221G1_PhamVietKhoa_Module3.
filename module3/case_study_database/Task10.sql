/**
  10.	Hiển thị thông tin tương ứng với từng hợp đồng thì đã sử dụng bao nhiêu dịch vụ đi kèm.
  Kết quả hiển thị bao gồm ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, so_luong_dich_vu_di_kem
  (được tính dựa trên việc sum so_luong ở dich_vu_di_kem).
 */

USE furama_manager;
SELECT c.contract_id, coalesce(sum(cd.amount), 0) AS total_service_used
FROM contract c
         LEFT JOIN contract_detail cd ON c.contract_id = cd.contract_id
GROUP BY c.contract_id;
