package com.yay.linda.pixelstarshipscrew.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.mongo")
public class MongoProperties {

    private String host;
    private int port;
    private String databaseName;

    public String getHost() {
        return host;
    }

    public MongoProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public MongoProperties setPort(int port) {
        this.port = port;
        return this;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public MongoProperties setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }
}
