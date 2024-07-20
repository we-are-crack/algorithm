-- 코드를 작성해주세요

-- 재귀를 사용한 풀이
with recursive gen as (
    select
        1 as gen,
        id
    from
        ecoli_data
    where
        parent_id is null
    
    union all
    
    select
        gen + 1,
        child.id
    from
        ecoli_data child
        join gen parent on parent.id = child.parent_id
    where
        gen < 3
)

select id
from gen
where gen.gen = 3
order by id

-- 서브쿼리를 사용한 풀이
# select
#     id
# from
#     ecoli_data
# where
#     parent_id in (
#         select
#             c.id
#         from
#             ecoli_data p
#             join ecoli_data c on p.id = c.parent_id and p.parent_id is null
#     )