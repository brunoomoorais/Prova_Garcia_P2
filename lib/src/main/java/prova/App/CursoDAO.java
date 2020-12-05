package prova.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		
		public void insert(CursoUpdate curso) {
			String sql = "INSERT INTO curso(nome, descricao, categoria_id, valor, desconto) VALUES(?, ?, ?, ?, ?)";
			getJdbcTemplate().update(sql, new Object[] {
					curso.getNome(), curso.getDescricao(), curso.getCategoria(), curso.getValor(), curso.getDesconto()
			});
		}
		
		public Map<String, Object> getCurso(int id){
			String sql = "select id, nome, descricao, categoria_id, (valor - (valor * desconto / 100) + (valor * desconto / 100)) as valorAtual, (valor - (valor * desconto / 100)) as valorDesc, desconto"
						+" from curso"
						+" WHERE id = ?;";
			return getJdbcTemplate().queryForMap(sql, new Object[] {id});
		}
		
		public List<Map<String, Object>> getCursos(){
			String sql = "select id, nome, descricao, categoria_id, (valor - (valor * desconto / 100) + (valor * desconto / 100)) as valorAtual, (valor - (valor * desconto / 100)) as valorDesc, desconto"
						+" from curso"
						+" order by nome;";
			return (List<Map<String, Object>>) getJdbcTemplate().queryForList(sql); 
		}
		
		public List<Map<String, Object>> getCursosFilter(int categoriaId, double valor, int desconto){			
			String sql = "select id, nome, descricao, categoria_id, (valor - (valor * desconto / 100) + (valor * desconto / 100)) as valorAtual, (valor - (valor * desconto / 100)) as valorDesc, desconto"
						+" from curso";
			int count = 0;
			int countObject = 0;
			
			if(categoriaId > -1) countObject++;
			if(valor > 0) countObject++;
			if(desconto > -1) countObject++;
			if(countObject != 0)
				sql += " WHERE";
			Object[] parameters = new Object[countObject];
			
			if(categoriaId > -1)
			{
				sql += " categoria_id = ?";
				parameters[count] = categoriaId;
				count++;
			}

			if(valor > 0.00)
			{
				if(count > 0) sql += " AND";
				sql += " (valor - (valor * desconto / 100)) < cast(? as real)";
				parameters[count] = valor;
				count++;
			}
			
			if(desconto > -1)
			{
				if(count > 0) sql += " AND";
				sql += " 	desconto > ?";
				parameters[count] = desconto;				
			}
			
			Object[] newParameters = (Object[]) parameters;
			
			if(count != 0)
				sql += " order by nome;";
			
			return (List<Map<String, Object>>) getJdbcTemplate().queryForList(sql, parameters); 
		}
		
		public void deleteCurso(int id) {
			String sql = "DELETE FROM curso where id = ?";
			getJdbcTemplate().update(sql, new Object[] {id});
		}
		
		public void updateCurso(int id, CursoUpdate curso) {
			String sql = "UPDATE curso"
						+" SET nome = ?,"
						+" descricao = ?,"
						+ "categoria_id = ?,"
						+" valor = CAST(? as REAL),"
						+" desconto = ?"
						+" WHERE id = ?;";
			getJdbcTemplate().update(sql, new Object[] {
					curso.getNome(), curso.getDescricao(), curso.getCategoria(), curso.getValor(), curso.getDesconto(), id 
			});
		}
}
