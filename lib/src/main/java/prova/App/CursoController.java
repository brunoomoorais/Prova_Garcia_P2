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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class CursoController 
{
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/cursos/insert")
	public String cadastrar(Model model)
	{		
		CategoriaService catDao = context.getBean(CategoriaService.class);
		model.addAttribute("categoriasObject", catDao.getAll());
		model.addAttribute("cursoObject", new CursoUpdate());
		return "adicionar_curso";
	}
	
	@PostMapping("/cursos/insert")
	public String postCurso(@ModelAttribute CursoUpdate curso, Model model)
	{
		CursoService cdao = context.getBean(CursoService.class);
		cdao.insert(curso);
		return "redirect:/cursos/admin";
	}
	
	@GetMapping("/cursos/desc/{id}")
	public String getCurso(@PathVariable("id") int id, Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		CategoriaService catDao = context.getBean(CategoriaService.class);
		Map<String, Object> curso = cursoDao.getId(id);
		/*if(curso == null)
		{
			model.addAttribute("result", "/cursos/desc/{id}");
			return "error";
		}*/
		Map<String, Object> categoria = catDao.getId((int)curso.get("categoria_id"));
		Curso ResultCurso = new Curso(
									(int)curso.get("id"),
									(String)curso.get("nome"),
									(String)curso.get("descricao"),
									new Categoria(
													(int)categoria.get("id"),
													(String)categoria.get("nome")
												 ),
									(double)curso.get("valorAtual"),
									(double)curso.get("valorDesc"),
									(int)curso.get("desconto")
								);
		model.addAttribute("curso", ResultCurso);
		return "curso";
	}
	
	@GetMapping("/cursos/admin")
	public String getCursosAdmin(Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		List<Map<String, Object>> cursos = cursoDao.getAll();
		model.addAttribute("cursos", cursos);
		return "index_admin";
	}
	
	@GetMapping("/cursos/cliente")
	public String getCursosCliente(Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		CategoriaService catDao = context.getBean(CategoriaService.class);
		List<Map<String, Object>> cursos = cursoDao.getAll();
		model.addAttribute("cursos", cursos);
		model.addAttribute("categorias", catDao.getAll());
		return "index_cliente";
	}
	
	@GetMapping("/cursos/cliente/filtered")
	public String getCursosFilter(@RequestParam(name = "categoria", required = true, defaultValue = "-1") int categoria,
								  @RequestParam(name = "valor", required = true, defaultValue = "-1") double valor,
								  @RequestParam(name = "desconto", required = true, defaultValue = "-1") int desconto,
								  Model model)
	{
		CategoriaService catDao = context.getBean(CategoriaService.class);
		CursoService cursoDao = context.getBean(CursoService.class);
		List<Map<String, Object>> cursos = cursoDao.getAllFiltered(categoria, valor, desconto);
		model.addAttribute("cursos", cursos);
		model.addAttribute("categorias", catDao.getAll());
		return "index_cliente";
	}
	
	@GetMapping("/cursos/{id}/update")
	public String UpdateForm(@PathVariable("id") int id, Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		CategoriaService catDao = context.getBean(CategoriaService.class);
		Map<String, Object> curso = cursoDao.getId(id);
		/*if(curso == null)
		{
			model.addAttribute("result", "/cursos/{id}/update");
			return "error";
		}*/
		Map<String, Object> categoria = catDao.getId((int)curso.get("categoria_id"));
		CursoUpdate ResultCurso = new CursoUpdate(
										(String)curso.get("nome"),
										(String)curso.get("descricao"),
										(int)categoria.get("id"),
										(double)curso.get("valorAtual"),
										(int)curso.get("desconto")
									);
		model.addAttribute("cursoObject", ResultCurso);	
		model.addAttribute("id", id);
		model.addAttribute("categoriasObject", catDao.getAll());
		return "editar_curso";
	}
		
	
	@PostMapping("/cursos/{id}/update")
	public String updateCurso(@PathVariable("id")int id, @ModelAttribute CursoUpdate curso, Model model)
	{
		model.addAttribute("cursoObject", curso);
		CursoService cursoDao = context.getBean(CursoService.class);
		cursoDao.update(id, curso);
		return "redirect:/cursos/admin";
	}
	
	@PostMapping("/cursos/{id}/delete")
	public String deleteCurso(@PathVariable("id") int id, Model model)
	{
		CursoService cursoDao = context.getBean(CursoService.class);
		cursoDao.delete(id);
		return "redirect:/cursos/admin";
	}
}
