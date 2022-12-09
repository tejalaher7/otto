package com.otto.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.core.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;



@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = Application.class)
@AutoConfigureMockMvc

public class IPRangeServiceTest {
	
	
	@TestConfiguration
    static class IPRangeServiceTestContextConfiguration {
 
        @Bean
        public IPRangeService ipRangeService() {
            return new IPRangeService();
        }
    }

    @Autowired
    private IPRangeService ipRangeService;

	@Test
	public void testgetIPRanges() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testgetAll_IpRanges()
	{
		
	}
	
	@Test
	public void testgetIpRanges_region()
	{
		
	}

	@Test 
	public void testretrieveJSONTag()
	{
		String file = "src/test/resources/test.json";
		String json;
        try {
			 json = Files.readAllBytes(Paths.get(file)).toString();
		} catch (IOException e) {
			fail("Exception " + e);
		}
        
       
		
	}
	
	
	
	@Test
	public void testgetAWS_IPRanges()
	{
		
	}
}
