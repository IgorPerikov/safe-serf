CREATE TABLE IF NOT EXISTS orders.tokens (
    token_id text,
    login text,
    hashed_password text,
    balance int,
 PRIMARY KEY (token_id))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };
