package prova.App;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	@Autowired
	UsuarioDAO uDao;
		
	public String getId(String login, String senha){
		return uDao.getLogin(login, senha);
	}	
}
