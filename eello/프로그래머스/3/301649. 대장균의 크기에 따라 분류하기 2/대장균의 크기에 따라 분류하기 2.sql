-- 코드를 작성해주세요
select
    id,
    case
        when pr <= 0.25 then "CRITICAL"
        when pr <= 0.50 then "HIGH"
        when pr <= 0.75 then "MEDIUM"
        else "LOW"
    end as colony_name
from
    (
        select id, percent_rank() over(order by size_of_colony desc) as pr
        from ecoli_data
    ) as ranked
order by
    id