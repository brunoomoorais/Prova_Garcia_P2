package prova.App;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	CategoriaDAO catDao;
	
	public void insert(Categoria cat) {
		catDao.insert(cat);
	}
	
	public Map<String, Object> getCategoria(int id){
		return catDao.getCategoria(id);
	}
	
	public List<Map<String, Object>> getCategorias(){
		return catDao.getCategorias();
	}
	
	public void delete(int id) {
		catDao.deleteCategoria(id);
	}
	
	public void update(int id, Categoria cat) {
		catDao.updateCategoria(id, cat);
	}
}
