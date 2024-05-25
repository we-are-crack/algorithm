-- 코드를 입력하세요
SELECT ai.name, ai.datetime
FROM animal_ins ai LEFT OUTER JOIN animal_outs ao USING(animal_id)
WHERE ao.animal_id is NULL
ORDER BY ai.datetime
LIMIT 3;