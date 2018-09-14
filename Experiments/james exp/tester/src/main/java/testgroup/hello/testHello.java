package testgroup.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testHello {
	
	@RequestMapping("/hello")
	public String sayhello() {
		return "hello";
	}
}
