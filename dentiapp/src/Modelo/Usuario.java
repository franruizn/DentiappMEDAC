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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")

public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private int idUsuario;

	@Column(name = "pass")
	private int pass;

	@Column(name = "rol")
	private int rol;
	
	
	//Relacion con doctor
    @OneToMany(mappedBy = "usuario_doctor", cascade = CascadeType.ALL)
    
    private List<Doctor> doctor;
    
    public List<Doctor> getDoctor(){
        return doctor;
    }
    
    public void addUsuario(Doctor p){
        if (doctor == null) doctor=new ArrayList<>();
        doctor.add(p);
        p.setUsuario(this);
    }
	
	
	// Constructores

	public Usuario() {
	}

	public Usuario(int idUsuario, int pass, int rol) {
		super();
		this.idUsuario = idUsuario;
		this.pass = pass;
		this.rol = rol;
	}

	// Métodos
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

}
