package Modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	//relacion con consulta
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "idconsulta",referencedColumnName="idconsulta",insertable=false,updatable=false)
    private Consulta solicitud_consulta; 

    public Consulta getSolicitudes() {
        return solicitud_consulta;
    }
	public void setConsulta(Consulta consulta) {
		this.solicitud_consulta=consulta;
		
	}
	
	
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
