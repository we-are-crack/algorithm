-- 코드를 입력하세요
select
    usr.user_id,
    usr.nickname,
    concat_ws(" ", usr.city, usr.street_address1, usr.street_address2) as 전체주소,
    concat(substr(usr.tlno, 1, 3), "-", substr(usr.tlno, 4, 4), "-", substr(usr.tlno, 8, 4)) as 전화번호
from
    used_goods_board board join used_goods_user usr on board.writer_id = usr.user_id
group by
    usr.user_id
having
    count(*) >= 3
order by
    usr.user_id desc