select
    sum(grade.score) as score,
    emp.emp_no,
    emp.emp_name,
    emp.position,
    emp.email
from
    hr_employees emp
    join hr_grade grade on emp.emp_no = grade.emp_no
where
    year = 2022
group by
    emp_no
order by
    score desc
limit
    1