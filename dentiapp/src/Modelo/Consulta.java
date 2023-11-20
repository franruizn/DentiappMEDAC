package Modelo;

import java.sql.Date;

public class Consulta {
	
	private int idConsulta;
	private int fk_idPaciente;
	private int fk_idDoctor;
	private int fk_idTratamiento;
	private String observaciones;
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
