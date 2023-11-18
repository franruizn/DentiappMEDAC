package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")

public class Stock implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idstock")
	private int idStock;

	@Column(name = "fk_idproveedor")
	private int fk_idProveedor;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "cantidad")
	private int cantidad;
	
	
	// Constructores

	public Stock() {

	}

	public Stock(int idStock, int fk_idProveedor, String nombre, int cantidad) {
		super();
		this.idStock = idStock;
		this.fk_idProveedor = fk_idProveedor;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	// Métodos
	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	public int getFk_idProveedor() {
		return fk_idProveedor;
	}

	public void setFk_idProveedor(int fk_idProveedor) {
		this.fk_idProveedor = fk_idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
