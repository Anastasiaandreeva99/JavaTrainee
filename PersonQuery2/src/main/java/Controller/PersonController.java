package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import Services.PersonService;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping("/")
	public String persons()  {
		return personService.getALL().toString();
	}

}
