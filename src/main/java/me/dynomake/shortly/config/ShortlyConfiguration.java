package me.dynomake.shortly.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ShortlyConfiguration {
    private String domain;
    private boolean fixDuplicate;
    private int charactersInCode;
}