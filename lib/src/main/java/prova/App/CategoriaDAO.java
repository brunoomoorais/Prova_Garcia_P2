package prova.App;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDAO extends JdbcDaoSupport{
		@Autowired
		DataSource dataSource;
		
		@PostConstruct
		private void initialize() {
			setDataSource(dataSource);
		}
		
		public void insert(Categoria cat) {
			String sql = "do $$"
						+"  declare"
						+"    categoriaId integer := (select MAX(id) + 1 from categoria);"
						+"begin"
						+"    insert into categoria(id, nome)"
						+"        values (categoriaId, ?);"
						+"    Raise Notice '%', categoriaId;"
						+"end $$;";
			getJdbcTemplate().update(sql, new Object[] {
					cat.getNome()
			});
		}
}