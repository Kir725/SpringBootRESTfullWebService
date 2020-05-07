CREATE TABLE customer (
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	SecondName varchar(50) NOT NULL,
	Neighborhood varchar(50) NOT NULL,
	Discount numeric
)