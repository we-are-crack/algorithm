select
    id,
    email,
    first_name,
    last_name
from
    developers
where
    skill_code & (
        select sum(code)
        from skillcodes
        where
            name in ('Python', 'C#')
    )
order by
    id