package prova.App;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String principal(Model model)
	{
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
}
