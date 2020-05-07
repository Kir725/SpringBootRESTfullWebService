CREATE TABLE book (
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Title varchar(255) NOT NULL,
	Price numeric NOT NULL,	
	Warehouse_area varchar(50) NOT NULL,
	Quantity int 
);

INSERT INTO book (title,price,warehouse_area,quantity) VALUES
('Библия',1500,'Московский', 5000),
('Война и мир',1000,'Нижегородский', 250),
('Мастер и Маргарита',800,'Сормовский', 100),
('Внутреннее устройство Windows',2000,'Ленинский', 5),
('Windows 10. Новейший самоучитель',1800,'Приокский', 15),
('Псалтирь 1642 года',22000,'Канавинский', 1),
('Раскраска',40000,'Советский', 10),
('Гордость и предубеждение',238,'Московский', 50),
('Степной волк',800,'Нижегородский', 15),
('Обломов',150,'Советский', 150);

CREATE TABLE customer (
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	SecondName varchar(50) NOT NULL,
	Neighborhood varchar(50) NOT NULL,
	Discount numeric
);

INSERT INTO customer (secondname,neighborhood,discount) VALUES
('Трусов','Московский', 5),
('Резнов','Нижегородский', 10),
('Тюрина','Сормовский', 15),
('Леванов','Ленинский', 5),
('Усова','Приокский', 7),
('Астапов','Канавинский', 3),
('Попова','Советский', 10),
('Дуреев','Московский', 5),
('Михайлов','Нижегородский', 15),
('Смирнов','Советский', 5);

CREATE TABLE store(
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Name varchar(50) NOT NULL,
	Neighborhood varchar(50) NOT NULL,
	Commission numeric NOT NULL
);

INSERT INTO store (name,neighborhood,commission) VALUES
('Дом книги','Московский',5),
('Дирижабль','Советский',5),
('Читайна','Сормоский', 7),
('Читай город','Ленинский',10),
('Книга','Канавинский',5),
('Читайна','Московский',7);

CREATE TABLE sale(
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Sale_date date NOT NULL,
	Seller int NOT NULL REFERENCES store(id),
	Customer int NOT NULL REFERENCES customer(id),
	Book int NOT NULL REFERENCES book(id),
	Quantity int NOT NULL
);

INSERT INTO public.sale(sale_date, seller, customer, book, quantity)
	VALUES 
	('2019-04-12',1,2,4,2),
	('2019-06-19',2,3,1,200),
	('2019-10-27',3,4,2,1),
	('2020-01-08',6,1,8,1),
	('2020-02-07',5,5,5,6),
	('2020-03-03',4,6,7,2),
	('2020-03-07',3,7,3,10),
	('2020-03-20',2,5,10,1),
	('2020-04-01',2,8,4,1),
	('2020-04-12',5,9,6,1),
	('2020-04-18',1,10,9,4),
	('2019-11-03',2,7,7,1),
	('2019-10-12',2,10,2,1);

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

CREATE VIEW sale_with_cost AS 
	SELECT s.*,c.discount/100*(b.price+(b.price*store.commission/100)) AS sale_cost
	FROM sale s JOIN book b ON s.book = b.id 
				JOIN customer c ON s.customer = c.id
					JOIN store ON s.seller = store.id;
CREATE TRIGGER view_sale_update_tg
    INSTEAD OF INSERT OR DELETE OR UPDATE 
    ON public.sale_with_cost
    FOR EACH ROW
    EXECUTE PROCEDURE public.update_sale_with_cost_view();













 

