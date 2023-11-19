package Modelo;

import java.io.Serializable;
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

	
	//Relacion con consulta
    @OneToMany(mappedBy = "consulta_paciente", cascade = CascadeType.ALL)
    
    private List<Consulta> consulta;
    
    public List<Consulta> getConsulta(){
        return consulta;
    }
    
    public void addPaciente(Consulta p){
        if (consulta == null) consulta=new ArrayList<>();
        consulta.add(p);
        p.setPaciente(this);
    }
    
    
  //Relacion con facturacion
    @OneToMany(mappedBy = "facturacion_paciente", cascade = CascadeType.ALL)
    
    private List<Facturacion> facturacion;
    
    public List<Facturacion> getFacturacion(){
        return facturacion;
    }
    
    public void addPaciente(Facturacion p){
        if (facturacion == null) facturacion=new ArrayList<>();
        facturacion.add(p);
        p.setPaciente(this);
    }
	

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
