-- 코드를 입력하세요
select
    apnt.apnt_no,
    pt.pt_name,
    pt.pt_no,
    apnt.mcdp_cd,
    dr.dr_name,
    apnt.apnt_ymd
from
    appointment apnt
    join patient pt on apnt.pt_no = pt.pt_no
    join doctor dr on apnt.mddr_id = dr.dr_id
where
    dr.mcdp_cd = "CS"
    and
    apnt.apnt_ymd like "2022-04-13%"
    and
    apnt.apnt_cncl_yn = "N"
order by
    apnt.apnt_ymd