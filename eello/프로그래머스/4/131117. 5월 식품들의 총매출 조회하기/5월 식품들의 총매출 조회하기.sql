-- 코드를 입력하세요
with may_order_total_amount as (
    select
        product_id,
        sum(amount) as total_amount
    from
        food_order
    where
        produce_date like "2022-05%"
    group by
        product_id
)

select
    fp.product_id,
    fp.product_name,
    fp.price * mota.total_amount as total_sales
from
    food_product fp
    join may_order_total_amount mota using(product_id)
order by
    total_sales desc,
    fp.product_id