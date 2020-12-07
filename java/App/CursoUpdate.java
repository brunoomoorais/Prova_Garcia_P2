package prova.App;

public class CursoUpdate {
	//id, nome, descricao, categoria_id, valor, desconto
	private String Nome;
	private String Descricao;
	private int CategoriaId;
	private double Valor;
	private int Desconto;
	
	public CursoUpdate() {
		
	}
	
	public CursoUpdate(String nome, String desc, int categoriaId, double valorAtual, int desconto)
	{
		this.Nome = nome;
		this.Descricao = desc;
		this.CategoriaId = categoriaId;
		this.Valor = valorAtual;
		this.Desconto = desconto;		
	}
	
	public String getNome() {return Nome;}
	
	public String getDescricao() {return Descricao;}
	
	public int getCategoria() {return CategoriaId;}
	
	public double getValor() {return this.Valor;}	
	
	public void setNome(String nome) {
		Nome = nome;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public void setCategoriaId(int categoriaId) {
		CategoriaId = categoriaId;
	}

	public void setValor(double valor) {
		Valor = valor;
	}

	public void setDesconto(int desconto) {
		Desconto = desconto;
	}

	public int getDesconto() {return Desconto;}
}
