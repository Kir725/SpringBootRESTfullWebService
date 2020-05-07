CREATE VIEW sale_with_cost AS 
	SELECT s.*,c.discount/100*(b.price+(b.price*store.commission/100)) AS sale_cost
	FROM sale s JOIN book b ON s.book = b.book_id 
				JOIN customer c ON s.customer = c.customer_id
					JOIN store ON s.seller = store.store_id;
CREATE TRIGGER view_sale_update_tg
    INSTEAD OF INSERT OR DELETE OR UPDATE 
    ON public.sale_with_cost
    FOR EACH ROW
    EXECUTE PROCEDURE public.update_sale_with_cost_view();


