package prova.App;

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
		
		public void insert(Curso curso) {
			String sql = "do $$"
						 + " declare"
						 + "        cursoId integer := (select MAX(id) + 1 from curso);"
					     + "begin"
						 + "    insert into curso(id, nome, descricao, categoria_id, valor, desconto)"
						 + "        values (cursoId, ?, ?, ?, ?, ?);"
						 + "    Raise Notice '%', cursoId;"
						 + "end $$";
			getJdbcTemplate().update(sql, new Object[] {
					curso.getNome(), curso.getDescricao(), curso.getCategoria().getId(), curso.getValor(), curso.getDesconto()
			});
		}
		
		public Map<String, Object> getCurso(int id){
			String sql = "select id, nome, descricao, categoria_id, valor, (valor - (valor * desconto / 100)) as valorDesc, desconto"
						+"from curso"
						+"WHERE id = ?;";
			return getJdbcTemplate().queryForMap(sql, new Object[] {id});
		}
		
		public List<Map<String, Object>> getCursos(){
			String sql = "select id, nome, descricao, categoria_id, valor, (valor - (valor * desconto / 100)) as valorDesc, desconto"
						+"from curso"
						+"order by nome;";
			return (List<Map<String, Object>>) getJdbcTemplate().queryForList(sql); 
		}
		
		public void deleteCurso(int id) {
			String sql = "DELETE FROM curso where id = ?";
			getJdbcTemplate().update(sql, new Object[] {id});
		}
		
		public void updateCurso(int id, Curso curso) {
			String sql = "UPDATE curso"
						+"SET nome = ?,"
						+"    descricao = ?,"
						+ "	  categoria_id = ?,"
						+"    valor = CAST(? as MONEY),"
						+"    desconto = ?"
						+"WHERE id = ?;";
			getJdbcTemplate().update(sql, new Object[] {
					curso.getNome(), curso.getDescricao(), curso.getCategoria().getId(), curso.getValor(), curso.getDesconto(), id 
			});
		}
}
