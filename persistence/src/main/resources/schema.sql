CREATE TABLE IF NOT EXISTS users (
				userid SERIAL PRIMARY KEY,
				username VARCHAR (100),
				password VARCHAR (100)
);