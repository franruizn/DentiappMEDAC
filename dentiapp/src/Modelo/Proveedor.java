package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")

public class Proveedor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproveedor")
	private int idProveedor;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "telefono")
	private int telefono;
	
	
	// Constructores
	public Proveedor() {
	}

	public Proveedor(int idProveedor, String nombre, int telefono) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	// Métodos
	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
