-- 코드를 작성해주세요
select
    emp.emp_no,
    emp.emp_name,
    case 
        when avg(grade.score) >= 96 then 'S'
        when avg(grade.score) >= 90 then 'A'
        when avg(grade.score) >= 80 then 'B'
        else 'C'
    end as grade,
    case 
        when avg(grade.score) >= 96 then sal * 0.2
        when avg(grade.score) >= 90 then sal * 0.15
        when avg(grade.score) >= 80 then sal * 0.1
        else 0
    end as bonus
from
    hr_employees emp
    join hr_grade grade using(emp_no)
group by
    emp.emp_no
order by
    emp.emp_no