-- 코드를 입력하세요
with history as (
    select
        car_id, month(start_date) as month
    from
        car_rental_company_rental_history
    where
        month(start_date) between 8 and 10
), limit_cond as (
    select
        car_id
    from
        history
    group by
        car_id
    having
        count(*) >= 5
)

select
    month, car_id, count(*) as records
from
    history
where
    car_id in (
        select
            car_id
        from
            limit_cond
    )
group by
    month, car_id
having
    count(*) > 0
order by
    month,
    car_id desc