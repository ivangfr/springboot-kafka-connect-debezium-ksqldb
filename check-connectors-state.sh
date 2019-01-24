#!/usr/bin/env bash

echo "-------------------------------"
echo "Connector and their tasks state"
echo "-------------------------------"

curl localhost:8083/connectors/debezium-mysql-source-researchers/status

echo
curl localhost:8083/connectors/elasticsearch-sink-researchers/status

echo
echo "You can also use Kafka Connect UI, link http://localhost:8086"
echo