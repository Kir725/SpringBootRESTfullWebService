CREATE TABLE store(
	id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Name varchar(50) NOT NULL,
	Neighborhood varchar(50) NOT NULL,
	Commission numeric NOT NULL
)
