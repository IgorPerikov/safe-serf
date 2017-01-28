CREATE TABLE IF NOT EXISTS orders.orders (
    id uuid,
    url text,
 PRIMARY KEY (id))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };