package prova.produtos.model;

public class Categoria {
	private int Id;
	private String Nome;
	
	public Categoria(int id, String nome)
	{
		this.Id = id;
		this.Nome = nome;
	}
	
	public int getId() {return Id;}
	
	public String getNome() {return Nome;}
}
