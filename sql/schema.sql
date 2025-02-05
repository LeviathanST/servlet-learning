CREATE TABLE IF NOT EXISTS account (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	"username" VARCHAR(255) NOT NULL,
	"password" VARCHAR(255) NOT NULL,
	-- 1: Customer
	-- 2: Staff 
	role INTEGER NOT NULL 
);
CREATE TABLE IF NOT EXISTS category (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	"name" VARCHAR(255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS product (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	"name" VARCHAR(255) NOT NULL,
	price FLOAT NOT NULL,
	product_year INTEGER NOT NULL,
	"image" VARCHAR(500) NOT NULL,
	category_id INTEGER NOT NULL,

	FOREIGN KEY (category_id) REFERENCES category(id)
);

