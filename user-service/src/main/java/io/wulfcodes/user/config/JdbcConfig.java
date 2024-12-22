package io.wulfcodes.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories("io.wulfcodes.user.repository")
public class JdbcConfig {}
