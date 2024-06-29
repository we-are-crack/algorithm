-- 코드를 입력하세요
with jan_sales as (
    select
        book.book_id, book.category, book.author_id, author.author_name, book.price * sum(sales.sales) as sales
    from
        book
        join author on book.author_id = author.author_id
        join book_sales sales on book.book_id = sales.book_id and sales.sales_date like '2022-01%'
    group by
        book.book_id, book.category, book.author_id
)

select
    author_id, author_name, category, sum(sales) as total_sales
from
    jan_sales
group by
    author_id, category
order by
    author_id, category desc