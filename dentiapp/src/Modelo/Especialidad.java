package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "especialidad")

public class Especialidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idespecialidad")
	private int idEspecialidad;

	@Column(name = "nombre")
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
