package testgroup.objects;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testobjects {

	@RequestMapping("/objects")
	public List<objects> getAllobjects(){
		return Arrays.asList(
			new objects("a", "a name", "a description"),
			new objects("b", "b name", "b description")
			);
	}
}
