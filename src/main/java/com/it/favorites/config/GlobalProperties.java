package com.it.favorites.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "my-global")
public class GlobalProperties {
    private String filePath;
}
