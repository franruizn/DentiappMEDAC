package Modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")

public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpedido")
	private int idPedido;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "fk_idstock")
	private int fk_idStock;
	
	
	
	//relacion con stock
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "idstock",referencedColumnName="idstock",insertable=false,updatable=false)
    private Stock pedido_stock; 

    public Stock getStock() {
        return pedido_stock;
    }
	public void setStock(Stock stock) {
		this.pedido_stock=stock;
		
	}
	
	
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