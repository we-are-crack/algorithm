-- 코드를 입력하세요
with renting_car as (
    select car_id, history_id
    from car_rental_company_rental_history
    where "2022-10-16" between start_date and end_date
    group by car_id
), all_car as (
    select car_id
    from car_rental_company_rental_history
    group by car_id
)

select
    a.car_id,
    case
        when rc.history_id is null then "대여 가능"
        else "대여중"
    end as availability
from
    all_car a left outer join renting_car rc using(car_id)
order by
    a.car_id desc