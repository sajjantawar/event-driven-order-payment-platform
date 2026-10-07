CREATE TABLE orders (
 id UUID PRIMARY KEY,
 customer_id VARCHAR(80) NOT NULL,
 total_amount NUMERIC(19,2) NOT NULL,
 currency VARCHAR(3) NOT NULL,
 status VARCHAR(30) NOT NULL,
 created_at TIMESTAMPTZ NOT NULL,
 version BIGINT NOT NULL DEFAULT 0
);
CREATE INDEX idx_orders_customer_created ON orders(customer_id, created_at DESC);