package dentiapp;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctor {
	//Atributos
	private int idDoctor, fk_idUsuario;
	private String nombre, especialidad;
	private ConexionMySQL cn = new ConexionMySQL();
	
	//Constructores
	public Doctor() {
		
	}
	
	public Doctor(int iddoctor, int fk_idusuario, int fk_idespecialidad, String nombre) throws SQLException {
		this.idDoctor = iddoctor;
		this.fk_idUsuario = fk_idusuario;
		
		cn.conectar();
		String consulta = "SELECT nombre FROM especialidad WHERE idespecialidad = " + fk_idespecialidad;
		ResultSet rset = cn.ejecutarSelect(consulta);
		if(rset.next()) {
			this.especialidad = rset.getString("nombre");
		}
		
		this.nombre = nombre;
	}
	
	//Métodos
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
		if(rset.next()) {
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
