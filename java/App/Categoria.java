package prova.App;

public class Categoria {
	private int Id;
	private String Nome;
	
	public Categoria() {
		
	}
	
	public Categoria(String nome)
	{
		this.Nome = nome;
	}
	
	public Categoria(int id, String nome)
	{
		this.Id = id;
		this.Nome = nome;
	}
	
	public int getId() {return Id;}
	
	public String getNome() {return Nome;}
	
	//public void setId(int id){ this.Id = id;}
	
	public void setNome(String nome) { this.Nome = nome;}	
}
