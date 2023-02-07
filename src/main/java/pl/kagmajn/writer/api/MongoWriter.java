package pl.kagmajn.writer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class MongoWriter {
    private final ObjectMapper objectMapper;
    private MongoTemplate mongoTemplate;

    public MongoWriter(final MongoTemplate mongoTemplate){
        this.objectMapper = new ObjectMapper();
        this.mongoTemplate = mongoTemplate;
    }

    public void insertJsonStringToMongoDb(String row, String CollectionName){
        try {
            mongoTemplate.insert(row, CollectionName);
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
    }
}
