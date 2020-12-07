package prova.App;

import java.util.List;
import java.util.Map;

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
			String sql = "INSERT INTO categoria(nome) VALUES(?)";
			getJdbcTemplate().update(sql, new Object[] {
					cat.getNome()
			});
		}

		public Map<String, Object> getCategoria(int id){
			String sql = "select id, nome from categoria where id = ?;";
			return getJdbcTemplate().queryForMap(sql, new Object[] {id});
		}
		
		public List<Map<String, Object>> getCategorias(){
			String sql = "select id, nome from categoria;";
			List<Map<String, Object>> categorias = (List<Map<String, Object>>) getJdbcTemplate().queryForList(sql); 
			return categorias;
		}
		
		public void deleteCategoria(int id) {
			String sql = "DELETE FROM categoria where id = ?";
			getJdbcTemplate().update(sql, new Object[] {id});
		}
		
		public void updateCategoria(int id, Categoria categoria) {
			String sql = "UPDATE categoria"
						+" SET nome = ?"
						+" WHERE id = ?;";
			getJdbcTemplate().update(sql, new Object[] {
					categoria.getNome(), id 
			});
		}
		
}