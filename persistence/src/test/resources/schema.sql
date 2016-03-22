CREATE TABLE IF NOT EXISTS users (
				userId integer SERIAL PRIMARY KEY,
				username varchar (100),
				password varchar (100)
)