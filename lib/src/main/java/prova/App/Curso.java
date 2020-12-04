package prova.App;

public class Curso {
	//id, nome, descricao, categoria_id, valor, desconto
	private int Id;
	private String Nome;
	private String Descricao;
	private Categoria Categoria;
	private double Valor;
	private double ValorDesconto;
	private int Desconto;
	
	public Curso() {
		
	}
	
	public Curso(String nome, String desc, Categoria categoria, double valorAtu, double valorDesc, int desconto)
	{
		this.Nome = nome;
		this.Descricao = desc;
		this.Categoria = categoria;
		this.Valor = valorAtu;
		this.ValorDesconto = valorDesc;
		this.Desconto = desconto;		
	}
	
	public Curso(int id, String nome, String desc, Categoria categoria, double valorAtu, double valorDesc, int desconto)
	{
		this.Id = id;
		this.Nome = nome;
		this.Descricao = desc;
		this.Categoria = categoria;
		this.Valor = valorAtu;
		this.ValorDesconto = valorDesc;
		this.Desconto = desconto;		
	}
	
	public int getId() {return Id;}
	
	public String getNome() {return Nome;}
	
	public String getDescricao() {return Descricao;}
	
	public Categoria getCategoria() {return Categoria;}
	
	public double getValor() {return this.Valor;}
	
	public double getValorDesconto() {return ValorDesconto;}
	
	public int getDesconto() {return Desconto;}
}
