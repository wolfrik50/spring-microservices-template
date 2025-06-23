package io.wulfcodes.hotel.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("io.wulfcodes.hotel.repository")
@EntityScan("io.wulfcodes.common.model.entity")
public class JpaConfig {}
