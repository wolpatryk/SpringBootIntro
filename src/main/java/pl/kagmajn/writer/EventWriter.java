package pl.kagmajn.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import pl.kagmajn.writer.api.JsonFormatter;
import pl.kagmajn.writer.api.MongoWriter;
import pl.kagmajn.writer.api.RestService;
import pl.kagmajn.writer.config.BeansConfiguration;
import pl.kagmajn.writer.config.GCPconfiguration;
import pl.kagmajn.writer.config.MongoConfiguration;
import pl.kagmajn.writer.config.ServerConfiguration;
import pl.kagmajn.writer.controller.HealthController;
import pl.kagmajn.writer.controller.StatusController;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EventWriter {

    private final GCPconfiguration configuration;
    private final MongoConfiguration mongoConfiguration;
    private final ServerConfiguration serverConfiguration;
    @Autowired
    public EventWriter(final GCPconfiguration configuration,
                       final MongoConfiguration mConfiguration,
                       final ServerConfiguration serverConfiguration){
        // begin of program
        // define config files
        this.configuration = configuration;
        this.mongoConfiguration = mConfiguration;
        this.serverConfiguration = serverConfiguration;

        log.info("@@@@@@ MAIN @@@@@@");
        log.info("{}", configuration.getProject());

        HealthController.setHealthy(true);
        StatusController.setHealthy(false);

        try{
            log.info("App running");
            log.info("localhost:{}", serverConfiguration.getPort());
            log.info("Connected to Mongo: {}", mConfiguration.getDatabase());
            log.info("App success");
        } catch (Exception e) {
            log.error("@@@@@@@@@@@@ ERROR @@@@@@@@@@@@");
            log.error("Error while exporting data: ", e);
            log.error("@@@@@@@@@@@@ ERROR @@@@@@@@@@@@");
        }

        // end of program
        log.info("@@@@@@ MAIN @@@@@@");
    }
}


