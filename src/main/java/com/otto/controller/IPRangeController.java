package com.otto.controller;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.otto.service.IPRangeService;

@RestController
public class IPRangeController {
	
	@Autowired
	private IPRangeService ipRangeService;
	

	@GetMapping("/ipranges")
	@Produces({MediaType.TEXT_PLAIN})
	public @ResponseBody
    String getipRanges(@RequestParam("region") String region)
	{
		return ipRangeService.getIPRanges(region);
		
	}

}
