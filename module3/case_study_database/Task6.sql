USE furama_manager;
SELECT DISTINCT f.facility_id, f.facility_name, f.floor_square, f.rental_fee, ft.value
FROM facility f
         LEFT JOIN facility_type ft ON f.facility_type_id = ft.facility_type_id
         LEFT JOIN contract ct ON f.facility_id = ct.facility_id
WHERE f.facility_id NOT IN (SELECT contract.facility_id FROM contract WHERE contract.date_signed BETWEEN '2021-01-01' AND '2021-03-31')

