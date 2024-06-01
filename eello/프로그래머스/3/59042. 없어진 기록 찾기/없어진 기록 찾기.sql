-- 코드를 입력하세요
select
    outs.animal_id, outs.name
from
    animal_outs outs left outer join animal_ins ins using(animal_id)
where
    ins.animal_id is null