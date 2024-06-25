-- 코드를 입력하세요
select
    info.rest_id,
    info.rest_name,
    info.food_type,
    info.favorites,
    info.address,
    round(avg(review.review_score), 2) as score
from
    rest_info info
    join rest_review review using(rest_id)
where
    info.address like "서울%"
group by
    info.rest_id
order by
    score desc,
    info.favorites desc