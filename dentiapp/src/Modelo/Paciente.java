package Modelo;

public class Paciente {
	//Atributos
	private int idPaciente;
	private String nombre, dni;
	
	//Constructores
	public Paciente() {
	}

	public Paciente(int idPaciente, String nombre, String dni) {
		super();
		this.idPaciente = idPaciente;
		this.nombre = nombre;
		this.dni = dni;
	}

	//Métodos
	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
