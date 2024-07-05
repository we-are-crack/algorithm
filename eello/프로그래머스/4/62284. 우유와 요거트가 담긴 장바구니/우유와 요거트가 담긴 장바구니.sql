-- 코드를 입력하세요
select cart_id
from cart_products
where
    cart_id in (
        select cart_id
        from cart_products
        where name = "Milk"
    )
    and
    name = "Yogurt"
order by
    cart_id