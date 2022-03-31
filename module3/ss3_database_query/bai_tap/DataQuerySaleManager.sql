use sale_manager;

-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select o.order_id, o.order_date, p.product_name, p.product_price
from `order` as o
         inner join order_detail od on o.order_id = od.order_id
         inner join product p on od.product_id = p.product_id;
         
-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select c.customer_name, p.product_name
from customer as c
		inner join `order` as o on o.customer_id = c.customer_id
		inner join `order_detail` as od on od.order_id = o.order_id
		inner join `product` as p on p.product_id = od.product_id;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select c.customer_name, o.order_id
from customer as c
		left join `order` as o on o.customer_id = c.customer_id
where o.order_id is null;

-- Hiển thị mã hóa đơn, ngày bán và Tong giá tiền của từng hóa đơn
select o.order_id, o.order_date, sum(p.product_price*od.order_quantity) as total_of_order
from `order` as o
		inner join `order_detail` as od on od.order_id = o.order_id
		inner join `product` as p on p.product_id = od.product_id
        group by od.order_id;