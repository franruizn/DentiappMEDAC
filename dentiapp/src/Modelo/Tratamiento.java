package Modelo;


public class Tratamiento{

	private int idTratamiento;
	private String nombre;
	private int precio;
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
