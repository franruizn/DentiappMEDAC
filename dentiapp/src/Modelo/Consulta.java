package Modelo;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	//Relacion con solicitud
    @OneToMany(mappedBy = "solicitud_consulta", cascade = CascadeType.ALL)
    
    private List<Solicitudes> solicitudes;
    
    public List<Solicitudes> getSolicitudes(){
        return solicitudes;
    }
    
    public void addConsulta(Solicitudes p){
        if (solicitudes == null) solicitudes=new ArrayList<>();
        solicitudes.add(p);
        p.setConsulta(this);
    }
    
  //relacion con paciente
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "fk_idpaciente",referencedColumnName="idpaciente",insertable=false,updatable=false)
    private Paciente consulta_paciente; 

    public Paciente getPaciente() {
        return consulta_paciente;
    }
	public void setPaciente(Paciente paciente) {
		this.consulta_paciente=paciente;
		
	}
	
	//relacion con tratamiento
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "fk_idtratamiento",referencedColumnName="idtratamiento",insertable=false,updatable=false)
    private Tratamiento consulta_tratamiento; 

    public Tratamiento getTratamiento() {
        return consulta_tratamiento;
    }
	public void setTratamiento(Tratamiento tratamiento) {
		this.consulta_tratamiento=tratamiento;
		
	}
	
	//relacion con doctor
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "fk_iddoctor",referencedColumnName="iddoctor",insertable=false,updatable=false)
    private Doctor consulta_doctor; 

    public Doctor getDoctor() {
        return consulta_doctor;
    }
	public void setDoctor(Doctor doctor) {
		this.consulta_doctor=doctor;
		
	}
	
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
