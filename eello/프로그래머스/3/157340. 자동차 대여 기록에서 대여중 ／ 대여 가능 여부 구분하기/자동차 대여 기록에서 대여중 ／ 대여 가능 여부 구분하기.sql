-- 코드를 입력하세요
with renting_car as (
    select car_id, 1 as availability
    from car_rental_company_rental_history
    where "2022-10-16" between start_date and end_date
    group by car_id
)

select
    history.car_id,
    case
        when max(rc.availability) = 1 then "대여중"
        else "대여 가능"
    end as availability
from
    car_rental_company_rental_history history
    left join renting_car rc using(car_id)
group by
    history.car_id
order by
    history.car_id desc