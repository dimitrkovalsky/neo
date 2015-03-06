package com.liberty.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

/**
 * User: Dmytro_Kovalskyi
 * Date: 03.03.2015
 * Time: 14:22
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "org.springframework.data.neo4j.repository")
public class SpringConfig extends Neo4jConfiguration {

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new SpringRestGraphDatabase("http://localhost:7474/db/data/");
    }
}
