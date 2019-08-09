/**
 * 
 */
package com.example.gederana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.gederana.dao.TestService;
import com.example.gederana.model.TestModelFX;

 

/**
 * @author gederanadewadatta
 *
 */
@RestController

@RequestMapping(path = "/fx")
public class TestController {
	@Autowired
	private TestService testService;

	
	@RequestMapping(path = "/{second}/{third}/{fourth}/{fifth}",produces="application/json", method = RequestMethod.GET)
	public TestModelFX testFX(@PathVariable("second") String second,@PathVariable("third") String third,@PathVariable("fourth") String fourth,
			@PathVariable("fifth")String fifth) {
		return testService.getFX(second,third,fourth,fifth);
		
	}
	 
}
