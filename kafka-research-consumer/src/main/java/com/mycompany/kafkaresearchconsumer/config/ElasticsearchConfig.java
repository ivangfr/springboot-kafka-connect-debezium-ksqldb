package com.mycompany.kafkaresearchconsumer.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.port}")
    private Integer elasticsearchPort;

    @Value("${elasticsearch.cluster-name}")
    private String elasticsearchClusterName;

    @Bean
    Client client() throws UnknownHostException {
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(elasticsearchHost), elasticsearchPort);
        Settings settings = Settings.builder().put("cluster.name", elasticsearchClusterName).build();
        return new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);
    }

}