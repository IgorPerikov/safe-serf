safe-serf
---------

preparation:
* download ```https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.1.0/kafka_2.11-0.10.1.0.tgz```
* execute ```./kafka-launch.sh``` from folder with kafka extracted
* execute ```./deploy-scripts/cassandra-launch.sh```
* execute ```docker exec -ti deployscripts_cassandra-1_1 cqlsh``` and execute all cql files one-by-one from api/src/main/resources/cql
* launch applications (watch appropriate readme's)