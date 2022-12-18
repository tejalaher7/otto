package com.otto.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;



@Service
public class IPRangeService {
	
	private String awsIPRangeURL = "https://ip-ranges.amazonaws.com/ip-ranges.json";
	private List<String> validRegions=  Arrays.asList("US", "AP", "CN", "SA", "AF", "CA", "EU", "ALL");
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper mapper;
	
	
//Method to get IP Ranges
public String getIPRanges(String region) {
		
		
		String result = null;
		List<String> validRegions_lowercase = validRegions.stream()
				  .map(String::toLowerCase)
				  .collect(Collectors.toList());
		
		if(validRegions.contains(region)|| validRegions_lowercase.contains(region))
		{
			JsonNode prefixes = getAWS_IPRanges(awsIPRangeURL);
             
			if(region.equals("ALL"))
			{
				result=getAll_IpRanges(prefixes);
			}
                         
			else
			{
				 result=getIpRanges_region(region,prefixes);
			}
                     
		 }          

		else
			return "Sorry the Region is invalid. Please enter a valid Region.";  
             
               
		return result;
	
	}			

//Method returns ALL the available IPRanges

public String getAll_IpRanges(JsonNode prefixes) {
	StringBuilder result = new StringBuilder();
	 if (prefixes.isArray()) {		
         ArrayNode arrayNode = (ArrayNode) prefixes;
         
         for (int i = 0; i < arrayNode.size(); i++) 
         {
             JsonNode individualElement = arrayNode.get(i);
             	result.append(individualElement);
             	result.append("<br>"); 
             	
         }

	 	}
	 return result.toString();
}

//Method returns IP Ranges as per the region passed in the URL query parameter
public String getIpRanges_region(String region, JsonNode prefixes) {
	ArrayNode arrayNode = (ArrayNode) prefixes;
	StringBuilder result = new StringBuilder();
    for (int i = 0; i < arrayNode.size(); i++) 
    {
    	
        JsonNode individualElement = arrayNode.get(i);
        if(individualElement.get("region").toString().startsWith("\"" + region.toLowerCase()))
        {
        	result.append(individualElement);
        	result.append("<br>"); 
        }
    }
	return result.toString();
}
	

//Method returns data of a JSON tag after parsing the input JSON String

/*public JsonNode retrieveJSONTag(String inline) {
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
           
        // Read the JSON into the Jackson tree model and get the "prefixes" node
        JsonNode tree = null;
		try {
			tree = mapper.readTree(inline);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JsonNode prefixes = tree.get("prefixes");
		return prefixes;
	}*/


//Method to get the response after hitting the AWS IP Range Url

public JsonNode getAWS_IPRanges(String awsIPRangeURL) {
		String  response = restTemplate.getForObject(awsIPRangeURL, String.class);
	    JsonNode prefixes = null;
	     
		try {
			
			JsonNode rootNode = mapper.readValue(response, JsonNode.class);
			prefixes= rootNode.get("prefixes");
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	     
		return prefixes;
	}


}


