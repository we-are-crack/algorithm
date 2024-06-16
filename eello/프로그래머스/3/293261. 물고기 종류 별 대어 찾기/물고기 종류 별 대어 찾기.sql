-- 코드를 작성해주세요
with biggest_fish as (
    select
        fish_type,
        max(length) as max_length
    from
        fish_info
    group by
        fish_type
)

select
    fi.id as id,
    fni.fish_name as fish_name,
    fi.length as length
from
    biggest_fish bf,
    fish_info fi
    join fish_name_info fni using(fish_type)
where
    fi.fish_type = bf.fish_type and fi.length = bf.max_length
order by
    fi.id