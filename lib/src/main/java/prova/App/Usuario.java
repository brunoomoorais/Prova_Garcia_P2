package prova.App;

public class Usuario {
	
	private String Login;
	private String Senha;
	private int Usuario;
	
	public Usuario() {
		
	}

	public Usuario(String login, String senha, int usuario) {
		this.Login = login;
		this.Senha = senha;
		this.Usuario = usuario;
	}
	
	public String getLogin() {
		return Login;
	}
	
	public TipoUsuario getUsuario() {
		if(Usuario == 0){
			return TipoUsuario.Admin;
		}
		else
		{
			return TipoUsuario.Cliente;
		}
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

	public void setUsuario(int usuario) {
		Usuario = usuario;
	}
}
