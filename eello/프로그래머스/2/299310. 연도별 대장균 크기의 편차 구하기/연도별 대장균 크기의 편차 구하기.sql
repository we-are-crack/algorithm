with max_size_year as (
    select
        year(differentiation_date) as 'year',
        max(size_of_colony) as 'max'
    from
        ecoli_data
    group by
        year(differentiation_date)
)

select
    m.year,
    (m.max - ec.size_of_colony) as 'year_dev',
    ec.id
from
    ecoli_data ec
    join max_size_year m on year(differentiation_date) = m.year
order by
    m.year,
    year_dev