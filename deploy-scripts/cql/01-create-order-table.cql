CREATE TABLE IF NOT EXISTS orders.orders (
    id uuid,
    url text,
    created_at timestamp,
    is_ready boolean,
 PRIMARY KEY (id))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };