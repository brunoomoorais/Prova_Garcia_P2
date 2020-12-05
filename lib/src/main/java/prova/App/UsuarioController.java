package prova.App;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class UsuarioController 
{
	@Autowired
	private ApplicationContext context;
	
	
	@PostMapping("/usuarios/autentication")
	public String AutenticUser(@ModelAttribute Usuario usuario, Model model)
	{
		UsuarioService udao = context.getBean(UsuarioService.class);
		String tipoUsuario = udao.getId(usuario.getLogin(), usuario.getSenha());
		
		switch(tipoUsuario)
		{
			case "1":
				return "redirect:/cursos/admin";
			case "2":
				return "redirect:/cursos/cliente";
			default:
				return "redirect:/";
		}
	}	
}

