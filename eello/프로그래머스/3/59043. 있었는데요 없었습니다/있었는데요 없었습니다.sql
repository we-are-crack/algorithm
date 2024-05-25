-- 코드를 입력하세요
SELECT ai.animal_id, ai.name
FROM animal_ins ai JOIN animal_outs ao USING(animal_id)
WHERE ai.datetime > ao.datetime
ORDER BY ai.datetime;