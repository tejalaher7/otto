package test.otto.resources;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Prefixes {
	 
	public static JsonNode prefixes;
	 
	static
	 {
	        try {
	        	prefixes = new ObjectMapper().readTree(Files.readAllBytes(Paths.get("src/test/resources/prefixes")));
			} catch (IOException e) {
				fail("Exception " + e);
			}
		 
	 }

	 
}