-- 코드를 입력하세요
with most_written as (
    select member_id
    from rest_review
    group by member_id
    order by count(review_id) desc
    limit 1
)

select
    prof.member_name,
    review.review_text,
    date_format(review.review_date, "%Y-%m-%d") as review_date
from
    member_profile prof
    join rest_review review on prof.member_id = (
        select member_id
        from most_written
    ) and prof.member_id = review.member_id
order by
    review.review_date,
    review.review_text