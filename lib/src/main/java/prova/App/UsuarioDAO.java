package prova.App;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO extends JdbcDaoSupport{
		@Autowired
		DataSource dataSource;
		
		@PostConstruct
		private void initialize() {
			setDataSource(dataSource);
		}
		
		public Map<String, Object> getLogin(String login, String senha)
		{
			String sql = "SELECT tipoUsuarioId FROM usuario WHERE login = ? and senha = ?";
			return getJdbcTemplate().queryForMap(sql, new Object[] {login, senha});
		}
}