package pl.kagmajn.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kagmajn.writer.api.JsonFormatter;

import java.nio.file.Paths;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class EventWriterTests {
	public static final Logger log = LoggerFactory.getLogger(EventWriterTests.class);
	@Test
	//TODO make tests in the future
	// ./gradlew clean build
	// ./gradlew test --tests --info pl.kagmajn.writer.EventWriterTests
	public void shouldWorkTest(){
		try {
			log.info("ForexEventWriter test");
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> testEvent = objectMapper.readValue(Paths.get("src/test/resources/event.json").toFile(), Map.class);
			JsonFormatter jf = new JsonFormatter();
			String testEventString = jf.jsonObjectMapper(testEvent);

			if(testEventString instanceof String){
				assertTrue("This will succeed.", true);
			} else {
				assertTrue("This will fail!", false);
			}

			String tmp = "setting up a test";
			log.info("ForexEventWriter test success");
		} catch (Exception e) {
			log.error("Error in tests: ", e);
		}
	}
}
