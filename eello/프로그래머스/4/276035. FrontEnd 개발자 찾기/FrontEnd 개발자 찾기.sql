select
    distinct dev.id,
    dev.email,
    dev.first_name,
    dev.last_name
from
    developers dev
    join skillcodes skill
    on
        skill.category = "Front End" and
        (dev.skill_code & skill.code) = skill.code
order by
    dev.id