select
    info.ingredient_type,
    sum(fh.total_order) as total_order
from
    first_half fh
    join icecream_info info using(flavor)
group by
    info.ingredient_type
order by
    total_order