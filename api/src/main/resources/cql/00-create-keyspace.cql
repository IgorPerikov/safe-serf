CREATE KEYSPACE IF NOT EXISTS order
 WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1} AND durable_writes = true;