CREATE TABLE IF NOT EXISTS orders.order_results (
    id uuid,
    url text,
    status text,
    check_date timestamp,
 PRIMARY KEY (id))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };
