package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "solicitudes")

public class Solicitudes implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsolicitudes")
	private int idSolicitudes;

	@Column(name = "fk_idconsulta")
	private int fk_idConsulta;
	
	
	// Constructores
	public Solicitudes() {
	}

	public Solicitudes(int idSolicitudes, int fk_idConsulta) {
		super();
		this.idSolicitudes = idSolicitudes;
		this.fk_idConsulta = fk_idConsulta;
	}

	// Métodos
	public int getIdSolicitudes() {
		return idSolicitudes;
	}

	public void setIdSolicitudes(int idSolicitudes) {
		this.idSolicitudes = idSolicitudes;
	}

	public int getFk_idConsulta() {
		return fk_idConsulta;
	}

	public void setFk_idConsulta(int fk_idConsulta) {
		this.fk_idConsulta = fk_idConsulta;
	}

}
