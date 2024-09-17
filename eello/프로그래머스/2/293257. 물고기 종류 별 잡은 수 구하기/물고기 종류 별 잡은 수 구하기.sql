select
    count(fi.id) as fish_count,
    fni.fish_name
from
    fish_info fi
    join fish_name_info fni using(fish_type)
group by
    fni.fish_name
order by
    fish_count desc