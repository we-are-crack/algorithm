-- 코드를 입력하세요
select ai.animal_id, ai.name
from animal_ins ai join animal_outs ao using(animal_id)
order by datediff(ao.datetime, ai.datetime) desc
limit 2;