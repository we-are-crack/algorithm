-- 코드를 입력하세요
with recursive cte_hour as (
    select 0 as hour
    union all
    select hour + 1
    from cte_hour
    where hour < 23
)

select
    hour.hour,
    count(animal_id) as 'count'
from
    animal_outs outs
    right outer join cte_hour hour on hour(outs.datetime) = hour.hour
group by
    hour.hour
order by
    hour.hour