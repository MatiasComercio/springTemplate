CREATE TABLE IF NOT EXISTS users (
				userId integer IDENTITY PRIMARY KEY,
				username varchar (100),
				password varchar (100)
)