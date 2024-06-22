-- 코드를 입력하세요
with most_expensive_food as (
    select
        category, max(price) as price
    from
        food_product
    where
        category in ("과자", "국", "김치", "식용유")
    group by
        category
)

select
    category, price as max_price, product_name
from
    food_product
where
    (category, price) in (
        select category, price
        from most_expensive_food
    )
order by
    price desc