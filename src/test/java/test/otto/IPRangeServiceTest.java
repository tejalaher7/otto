package test.otto;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otto.service.IPRangeService;

import test.otto.resources.Prefixes;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = IPRangeServiceTest.class)
@AutoConfigureMockMvc
public class IPRangeServiceTest {
	
	@InjectMocks
	IPRangeService ipRangeService;
	
	@Mock
    RestTemplate restTemplate;
	
	@Mock
	ObjectMapper mapper; 
	
	@Mock
	JsonNode rootNode;
	
	@Before
	public void setup()
	{
		
		//MockitoAnnotations.initMocks(IPRangeServiceTest.class);
		try {
			Mockito.when(mapper.readValue(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(rootNode);
			Mockito.when(rootNode.get("prefixes")).thenReturn(Prefixes.prefixes);
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test_getAll_IpRanges()
	{
		
		try {
			String result = new String(Files.readAllBytes(Paths.get("src/test/resources/ipRanges_all")));
			 assertEquals(result.trim(),ipRangeService.getAll_IpRanges(Prefixes.prefixes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}
	
	@Test
	public void test_getIpRanges_region()
	{
		
		try {
			 String result = new String(Files.readAllBytes(Paths.get("src/test/resources/ipRanges_region")));
			 assertEquals(result.trim(),ipRangeService.getIpRanges_region("US",Prefixes.prefixes));
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/*@Test 
	public void test_retrieveJSONTag()
	{
		String inline= "";
        try {
			 inline = new String(Files.readAllBytes(Paths.get("src/test/resources/jsonResponse")));
		} catch (IOException e) {
			fail("Exception " + e);
		}
		assertEquals(Prefixes.prefixes.toString(),ipRangeService.retrieveJSONTag(inline).toString());
        
  	}
*/
	
	@Test
	public void test_getAWS_IPRanges()
	{
		assertEquals(Prefixes.prefixes,ipRangeService.getAWS_IPRanges("https://ip-ranges.amazonaws.com/ip-ranges.json"));
	}
	@Test
	public void test_getIPRanges()
	{
		
		
	        try {
				
				 String result_region = new String(Files.readAllBytes(Paths.get("src/test/resources/ipRanges_region")));
				 assertEquals(result_region.trim(),ipRangeService.getIPRanges("US"));
				 
			} catch (IOException e) {
				fail("Exception " + e);
			}
		
		 assertEquals("Sorry the Region is invalid. Please enter a valid Region.",ipRangeService.getIPRanges("rr"));
		 
	}
}
