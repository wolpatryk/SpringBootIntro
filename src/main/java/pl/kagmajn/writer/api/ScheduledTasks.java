package pl.kagmajn.writer.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.kagmajn.writer.logging.MetricLogger;

@Component
public class ScheduledTasks {
    private final RestService restService;
    private final ObjectMapper objectMapper;
    private final MongoWriter mongoWriter;
    private final MetricLogger metricLogger;

    public ScheduledTasks(final RestService restService,
                          final MongoWriter mongoWriter,
                          final MetricLogger metricLogger){
        // functions
        this.restService = restService;
        this.mongoWriter = mongoWriter;
        this.metricLogger = metricLogger;

        // init libs
        this.objectMapper = new ObjectMapper();
    }

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 10000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }

    @Scheduled(fixedRate = 1000)
    public void scheduleApiRequest(){
        try{
//            log.info(String.format("fstring %s w javie %s", "test1", "test2"));
            final Map<String, Object> t = objectMapper.readValue(restService.getPostsPlainJSON(), HashMap.class);
            String res = objectMapper.writeValueAsString(t);
            mongoWriter.insertJsonStringToMongoDb(res, "test");
            metricLogger.logMongoInsertsCount();
//            log.info(objectMapper.writeValueAsString(t));
        } catch (Exception e){
            log.error("Exception in scheduling for api request: ", e);
        }
    }

}