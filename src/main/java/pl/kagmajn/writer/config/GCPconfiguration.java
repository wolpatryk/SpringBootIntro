package pl.kagmajn.writer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gcp")
@Getter
@Setter
public class GCPconfiguration {
    private String project;
    private String keyPath;
    private String pubSubsubscriptionName;
}
