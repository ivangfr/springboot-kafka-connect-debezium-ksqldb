# springboot-kafka-debezium-ksql

## Goal

The goal of this project is to play with [`Kafka`](https://kafka.apache.org), [`Debezium`](https://debezium.io/) and
[`KSQL`](https://www.confluent.io/product/ksql/). For this, we have: `research-service` that inserts/updates/deletes
records in [`MySQL`](https://www.mysql.com); `Source Connectors` that monitor inserted/updated/deleted records in MySQL
and push messages related to those changes to Kafka; `Sink Connectors` and `Spring-boot apps consumers` that read
messages from Kafka and insert/update documents in [`Elasticsearch`](https://www.elastic.co); finally, `KSQL-Server`
that listens some topics in Kafka, does some joins and pushes new messages to new topics in Kafka.

## Microservices

![project-diagram](images/project-diagram.png)

### research-service

Monolithic spring-boot application that exposes a REST API to manage Institutes, Articles, Researchers and Reviews.
The data is saved in MySQL. Besides, if the service is run with the profile `simulation`, it will _simulate_ an
automatic and random creation of reviews.

### kafka-research-consumer

Spring-boot application that listens messages from the topic `REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES` (that is one of
`KSQL` outputs) and save the payload of those messages (reviews) in `Elasticsearch`.

## Start Environment

### Docker Compose

1. Open one terminal

2. Inside `/springboot-kafka-debezium-ksql` root folder run

```
docker-compose up -d
```
> During the first run, an image for `mysql` and `kafka-connect`will be built, whose names are
> `springboot-kafka-debezium-ksql_mysql` and `springboot-kafka-debezium-ksql_kafka-connect`, respectively.
> To rebuild those images run
> ```
> docker-compose build
> ```
> To stop and remove containers, networks and volumes type:
> ```
> docker-compose down -v
> ```

3. Wait a little bit until all containers are `Up (healthy)`. To check the status of the containers run the command
```
docker-compose ps
```

### Create connectors

1. In a terminal, run the following script to create the connectors on `kafka-connect`
```
./create-connectors.sh
```

2. You can check the state of the connectors and their tasks on `Kafka Connect UI` (http://localhost:8086) or
running the following script
```
./check-connectors-state.sh
```

### Run research-service

There are two ways to run `research-service`, using Swagger or batch simulation

#### Swagger

In a new terminal, run the command below inside `/springboot-kafka-debezium-ksql/research-service`. It will start
the service as a REST API
```
./mvnw spring-boot:run
```
The swagger link is http://localhost:9080/swagger-ui.html

> **Note**: if you pick this way, create at least one `review` on `Review-Controller` -> `POST /api/reviews` so that
> the topic `mysql.researchdb.reviews` is created on Kafka. Otherwise, you can an exception on running `ksql-cli`,
> explained in the next sections. The exception looks like:
> ```
> io.confluent.ksql.parser.exception.ParseFailedException: Exception while processing statement: Avro schema for message
> values on topic mysql.researchdb.reviews does not exist in the Schema Registry.
> ```

#### Simulation

Inside `/springboot-kafka-debezium-ksql/research-service`, you can run the simulation
```
# Using default values (reviews.total=10 and reviews.delay-interval=0)
./mvnw spring-boot:run -Dspring-boot.run.profiles=simulation

# Changing values
./mvnw spring-boot:run \
  -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=simulation -Dreviews.total=100 -Dreviews.delay-interval=0"
```
This mode will create automatically and randomly a certain number of reviews.
- `reviews.total`: total number of reviews you want to be created;
- `reviews.delay-interval`: delay between the creation of reviews.

### Run ksql-cli

1. Run the following command to start `ksql-cli`
```
docker run -it --rm --name ksql-cli \
  --network springboot-kafka-debezium-ksql_default \
  --volume $PWD/docker/ksql/create.research.streams.tables:/tmp/ksql/create.research.streams.tables \
  confluentinc/cp-ksql-cli:5.1.0 http://ksql-server:8088
```

2. Inside `ksql-cli`, run script to create streams and tables
```
RUN SCRIPT '/tmp/ksql/create.research.streams.tables';
```

3. Some commands to check if everything is set
```
SET 'auto.offset.reset' = 'earliest';

DESCRIBE REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES;
SELECT * from REVIEWS_RESEARCHERS_INSTITUTES_ARTICLES;
```

### Run kafka-research-consumer

In a new terminal, run the command below inside `/springboot-kafka-debezium-ksql/kafka-research-consumer`
```
./mvnw spring-boot:run
```

## Useful links & commands

### MySQL
```
docker exec -it mysql bash -c 'mysql -uroot -psecret'
use researchdb
SELECT a.id AS review_id, c.id AS article_id, c.title AS article_title, b.id AS reviewer_id, b.first_name, b.last_name, b.institute_id, a.comment \
  FROM reviews a, researchers b, articles c \
  WHERE a.researcher_id = b.id and a.article_id = c.id;
```

### Elasticsearch
```
curl localhost:9200/_cat/indices?v
curl localhost:9200/institutes/_search?pretty
curl localhost:9200/articles/_search?pretty
curl localhost:9200/reviews/_search?pretty
```

## TODO

1. Create indices dynamically and add an `alias` for them. 

2. Create `RESEARCHERS_INSTITUTES` stream in `ksql.commands` file.

3. Configure `Elasticsearch Sink Connector` to listen successfully from the topics `RESEARCHERS_INSTITUTES`.

4. replace the deprecated `topic.index.map` configured in `elasticsearch-sink-*` connectors:
Waiting for those `kafka-connect-elasticsearch` issues to be fixed:
- `Create indices before writing records #261` https://github.com/confluentinc/kafka-connect-elasticsearch/pull/261
- `ConnectException: Cannot create mapping when using RegexRouter/TimestampRouter SMT #99` https://github.com/confluentinc/kafka-connect-elasticsearch/issues/99

## References

- https://docs.confluent.io/current/ksql/docs/tutorials/basics-docker.html#ksql-quickstart-docker