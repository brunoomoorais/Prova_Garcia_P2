package prova.App;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
	@Autowired
	CursoDAO cDao;
	
	public void insert(Curso curso) {
		cDao.insert(curso);
	}
	
	public Map<String, Object> getId(int id){
		return cDao.getCurso(id);
	}
	
	public List<Map<String, Object>> getAll(){
		return cDao.getCursos();
	}
	
	public List<Map<String, Object>> getAllFiltered(int categoriaId, double valor, int desconto){
		return cDao.getCursosFilter(categoriaId, valor, desconto);
	}
	
	public void delete(int id) {
		cDao.deleteCurso(id);
	}
	
	public void update(int id, Curso curso) {
		cDao.updateCurso(id, curso);
	}
}
