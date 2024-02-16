package com.mongodb.demo.config;

import graphql.scalars.ExtendedScalars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@Slf4j
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.Url);
    }

    @Bean
    GraphQlSourceBuilderCustomizer inspectionCustomizer() {
        return source -> source.inspectSchemaMappings(schemaReport -> log.info(schemaReport.toString()));
    }
}
