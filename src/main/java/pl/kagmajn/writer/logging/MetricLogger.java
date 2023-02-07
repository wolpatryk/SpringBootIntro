package pl.kagmajn.writer.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class MetricLogger {

    private AtomicInteger mongoInsertsCount = new AtomicInteger(0);
    private AtomicInteger mongoDbExceptionCount = new AtomicInteger(0);

    public void logMongoDbError() {
        mongoDbExceptionCount.incrementAndGet();
    }

    public void logMongoInsertsCount(){
        mongoInsertsCount.incrementAndGet();
    }

    @Scheduled(fixedDelay = 5000)
    public void logErrors() {
        final int mongoInsertsLogCount = mongoDbExceptionCount.get();
        final int errorResponseCount = mongoInsertsCount.get();

        if (mongoInsertsLogCount > 0) {
            log.error("There were {} MongoDB inserts", mongoInsertsLogCount);
            mongoDbExceptionCount.set(0);
        }

        if(errorResponseCount > 0){
            log.error("There were {} MongoDB errors", errorResponseCount);
            mongoInsertsCount.set(0);
        }
    }
}
