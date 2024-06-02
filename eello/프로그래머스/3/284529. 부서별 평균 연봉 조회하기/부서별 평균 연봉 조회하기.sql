-- 코드를 작성해주세요
select
    dep.dept_id, dep.dept_name_en, round(avg(emp.sal), 0) as avg_sal
from
    hr_department dep join hr_employees emp using(dept_id)
group by
    dep.dept_id
order by
    avg_sal desc