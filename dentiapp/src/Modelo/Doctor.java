package Modelo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import Controlador.ConexionMySQL;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddoctor")
	private int idDoctor;

	@Column(name = "fk_idusuario")
	private int fk_idUsuario;

	@Column(name = "fk_idespecialidad")
	private String especialidad;

	@Column(name = "nombre")
	private String nombre;

	
	//Relacion con consulta
    @OneToMany(mappedBy = "consulta_doctor", cascade = CascadeType.ALL)
    
    private List<Consulta> consulta;
    
    public List<Consulta> getConsulta(){
        return consulta;
    }
    
    public void addDoctor(Consulta p){
        if (consulta == null) consulta=new ArrayList<>();
        consulta.add(p);
        p.setDoctor(this);
    }
    
    
  //relacion con especialidad
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "idespecialidad",referencedColumnName="idespecialidad",insertable=false,updatable=false)
    private Especialidad especialidad_doctor; 

    public Especialidad getEspecialidad() {
        return especialidad_doctor;
    }
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad_doctor=especialidad;
		
	}
	
	//relacion con usuario
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "idusuario",referencedColumnName="idusuario",insertable=false,updatable=false)
    private Usuario usuario_doctor; 

    public Usuario getUsuario() {
        return usuario_doctor;
    }
	public void setUsuario(Usuario usuario) {
		this.usuario_doctor=usuario;
		
	}
	
	
	// Atributos
	private ConexionMySQL cn = new ConexionMySQL();

	// Constructores
	public Doctor() {

	}

	public Doctor(int iddoctor, int fk_idusuario, int fk_idespecialidad, String nombre) throws SQLException {
		this.idDoctor = iddoctor;
		this.fk_idUsuario = fk_idusuario;

		cn.conectar();
		String consulta = "SELECT nombre FROM especialidad WHERE idespecialidad = " + fk_idespecialidad;
		ResultSet rset = cn.ejecutarSelect(consulta);
		if (rset.next()) {
			this.especialidad = rset.getString("nombre");
		}

		this.nombre = nombre;
	}

	// Métodos
	public int getIddoctor() {
		return idDoctor;
	}

	public void setIddoctor(int iddoctor) {
		this.idDoctor = iddoctor;
	}

	public int getFk_idusuario() {
		return fk_idUsuario;
	}

	public void setFk_idusuario(int fk_idusuario) {
		this.fk_idUsuario = fk_idusuario;
	}

	public String getFk_idespecialidad() {
		return especialidad;
	}

	public void setFk_idespecialidad(int fk_idespecialidad) throws SQLException {
		cn.conectar();
		String consulta = "SELECT nombre FROM especialidad WHERE idespecialidad = " + fk_idespecialidad;
		ResultSet rset = cn.ejecutarSelect(consulta);
		if (rset.next()) {
			this.especialidad = rset.getString(fk_idespecialidad);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
