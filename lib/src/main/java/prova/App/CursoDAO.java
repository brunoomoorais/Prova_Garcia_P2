package prova.App;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDAO extends JdbcDaoSupport{
		@Autowired
		DataSource dataSource;
		
		@PostConstruct
		private void initialize() {
			setDataSource(dataSource);
		}
		
		public void insert(Curso prod) {
			String sql = "do $$"
						 + " declare"
						 + "        cursoId integer := (select MAX(id) + 1 from curso);"
					     + "begin"
						 + "    insert into curso(id, nome, descricao, categoria_id, valor, desconto)"
						 + "        values (cursoId, ?, ?, ?, ?, ?);"
						 + "    Raise Notice '%', cursoId;"
						 + "end $$";
			getJdbcTemplate().update(sql, new Object[] {
					prod.getNome(), prod.getDescricao(), prod.getCategoria().getId(), prod.getValor(), prod.getDesconto()
			});
		}
}
