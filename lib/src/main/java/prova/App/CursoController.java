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
public class CursoController 
{
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/curso")
	public String cadastrar(Model model)
	{		
		model.addAttribute("curso", new Curso());
		return "form";
	}
	
	@PostMapping("/curso")
	public String postCurso(@ModelAttribute Curso curso, Model model)
	{
		model.addAttribute("curso", curso);
		CursoService cdao = context.getBean(CursoService.class);
		cdao.insert(curso);
		return "cursoSucesso";
	}
	
	@GetMapping("/curso/{id}")
	public String getCurso(@PathVariable("id") int id, Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		CategoriaService catDao = context.getBean(CategoriaService.class);
		Map<String, Object> curso = cursoDao.getId(id);
		Map<String, Object> categoria = catDao.getId((int)curso.get("categoria_id"));
		Curso ResultCurso = new Curso(
									(String)curso.get("nome"),
									(String)curso.get("descricao"),
									new Categoria(
													(int)categoria.get("id"),
													(String)categoria.get("nome")
												 ),
									(double)curso.get("valor"),
									(double)curso.get("valorDesconto"),
									(int)curso.get("desconto")
								);
		model.addAttribute("variavel_pagina", ResultCurso);
		return "produtosSucesso";
	}
	
	@GetMapping("/cursos")
	public String getCursos(Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		List<Map<String, Object>> cursos = cursoDao.getAll();
		model.addAttribute("variavel_pagina", cursos);
		return "produtosSucesso";
	}
	
	@GetMapping("/curso/{id}/update")
	public String UpdateForm(@PathVariable("id") int id, Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		CategoriaService catDao = context.getBean(CategoriaService.class);
		Map<String, Object> curso = cursoDao.getId(id);
		Map<String, Object> categoria = catDao.getId((int)curso.get("categoria_id"));
		Curso ResultCurso = new Curso(
									(String)curso.get("nome"),
									(String)curso.get("descricao"),
									new Categoria(
													(int)categoria.get("id"),
													(String)categoria.get("nome")
												  ),
									(double)curso.get("valor"),
									(double)curso.get("valorDesconto"),
									(int)curso.get("desconto")
								);
		model.addAttribute("variavel_pagina", ResultCurso);	
		return "formAtt";
	}
		
	
	@PostMapping("/curso/{id}/update")
	public String updateCurso(@PathVariable("id")int id, @ModelAttribute Curso curso, Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		cursoDao.update(id, curso);
		return "redirect:/rotaDasCategorias";
	}
	
	@PostMapping("/curso/{id}/delete")
	public String deleteCurso(@PathVariable("id") int id, Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		cursoDao.delete(id);
		return "formDelete";
	}
}
