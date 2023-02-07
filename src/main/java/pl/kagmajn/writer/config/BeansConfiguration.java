package pl.kagmajn.writer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@Configuration
@Slf4j
public class BeansConfiguration {
    @Bean
    public void beanTestFunction(){
        log.info("@@ This bean always runs while starting the application");
    }
}
