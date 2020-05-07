CREATE TABLE sale(
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Sale_date date NOT NULL,
	Seller int NOT NULL REFERENCES store(store_id),
	Customer int NOT NULL REFERENCES customer(customer_id),
	Book int NOT NULL REFERENCES book(book_id),
	Quantity int NOT NULL,
	Sale_cost numeric NOT NULL
)

