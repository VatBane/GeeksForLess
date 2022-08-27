CREATE TABLE expressions(
    id SERIAL PRIMARY KEY,
    expr VARCHAR(255) UNIQUE,
    res FLOAT
)