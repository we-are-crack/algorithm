-- 코드를 입력하세요
select
    usr.user_id,
    usr.nickname,
    sum(board.price) as total_sales
from used_goods_user usr
    join used_goods_board board
    on usr.user_id = board.writer_id
where
    board.status = 'DONE'
group by
    usr.user_id
having
    total_sales >= 700000
order by
    total_sales