package prova.produtos.model;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDAO extends JdbcDaoSupport{
		@Autowired
		DataSource dataSource;
		
		@PostConstruct
		private void initialize() {
			setDataSource(dataSource);
		}
		
		public void insert(Produto prod) {
			String sql = "INSERT INTO produto (id, nome, categoria, marca, valor)" +
						 "VALUES (?, ?, ?, ?, ?)";
			getJdbcTemplate().update(sql, new Object[] {
					prod.getId(), prod.getNome(), prod.getCategoria(), prod.getMarca(), prod.getValor()
			});
		}
}
