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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoriaController 
{
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/categoria")
	public String cadastrar(Model model)
	{		
		model.addAttribute("cat", new Categoria());
		return "form";
	}
	
	@PostMapping("/categoria")
	public String postCategoria(@ModelAttribute Categoria categoria, Model model)
	{
		model.addAttribute("cat", categoria);
		CategoriaService cdao = context.getBean(CategoriaService.class);
		cdao.insert(categoria);
		return "categoriaSucesso";
	}
	
	@GetMapping("/categoria/{id}")
	public String getCategoria(@PathVariable("id") int id, Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		Map<String, Object> categoria = catDao.getCategoria(id);
		Categoria cat = new Categoria((int)categoria.get("id"), (String)categoria.get("nome"));
		model.addAttribute("variavel_pagina", cat);
		return "produtosSucesso";
	}
	
	@GetMapping("/categorias")
	public String getCategorias(Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		List<Map<String, Object>> categorias = catDao.getCategorias();
		model.addAttribute("variavel_pagina", categorias);
		return "produtosSucesso";
	}
	
	@GetMapping("/categoria/{id}/update")
	public String UpdateForm(@PathVariable("id") int id, Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		Map<String, Object> categoria = catDao.getCategoria(id);
		Categoria cat = new Categoria((int)categoria.get("id"), (String)categoria.get("nome"));
		model.addAttribute("variavel_pagina", cat);	
		return "formAtt";
	}
		
	
	@PostMapping("/categoria/{id}/update")
	public String postCategoria(@PathVariable("id")int id, @ModelAttribute Categoria categoria, Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		catDao.update(id, categoria);
		return "redirect:/rotaDasCategorias";
	}
	
	@GetMapping("/categoria/{id}/delete")
	public String deleteCategoria(@PathVariable("id") int id, Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		catDao.delete(id);
		return "formDelete";
	}
}
