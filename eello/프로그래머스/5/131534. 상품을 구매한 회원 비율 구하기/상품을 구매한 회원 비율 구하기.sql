with joined_at_2021 as (
    select * from user_info
    where year(joined) = '2021'
)

select
    year(sales_date) as year,
    month(sales_date) as month,
    count(distinct u.user_id) as purchased_users,
    round(count(distinct u.user_id) / (select count(*) from joined_at_2021), 1) as purchased_ratio
from joined_at_2021 u
join online_sale os using(user_id)
group by year(sales_date), month(sales_date)
order by year, month