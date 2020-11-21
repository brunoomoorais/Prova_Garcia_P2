package prova.produtos.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	@Autowired
	ProdutoDAO prodDao;
	
	public void insert(Produto prod) {
		prodDao.insert(prod);
	}
}
