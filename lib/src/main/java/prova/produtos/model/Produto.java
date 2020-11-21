package prova.produtos.model;

public class Produto {
	
	private int Id;
	private String Nome;
	private Categoria Categoria;
	private String Marca;
	private double Valor;
	
	public Produto(int id, String nome, Categoria categoria, String marca, double valor)
	{
		this.Id = id;
		this.Nome = nome;
		this.Categoria = categoria;
		this.Marca = marca;
		this.Valor = valor;
	}
	
	public int getId() {return Id;}
	
	public String getNome() {return Nome;}
	
	public Categoria getCategoria() {return Categoria;}
	
	public String getMarca() {return Marca;}
	
	public double getValor() {return Valor;}
}
