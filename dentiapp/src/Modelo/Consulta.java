package Modelo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")

public class Consulta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idconsulta")
	private int idConsulta;

	@Column(name = "fk_idpaciente")
	private int fk_idPaciente;

	@Column(name = "fk_iddoctor")
	private int fk_idDoctor;
	
	@Column(name = "fk_idtratamiento")
	private int fk_idTratamiento;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "fecha")
	private Date fecha;
	
	
	// Constructores
	public Consulta() {		
	}
	public Consulta(int idConsulta, int fk_idPaciente, int fk_idDoctor, int fk_idTratamiento, String observaciones,
			Date fecha) {
		super();
		this.idConsulta = idConsulta;
		this.fk_idPaciente = fk_idPaciente;
		this.fk_idDoctor = fk_idDoctor;
		this.fk_idTratamiento = fk_idTratamiento;
		this.observaciones = observaciones;
		this.fecha = fecha;
	}
		
	//Métodos
	public int getIdConsulta() {
		return idConsulta;
	}


	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}


	public int getFk_idPaciente() {
		return fk_idPaciente;
	}


	public void setFk_idPaciente(int fk_idPaciente) {
		this.fk_idPaciente = fk_idPaciente;
	}


	public int getFk_idDoctor() {
		return fk_idDoctor;
	}


	public void setFk_idDoctor(int fk_idDoctor) {
		this.fk_idDoctor = fk_idDoctor;
	}


	public int getFk_idTratamiento() {
		return fk_idTratamiento;
	}


	public void setFk_idTratamiento(int fk_idTratamiento) {
		this.fk_idTratamiento = fk_idTratamiento;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
