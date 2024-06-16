-- 코드를 작성해주세요
with temp as (
    select
        id,
        fish_type,
        case
            when length is null then 10
            else length
        end as length,
        time
    from
        fish_info
)

select
    count(id) as fish_count,
    max(length) as max_length,
    fish_type
from
    temp
group by
    fish_type
having
    avg(length) >= 33
order by
    fish_type