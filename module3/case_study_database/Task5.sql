-- ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien
USE furama_manager;
SELECT c.customer_id,
       c.name,
       ct.value,
       ctr.contract_id,
       ctr.date_signed,
       ctr.date_end,
       f.facility_name,
       sum(f.rental_fee + coalesce(cd.amount * si.price, 0)) AS tong_tien
FROM customer c
         LEFT JOIN customer_type ct ON c.customer_type_id = ct.customer_type_id
         LEFT JOIN contract ctr ON c.customer_id = ctr.customer_id
         LEFT JOIN facility f ON ctr.facility_id = f.facility_id
         LEFT JOIN contract_detail cd ON ctr.contract_id = cd.contract_id
         LEFT JOIN service_include si ON cd.service_include_id = si.service_include_id
GROUP BY ctr.contract_id, c.customer_id;
