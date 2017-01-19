CREATE TABLE IF NOT EXISTS orders.order_results (
    id uuid,
    url text,
 PRIMARY KEY (id))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };