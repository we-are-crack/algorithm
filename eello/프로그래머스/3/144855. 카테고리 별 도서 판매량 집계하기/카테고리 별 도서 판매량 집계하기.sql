-- 코드를 입력하세요
SELECT b.category, sum(bs.sales) as total_sales
FROM book b join book_sales bs on b.book_id = bs.book_id
WHERE year(bs.sales_date) = 2022 and month(bs.sales_date) = 1
GROUP BY b.category
ORDER BY b.category;