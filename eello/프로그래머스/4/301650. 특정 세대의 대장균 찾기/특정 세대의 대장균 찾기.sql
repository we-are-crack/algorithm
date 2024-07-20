-- 코드를 작성해주세요
select
    id
from
    ecoli_data
where
    parent_id in (
        select
            c.id
        from
            ecoli_data p
            join ecoli_data c on p.id = c.parent_id and p.parent_id is null
    )    