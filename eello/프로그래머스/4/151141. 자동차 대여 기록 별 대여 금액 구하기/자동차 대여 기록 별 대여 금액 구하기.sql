with truck_discount_plan as (
    select
        car_type,
        replace(duration_type, '일 이상', '') as duration_type,
        0.01 * replace(discount_rate, '%', '') as discount_rate
    from
        car_rental_company_discount_plan
    where car_type = '트럭'
), truck_history as (
    select
        his.history_id,
        car.car_id,
        timestampdiff(day, his.start_date, his.end_date) + 1 as duration,
        case
            when timestampdiff(day, his.start_date, his.end_date) + 1 >= 90 then 90
            when timestampdiff(day, his.start_date, his.end_date) + 1 >= 30 then 30
            when timestampdiff(day, his.start_date, his.end_date) + 1 >= 7 then 7
            else 0
        end as duration_type,
        car.car_type,
        car.daily_fee
    from
        car_rental_company_rental_history his
        join car_rental_company_car car on his.car_id = car.car_id and car.car_type = '트럭'
)

select
    his.history_id,
    truncate(his.duration * his.daily_fee * (1 - coalesce(dis.discount_rate, 0)), 0) as fee
from
    truck_history his
    left outer join truck_discount_plan dis on his.duration_type = dis.duration_type and his.car_type = dis.car_type
order by
    fee desc,
    his.history_id desc