package prova.produtos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController 
{
	@GetMapping("/ola")
	public String ola(@RequestParam(name = "nome", required = false, defaultValue = "Mundo") String nome, Model model)
	{
		model.addAttribute("nome", nome);
		return "index";
	}
	
}
