with fe as (
    select
        sum(code) as code
    from
        skillcodes
    where
        category = 'Front End'
), dev_grade as (
    select
        case
            when 
                skill_code & (select * from fe)
                and skill_code & (select code from skillcodes where name = 'Python')
            then 'A'
            when
                skill_code & (select code from skillcodes where name = 'C#')
            then 'B'
            when
                skill_code & (select * from fe)
            then 'C'
        end as grade,
        id,
        email
    from
        developers
)

select
    grade,
    id,
    email
from
    dev_grade
where
    grade is not null
order by
    grade,
    id

