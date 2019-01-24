#!/usr/bin/env bash

echo "----------------------"
echo "Creating connector ..."
echo "----------------------"

echo
curl -i -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -d @connectors/debezium-mysql-source-researchers.json

echo
curl -i -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -d @connectors/elasticsearch-sink-researchers.json

echo
echo "--------------------------------------------------------------"
echo "Check state of connectors and their tasks by running script check-connectors-state.sh or at Kafka Connect UI, link http://localhost:8086"
echo "--------------------------------------------------------------"