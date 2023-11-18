package Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")

public class Paciente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpaciente")
	private int idPaciente;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "dni")
	private String dni;


	// Constructores
	public Paciente() {
	}

	public Paciente(int idPaciente, String nombre, String dni) {
		super();
		this.idPaciente = idPaciente;
		this.nombre = nombre;
		this.dni = dni;
	}

	// Métodos
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
