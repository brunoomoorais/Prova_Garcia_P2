package prova.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	CategoriaDAO catDao;
	
	public void insert(Categoria cat) {
		catDao.insert(cat);
	}
}
