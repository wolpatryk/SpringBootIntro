package pl.kagmajn.writer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@Slf4j
public class JsonFormatter {

    private final String jsonSchema;

    private final ObjectMapper objectMapper;

    public JsonFormatter(){
        this.jsonSchema = "defaultSchema";
        this.objectMapper = new ObjectMapper();
    }

    public String jsonObjectMapper(Map<String, Object> event){

        try {
            // return Map object as pure JSON string
            return objectMapper.writeValueAsString(event);

        } catch (Exception e) {
            log.error("Error: {}", e);
            return "{\"error\": \"" + e + "\"}";
        }
    }
}
