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
		
		public String getLogin(String login, String senha)
		{
			String sql = "select verifyFunc(?, ?) as tipoUsuarioId";
			
			return (String)getJdbcTemplate().queryForMap(sql, new Object[] {login, senha}).get("tipoUsuarioId");
		}
}