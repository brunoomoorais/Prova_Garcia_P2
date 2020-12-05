package prova.App;

public class Usuario {
	
	private String Login;
	private String Senha;
	
	public Usuario() {
		
	}

	public Usuario(String login, String senha) {
		this.Login = login;
		this.Senha = senha;
	}
	
	public String getLogin() {
		return Login;
	}	
	
	public String getSenha() {
		return Senha;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}
}
