package Modelo;


public class Pedido {

	private int idPedido;
	private int cantidad;
	private int fk_idStock;
		
	//Constructores
	public Pedido() {		
	}
	public Pedido(int idPedido, int cantidad, int fk_idStock) {
		super();
		this.idPedido = idPedido;
		this.cantidad = cantidad;
		this.fk_idStock = fk_idStock;
	}
	
	//Métodos
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getFk_idStock() {
		return fk_idStock;
	}
	public void setFk_idStock(int fk_idStock) {
		this.fk_idStock = fk_idStock;
	}	
	
}