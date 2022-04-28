FROM confluentinc/cp-kafka-connect:7.0.1

LABEL maintainer="ivangfr@yahoo.com.br"

USER root
RUN yum install unzip -y
RUN mkdir -p /usr/share/java/

WORKDIR /

# debezium-connector-mysql
RUN wget https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/1.9.1.Final/debezium-connector-mysql-1.9.1.Final-plugin.tar.gz -O /tmp/debezium-connector-mysql.tar.gz \
&& tar -xvzf /tmp/debezium-connector-mysql.tar.gz --directory /usr/share/java/ \
&& rm /tmp/debezium-connector-mysql.tar.gz

# confluentinc-kafka-connect-elasticsearch
ADD confluentinc-kafka-connect-elasticsearch-11.1.10.zip /tmp/confluentinc-kafka-connect-elasticsearch.zip
RUN unzip /tmp/confluentinc-kafka-connect-elasticsearch.zip -d /usr/share/java/ \
&& rm /tmp/confluentinc-kafka-connect-elasticsearch.zip
