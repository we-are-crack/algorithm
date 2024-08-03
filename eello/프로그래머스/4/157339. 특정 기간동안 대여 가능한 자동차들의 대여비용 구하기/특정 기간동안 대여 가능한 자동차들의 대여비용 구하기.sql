with nov_unavailable_car as (
    select car_id
    from car_rental_company_rental_history
    where
        start_date between "2022-11-01" and "2022-11-30"
        or
        end_date between "2022-11-01" and "2022-11-30"
        or (start_date < "2022-11-01" and end_date > "2022-11-30")
), nov_available_car as (
    select
        car.car_id,
        car.car_type,
        30 * car.daily_fee * (1 - (replace(plan.discount_rate, "%", "") / 100)) as fee
    from
        car_rental_company_car car
        join car_rental_company_discount_plan plan
        on
            car.car_id not in (select car_id from nov_unavailable_car) and
            car.car_type in ("SUV", "세단") and
            plan.duration_type = "30일 이상" and
            car.car_type = plan.car_type
)

select
    car_id,
    car_type,
    fee
from
    nov_available_car
where
    fee between 500000 and 2000000
order by
    fee desc,
    car_type,
    car_id desc