select
    prod.product_code,
    prod.price * sum(sale.sales_amount) as sales
from
    product prod
    join offline_sale sale using(product_id)
group by
    sale.product_id
order by
    sales desc,
    prod.product_code