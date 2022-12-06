package com.otto.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.TypeFactory;


@Service
public class IPRangeService {
	
	private String awsIPRangeURL = "https://ip-ranges.amazonaws.com/ip-ranges.json";
	private List<String> validRegions=  Arrays.asList("EU", "US", "AP", "CN", "SA", "AF", "CA");
	
	

	public String getIPRanges(String region) {
		
		String inline = "";
		StringBuilder result = new StringBuilder();
		
		
		if(validRegions.contains(region))
		{
			try{
			URL url = new URL("https://ip-ranges.amazonaws.com/ip-ranges.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } 
            
            else {

                Scanner scanner = new Scanner(url.openStream());
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                
             // Read the JSON into the Jackson tree model and get the "paths" node
             JsonNode tree = mapper.readTree(inline);
             JsonNode prefixes = tree.get("prefixes");            
             if (prefixes.isArray()) {
                 ArrayNode arrayNode = (ArrayNode) prefixes;
                 
                 for (int i = 0; i < arrayNode.size(); i++) 
                 {
                     JsonNode individualElement = arrayNode.get(i);                    
                     if (individualElement.get("region").toString().startsWith("\"" + region.toLowerCase()))
                     {
                    	result.append(individualElement);
                    	result.append("<br>"); 
                    	 
                     }
             
             
                 }
                
            }
            }
    	}
            catch (Exception e) 
    		{
            e.printStackTrace();
    		}
		return result.toString();
		}
		
		else
			return null;
		
		
	}


	}


