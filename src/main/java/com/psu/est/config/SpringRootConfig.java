package com.psu.est.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by danielkarkee on 2/1/16.
 */
@Configuration
@ComponentScan (value = {
        "com.psu.est.dao.impl",
        "com.psu.est.service"
})
@Import(value = {
        DataSourceConfig.class,
        SpringSecurityConfig.class
})
public class SpringRootConfig {
}
