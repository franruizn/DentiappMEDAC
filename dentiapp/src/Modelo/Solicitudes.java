package Modelo;

public class Solicitudes {

	private int idSolicitudes;
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
