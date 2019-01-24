# springboot-kafka-debezium-ksql

```
docker-compose up -d --build
```

```
./create-connectors.sh
```

```
curl localhost:9200/_cat/indices?v
curl localhost:9200/mysql.researchdb.institutes/_search?pretty
curl localhost:9200/mysql.researchdb.researchers/_search?pretty
```