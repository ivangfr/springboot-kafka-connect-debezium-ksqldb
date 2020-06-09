#!/usr/bin/env bash

echo
echo "Create topic mysql.researchdb.institutes"
echo "----------------------------------------"
docker exec -t zookeeper kafka-topics --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 5 --topic mysql.researchdb.institutes

echo
echo "Create topic mysql.researchdb.researchers"
echo "-----------------------------------------"
docker exec -t zookeeper kafka-topics --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 5 --topic mysql.researchdb.researchers

echo
echo "Create topic mysql.researchdb.articles"
echo "--------------------------------------"
docker exec -t zookeeper kafka-topics --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 5 --topic mysql.researchdb.articles

echo
echo "Create topic mysql.researchdb.reviews"
echo "-------------------------------------"
docker exec -t zookeeper kafka-topics --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 5 --topic mysql.researchdb.reviews

echo
echo "List topics"
echo "-----------"
docker exec -t zookeeper kafka-topics --list --bootstrap-server kafka:9092