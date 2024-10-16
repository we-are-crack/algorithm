select
    count(*) as FISH_COUNT
from
    fish_info info
    join fish_name_info name using(fish_type)
where
    name.fish_name in ('BASS', 'SNAPPER')