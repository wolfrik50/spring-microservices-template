package io.wulfcodes.hotel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("io.wulfcodes.hotel.repository")
public class JpaConfig {}
