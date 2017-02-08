CREATE TABLE IF NOT EXISTS orders.tokens (
    token_id text,
    login text,
    hashed_password text,
    maximum_balance int,
    current_balance int,
 PRIMARY KEY (token_id))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };
