package pl.kagmajn.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kagmajn.writer.config.GCPconfiguration;
import pl.kagmajn.writer.controller.HealthController;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EventWriter {

    private final JsonFormatter jsonFormatter;

    private final GCPconfiguration configuration;

    @Autowired
    public EventWriter(final GCPconfiguration configuration, final JsonFormatter jsonFormatter){

        this.configuration = configuration;
        this.jsonFormatter = jsonFormatter;

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info("{}", configuration.getProject());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@");

        HealthController.setHealthy(true);

        try{
            log.info("hello git!");
            log.info("App running");
            log.info("localhost:8899");
            forexEventWriter();

            Map<String, Object> mapResult = new HashMap<>();

            mapResult.put("name", "Patryk");
            mapResult.put("job", "Data Engineer");

            String res = jsonFormatter.jsonObjectMapper(null);

            log.info(res);
            log.info("App success");
        } catch (Exception e) {
            log.error("Error while exporting data: {}", e);
        }
    }
    public void forexEventWriter() throws Exception {
        try {
            log.info("Writing events to bigquery");
        } catch (Exception e) {
            log.info("Error while writing events to bigquery", e);
        }
    }
}


