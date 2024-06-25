-- 코드를 입력하세요
select
    year(sale.sales_date) as year,
    month(sale.sales_date) as month,
    usr.gender,
    count(distinct usr.user_id) as users
from
    user_info usr
    join online_sale sale on usr.user_id = sale.user_id and usr.gender is not null
group by
    year(sale.sales_date),
    month(sale.sales_date),
    usr.gender
order by
    year,
    month,
    gender