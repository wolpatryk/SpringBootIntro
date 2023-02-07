package pl.kagmajn.writer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriterApplicationTests {

	public static final Logger logger = LoggerFactory.getLogger(WriterApplicationTests.class);
	@Test
	public void shouldWorkTest(){
		logger.info("ForexEventWriter test");
		String tmp = "setting up a test";
		runTest(tmp);
		logger.info("ForexEventWriter test success");
	}

	public static void runTest(String testValue){
		logger.info("test function {}", testValue);
	}

}
