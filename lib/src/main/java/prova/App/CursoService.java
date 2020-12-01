package prova.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
	@Autowired
	CursoDAO prodDao;
	
	public void insert(Curso prod) {
		prodDao.insert(prod);
	}
}
