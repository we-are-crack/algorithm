select
    concat(ceil(month(differentiation_date) / 3), "Q") as quarter,
    count(*) as ecoli_count
from
    ecoli_data
group by
    quarter
order by
    quarter