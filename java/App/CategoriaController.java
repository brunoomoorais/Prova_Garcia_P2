package prova.App;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class CategoriaController 
{
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/categorias/insert")
	public String cadastrar(Model model)
	{		
		model.addAttribute("categoriaObject", new Categoria());
		return "adicionar_categoria";
	}
	
	@PostMapping("/categorias/insert")
	public String postCategoria(@ModelAttribute Categoria categoriaObject, Model model)
	{
		model.addAttribute("cat", categoriaObject);
		CategoriaService cdao = context.getBean(CategoriaService.class);
		cdao.insert(categoriaObject);
		return "redirect:/categorias";
	}
	
	@GetMapping("/categorias")
	public String getCategorias(Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		List<Map<String, Object>> categorias = catDao.getAll();
		model.addAttribute("categoriasObject", categorias);
		return "visualizar_categoria";
	}
	
	@GetMapping("/categorias/{id}/update")
	public String UpdateForm(@PathVariable("id") int id, Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		Map<String, Object> categoria = catDao.getId(id);
		Categoria cat = new Categoria((int)categoria.get("id"), (String)categoria.get("nome"));
		model.addAttribute("categoriaObject", cat);
		model.addAttribute("Id", id);
		return "editar_categoria";
	}
		
	
	@PostMapping("/categorias/{id}/update")
	public String postCategoria(@PathVariable("id")int id, @ModelAttribute Categoria categoriaObject, Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		catDao.update(id, categoriaObject);
		return "redirect:/categorias";
	}
	
	@PostMapping("/categorias/{id}/delete")
	public String deleteCategoria(@PathVariable("id") int id, Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		catDao.delete(id);
		return "redirect:/categorias";
	}
}
