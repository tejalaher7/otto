package test.otto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.otto.service.IPRangeService;

import test.otto.resources.Prefixes;



@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = IPRangeServiceTest.class)
@AutoConfigureMockMvc
public class IPRangeServiceTest {
	
	@InjectMocks
	IPRangeService ipRangeService;

	
	@Test
	public void test_getAll_IpRanges()
	{
		
		String result ="";
		try {
			 result = new String(Files.readAllBytes(Paths.get("src/test/resources/ipRanges_all")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		assertEquals(result.trim(),ipRangeService.getAll_IpRanges(Prefixes.prefixes));
		
	}
	
	@Test
	public void test_getIpRanges_region()
	{
		String result ="";
		try {
			 result = new String(Files.readAllBytes(Paths.get("src/test/resources/ipRanges_region")));
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		assertEquals(result.trim(),ipRangeService.getIpRanges_region("US",Prefixes.prefixes));
		
	}

	@Test 
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

	@Test
	public void test_getIPRanges()
	{
		
		 String result_region = "";
		 
	        try {
				 
				 result_region = new String(Files.readAllBytes(Paths.get("src/test/resources/ipRanges_region")));
			} catch (IOException e) {
				fail("Exception " + e);
			}
		 assertEquals(result_region.trim(),ipRangeService.getIPRanges("US"));
		 assertEquals("Sorry the Region is invalid. Please enter a valid Region.",ipRangeService.getIPRanges("rr"));
		 
	}
}
