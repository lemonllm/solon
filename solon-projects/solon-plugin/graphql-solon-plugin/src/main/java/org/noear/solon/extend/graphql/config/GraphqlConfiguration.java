package org.noear.solon.extend.graphql.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.extend.graphql.execution.DefaultGraphQlSource;
import org.noear.solon.extend.graphql.execution.GraphQlSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fuzi1996
 * @since 2.3
 */
@Configuration
public class GraphqlConfiguration {

    private static Logger log = LoggerFactory.getLogger(GraphqlConfiguration.class);

    public GraphqlConfiguration() {
    }

    /**
     * 注入GraphQlSource
     */
    @Bean
    public GraphQlSource defaultGraphqlSource() {
        DefaultGraphQlSource defaultGraphQlSource = new DefaultGraphQlSource();
        log.debug("注册默认的 GraphQlSource");
        return defaultGraphQlSource;
    }
}
