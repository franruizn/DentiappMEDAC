package Modelo;

public class Stock{
	
	private int idStock;
	private int fk_idProveedor;
	private String nombre;
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
