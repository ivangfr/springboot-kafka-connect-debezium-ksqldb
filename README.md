# springboot-kafka-debezium-ksql

The goal of this project is to play with [`Kafka`](https://kafka.apache.org), [`Debezium`](https://debezium.io/) and [`KSQL`](https://www.confluent.io/product/ksql/). For this, we have: `research-service` that inserts/updates/deletes records in [`MySQL`](https://www.mysql.com); `Source Connectors` that monitor change of records in MySQL and push messages related to those changes to Kafka; `Sink Connectors` and `kafka-research-consumer` that listen messages from Kafka and insert/update documents in [`Elasticsearch`](https://www.elastic.co); finally, `KSQL-Server` that listens some topics in Kafka, does some joins and pushes new messages to new topics in Kafka.

## Project Diagram

![project-diagram](images/project-diagram.png)

## Applications

- **research-service**

  Monolithic [`Spring Boot`](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) application that exposes a REST API to manage `Institutes`, `Articles`, `Researchers` and `Reviews`. The data is saved in `MySQL`.
  
  ![research-service-swagger](images/research-service-swagger.png)

- **kafka-research-consumer**

  `Spring Boot` application that listens messages from the topic `REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES` (that is one of `KSQL` outputs) and save the payload of those messages (i.e, reviews with detailed information) in `Elasticsearch`.

## Prerequisites

- [`Java 11+`](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [`Docker`](https://www.docker.com/)
- [`Docker-Compose`](https://docs.docker.com/compose/install/)

## Start Environment

- Open a terminal and inside `springboot-kafka-debezium-ksql` root folder run the following command
  ```
  docker-compose up -d
  ```
  > **Note:** During the first run, an image for `mysql` and `kafka-connect` will be built, whose names are `springboot-kafka-debezium-ksql_mysql` and `springboot-kafka-debezium-ksql_kafka-connect`, respectively. To rebuild those images run
  > ```
  > docker-compose build
  > ```

- Wait a little bit until all containers are `Up (healthy)`. To check the status of the containers run
  ```
  docker-compose ps
  ```
  
## Create Kafka Topics

In order to have topics in `Kafka` with more than `1` partition, we must create them manually and not wait for the connectors to create for us. So, for it:

- Open a new terminal and make sure you are in `springboot-kafka-debezium-ksql` root folder

- Run the script below
  ```
  ./create-kafka-topics.sh
  ```
  > **Note:** you can ignore the warnings

  It will create the topics `mysql.researchdb.institutes`, `mysql.researchdb.researchers`, `mysql.researchdb.articles` and `mysql.researchdb.reviews` with `5` partitions.

## Create connectors (3/4)

- In a terminal, make sure you are in `springboot-kafka-debezium-ksql` root folder

- Run the following `curl` commands to create one `Debezium` and two `Elasticsearch-Sink` connectors in `kafka-connect`
  ```
  curl -i -X POST localhost:8083/connectors -H 'Content-Type: application/json' -d @connectors/debezium-mysql-source-researchdb.json
  curl -i -X POST localhost:8083/connectors -H 'Content-Type: application/json' -d @connectors/elasticsearch-sink-institutes.json
  curl -i -X POST localhost:8083/connectors -H 'Content-Type: application/json' -d @connectors/elasticsearch-sink-articles.json
  ```

- You can check the state of the connectors and their tasks on `Kafka Connect UI` (http://localhost:8086) or calling `kafka-connect` endpoint
  ```
  curl localhost:8083/connectors/debezium-mysql-source-researchdb/status
  curl localhost:8083/connectors/elasticsearch-sink-institutes/status
  curl localhost:8083/connectors/elasticsearch-sink-articles/status
  ```

- The state of the connectors and their tasks must be `RUNNING`. If there is any problem, you can check `kafka-connect` container logs.
  ```
  docker logs kafka-connect
  ```

## Run research-service

- In a new terminal, make sure you are inside `springboot-kafka-debezium-ksql` root folder

- Run the command below to start the application
  ```
  ./mvnw clean spring-boot:run --projects research-service -Dspring-boot.run.jvmArguments="-Dserver.port=9080"
  ```
  > **Note:** It will create some articles, institutes and researchers. If you don't want it, just set to `false` the properties `load-samples.articles.enabled`, `load-samples.institutes.enabled` and `load-samples.researchers.enabled` in `application.yml`.

- The Swagger link is http://localhost:9080/swagger-ui.html

- **Important:** create at least one `review` so that `mysql.researchdb.reviews-key` and `mysql.researchdb.reviews-value` are created in `Schema Registry`. Below there is a request sample to create a review.
  ```
  curl -i -X POST localhost:9080/api/reviews \
    -H "Content-Type: application/json" \
    -d "{ \"researcherId\": 1, \"articleId\": 1, \"comment\": \"Ln 56: replace the 'a' by 'an'\"}"
  ```

## Run ksql-cli

- In a new terminal, inside `springboot-kafka-debezium-ksql` root folder, run the `docker` command below to start `ksql-cli`
  ```
  docker run -it --rm --name ksql-cli \
    --network springboot-kafka-debezium-ksql_default \
    -v $PWD/docker/ksql/researchers-institutes.ksql:/tmp/researchers-institutes.ksql \
    -v $PWD/docker/ksql/reviews-researchers-institutes-articles.ksql:/tmp/reviews-researchers-institutes-articles.ksql \
    confluentinc/cp-ksql-cli:5.4.1 http://ksql-server:8088
  ```
  
  This log should show, and the terminal will be waiting for user input
  ```
                    ===========================================
                    =        _  __ _____  ____  _             =
                    =       | |/ // ____|/ __ \| |            =
                    =       | ' /| (___ | |  | | |            =
                    =       |  <  \___ \| |  | | |            =
                    =       | . \ ____) | |__| | |____        =
                    =       |_|\_\_____/ \___\_\______|       =
                    =                                         =
                    =  Streaming SQL Engine for Apache Kafka® =
                    ===========================================
  
  Copyright 2017-2019 Confluent Inc.
  
  CLI v5.4.1, Server v5.4.1 located at http://ksql-server:8088
  
  Having trouble? Type 'help' (case-insensitive) for a rundown of how things work!
  
  ksql>
  ```

- On `ksql-cli` command line, run the following commands

  - Set `auto.offset.reset` value
    ```
    SET 'auto.offset.reset' = 'earliest';
    ```
  
  - Run the following script. It will create `RESEARCHERS_INSTITUTES` topic
    ```
    RUN SCRIPT '/tmp/researchers-institutes.ksql';
    ```
  
  - check whether the topic was created 
    ```
    DESCRIBE RESEARCHERS_INSTITUTES;
    SELECT * FROM RESEARCHERS_INSTITUTES EMIT CHANGES LIMIT 5;
    ```
  
  - Run the script below. It will create `REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES` topic
    ```
    RUN SCRIPT '/tmp/reviews-researchers-institutes-articles.ksql';
    ```
  
  - Check whether the topic was created
    ```
    DESCRIBE REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES;
    SELECT * FROM REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES EMIT CHANGES LIMIT 1;
    ```

## Create connectors (4/4)

- In a terminal, make sure you are in `springboot-kafka-debezium-ksql` root folder

- Run the `curl` command below to create `elasticsearch-sink-researchers` connector in `kafka-connect`
  ```
  curl -i -X POST localhost:8083/connectors -H 'Content-Type: application/json' -d @connectors/elasticsearch-sink-researchers.json
  ```

- You can check the state of the connector and its task on `Kafka Connect UI` (http://localhost:8086) or calling `kafka-connect` endpoint
  ```
  curl localhost:8083/connectors/elasticsearch-sink-researchers/status
  ```

## Run kafka-research-consumer

- Open a new terminal and navigate to `springboot-kafka-debezium-ksql` root folder

- Run the command below to start the application
  ```
  ./mvnw clean spring-boot:run --projects kafka-research-consumer -Dspring-boot.run.jvmArguments="-Dserver.port=9081"
  ```
  
- This service runs on port `9081`. The `health` endpoint is: http://localhost:9081/actuator/health

- \[Optional\] We can start another `kafka-research-consumer` instance by opening another terminal and running
  ```
  ./mvnw clean spring-boot:run --projects kafka-research-consumer -Dspring-boot.run.jvmArguments="-Dserver.port=9082"
  ```

## Testing

- Go to the terminal where `ksql-cli` is running. On `ksql-cli` command line, run the following query
  ```
  SELECT * FROM REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES EMIT CHANGES;
  ```

- In another terminal, call the `research-service` simulation endpoint
  ```
  curl -X POST localhost:9080/api/simulation/reviews \
    -H "Content-Type: application/json" \
    -d "{\"total\": 100, \"sleep\": 100}"
  ```

- The GIF below shows it

  ![ksql-select-example](images/ksql-select-example.gif)

- You can also query `Elasticsearch`
  ```
  curl "localhost:9200/reviews/_search?pretty"
  ```

## Useful Links/Commands

- **Kafka Topics UI**

  `Kafka Topics UI` can be accessed at http://localhost:8085

- **Kafka Connect UI**

  `Kafka Connect UI` can be accessed at http://localhost:8086

- **Schema Registry UI**

  `Schema Registry UI` can be accessed at http://localhost:8001

- **Schema Registry**

  You can use `curl` to check the subjects in `Schema Registry`

  - Get the list of subjects
    ```
    curl localhost:8081/subjects
    ```
  - Get the latest version of the subject `mysql.researchdb.researchers-value`
    ```
    curl localhost:8081/subjects/mysql.researchdb.researchers-value/versions/latest
    ```

- **Kafka Manager**

  `Kafka Manager` can be accessed at http://localhost:9000

  _Configuration_
  - First, you must create a new cluster. Click on `Cluster` (dropdown on the header) and then on `Add Cluster`
  - Type the name of your cluster in `Cluster Name` field, for example: `MyCluster`
  - Type `zookeeper:2181` in `Cluster Zookeeper Hosts` field
  - Enable checkbox `Poll consumer information (Not recommended for large # of consumers if ZK is used for offsets tracking on older Kafka versions)`
  - Click on `Save` button at the bottom of the page.

- **Elasticsearch**

  `Elasticsearch` can be accessed at http://localhost:9200

  - Get all indices
    ```
    curl "localhost:9200/_cat/indices?v"
    ```
  - Search for documents
    ```
    curl "localhost:9200/articles/_search?pretty"
    curl "localhost:9200/institutes/_search?pretty"
    curl "localhost:9200/researchers/_search?pretty"
    curl "localhost:9200/reviews/_search?pretty"
    ```

- **MySQL**

  ```
  docker exec -it mysql mysql -uroot -psecret --database researchdb
  SELECT a.id AS review_id, c.id AS article_id, c.title AS article_title, b.id AS reviewer_id, b.first_name, b.last_name, b.institute_id, a.comment \
    FROM reviews a, researchers b, articles c \
    WHERE a.researcher_id = b.id and a.article_id = c.id;
  ```
  > Type `exit` to leave `MySQL` terminal

## Shutdown

- Go to the terminals where `research-service` and `kafka-research-consumer` are running and press `Ctrl+C` to stop them
- Go to the terminal where `ksql-cli` is running and press `Ctrl+C` to stop the `SELECT` and type `exit`
- In a terminal and inside `springboot-kafka-debezium-ksql`, run the command below to stop and remove Docker containers, networks and volumes
  ```
  docker-compose down -v
  ```

## TODO

1. Create ES indices dynamically and add an `alias` for them.

1. Replace the deprecated `topic.index.map` configured in `elasticsearch-sink-*` connectors. Waiting for those `kafka-connect-elasticsearch` issues to be fixed:
   - `Create indices before writing records #261` https://github.com/confluentinc/kafka-connect-elasticsearch/pull/261
   - `ConnectException: Cannot create mapping when using RegexRouter/TimestampRouter SMT #99` https://github.com/confluentinc/kafka-connect-elasticsearch/issues/99

## References

- https://docs.confluent.io/current/ksql/docs/tutorials/basics-docker.html#ksql-quickstart-docker
