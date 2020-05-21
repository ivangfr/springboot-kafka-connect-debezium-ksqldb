FROM confluentinc/cp-kafka-connect:5.4.1

LABEL maintainer="ivangfr@yahoo.com.br"

WORKDIR /

RUN wget https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/1.1.1.Final/debezium-connector-mysql-1.1.1.Final-plugin.tar.gz -O /tmp/debezium-connector-mysql.tar.gz
RUN tar -xvzf /tmp/debezium-connector-mysql.tar.gz --directory /usr/share/java/
RUN rm /tmp/debezium-connector-mysql.tar.gz