package Modelo;


public class Usuario{

	private int idUsuario;
	private int pass;
	private int rol;
	
	// Constructores

	public Usuario() {
	}

	public Usuario(int idUsuario, int pass, int rol) {
		super();
		this.idUsuario = idUsuario;
		this.pass = pass;
		this.rol = rol;
	}

	// Métodos
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

}
