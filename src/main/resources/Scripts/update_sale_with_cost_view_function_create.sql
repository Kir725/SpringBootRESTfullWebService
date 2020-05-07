CREATE OR REPLACE FUNCTION update_sale_with_cost_view() RETURNS TRIGGER AS $$
    BEGIN        
			IF (TG_OP = 'DELETE') THEN
				DELETE FROM sale WHERE sale.id = OLD.id;
				return OLD;
			ELSIF (TG_OP = 'UPDATE') THEN
				UPDATE sale SET sale_date = NEW.sale_date,
								seller = NEW.seller,
								customer = NEW.customer,
								book = NEW.book,
								quantity = NEW.quantity
				WHERE id = OLD.id;								
				RETURN NEW;
			ELSIF (TG_OP = 'INSERT') THEN
				INSERT INTO sale(sale_date,seller,customer,book,quantity) VALUES(NEW.sale_date,NEW.seller,NEW.customer,NEW.book,NEW.quantity);
				return NEW;
			END IF;
    END;
$$ LANGUAGE plpgsql;