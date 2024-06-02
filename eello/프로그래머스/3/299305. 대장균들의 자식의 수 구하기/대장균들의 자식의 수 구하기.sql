-- 코드를 작성해주세요
select
    ed1.id, if(ed2.parent_id is null, 0, count(*)) as child_count
from
    ecoli_data ed1
    left outer join ecoli_data ed2
    on ed1.id = ed2.parent_id
group by
    ed1.id
order by
    ed1.id