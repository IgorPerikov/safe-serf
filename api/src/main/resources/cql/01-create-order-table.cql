CREATE TABLE IF NOT EXISTS order.orders (
    id uuid,
    url text,
    is_ready boolean,
 PRIMARY KEY (id))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };