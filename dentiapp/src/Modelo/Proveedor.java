package Modelo;



public class Proveedor{

	private int idProveedor;
	private String nombre;
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
