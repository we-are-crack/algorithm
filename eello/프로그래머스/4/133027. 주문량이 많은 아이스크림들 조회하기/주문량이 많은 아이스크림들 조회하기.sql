-- 코드를 입력하세요
select
    fh.flavor
from
    first_half fh
    join july on fh.flavor = july.flavor
group by
    fh.flavor
order by
    fh.total_order + sum(july.total_order) desc
limit 3