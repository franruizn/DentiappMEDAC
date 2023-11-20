package Modelo;

public class Especialidad {
	
	private int idEspecialidad;
	private String nombre;
	
	//Constructores
	public Especialidad() {
	}

	public Especialidad(int idEspecialidad, String nombre) {
		super();
		this.idEspecialidad = idEspecialidad;
		this.nombre = nombre;
	}

	//Métodos
	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
