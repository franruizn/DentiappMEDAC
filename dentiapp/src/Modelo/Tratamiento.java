package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tratamiento")

public class Tratamiento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtratamiento")
	private int idTratamiento;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "precio")
	private int precio;

	@Column(name = "fk_idstock")
	private int fk_idStock;

	// Constructores

	public Tratamiento() {

	}

	public Tratamiento(int idTratamiento, String nombre, int precio, int fk_idStock) {
		super();
		this.idTratamiento = idTratamiento;
		this.nombre = nombre;
		this.precio = precio;
		this.fk_idStock = fk_idStock;
	}

	// Métodos
	public int getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getFk_idStock() {
		return fk_idStock;
	}

	public void setFk_idStock(int fk_idStock) {
		this.fk_idStock = fk_idStock;
	}

}
