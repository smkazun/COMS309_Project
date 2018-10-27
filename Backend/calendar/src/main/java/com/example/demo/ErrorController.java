package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
	
	@GetMapping("/error")
	public @ResponseBody String errorPage()
	{
		return "An error has occured";
	}

}
