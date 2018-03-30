#! /bin/bash

#Provide Kafka, Elastic Installed Location
export kafkaLocation=/usr/local/Cellar/kafka/1.0.0/bin/
export elasticLocation=/usr/local/Cellar/elasticsearch-2.2.0/bin
export kafkatopicName=wordcounttopic

zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
kafka-server-start /usr/local/etc/kafka/server.properties

$elasticLocation/elasticsearch

echo 'Kafka Elasticsearch Services started'

/usr/local/Cellar/kafka/1.0.0/bin/kafka-console-producer \
--broker-list localhost:9092 --topic wordcounttopic