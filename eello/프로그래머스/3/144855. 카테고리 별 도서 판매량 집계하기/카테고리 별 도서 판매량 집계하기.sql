-- 코드를 입력하세요
SELECT b.category, sum(bs.sales) as total_sales
FROM book b join book_sales bs on b.book_id = bs.book_id
WHERE bs.sales_date like '2022-01%'
GROUP BY b.category
ORDER BY b.category;