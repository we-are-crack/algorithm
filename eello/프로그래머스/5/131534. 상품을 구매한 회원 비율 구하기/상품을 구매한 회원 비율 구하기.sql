with joined_at_2021 as (
    select * from user_info
    where joined >= '2021-01-01' and joined < '2022-01-01'
),
total_user_count as (
    select count(*) as total_cnt from joined_at_2021
)

select
    year(os.sales_date) as year,
    month(os.sales_date) as month,
    count(distinct u.user_id) as purchased_users,
    round(count(distinct u.user_id) / t.total_cnt, 1) as purchased_ratio
from joined_at_2021 u
join online_sale os using(user_id)
cross join total_user_count t
group by year(os.sales_date), month(os.sales_date)
order by year, month