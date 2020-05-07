CREATE TABLE book (
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Title varchar(255) NOT NULL,
	Price numeric NOT NULL,	
	Warehouse_area varchar(50) NOT NULL,
	Quantity int 
) 

