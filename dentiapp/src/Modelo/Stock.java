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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	
	//Relacion con tratamiento
    @OneToMany(mappedBy = "tratamiento_stock", cascade = CascadeType.ALL)
    
    private List<Tratamiento> tratamiento;
    
    public List<Tratamiento> getTratamiento(){
        return tratamiento;
    }
    
    public void addStock(Tratamiento p){
        if (tratamiento == null) tratamiento=new ArrayList<>();
        tratamiento.add(p);
        p.setStock(this);
    }
    
  //Relacion con pedido
    @OneToMany(mappedBy = "pedido_stock", cascade = CascadeType.ALL)
    
    private List<Pedido> pedido;
    
    public List<Pedido> getPedido(){
        return pedido;
    }
    
    public void addStock(Pedido p){
        if (pedido == null) pedido=new ArrayList<>();
        pedido.add(p);
        p.setStock(this);
    }
    
    
  //relacion con proveedor
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "idproveedor",referencedColumnName="idproveedor",insertable=false,updatable=false)
    private Proveedor stock_proveedor; 

    public Proveedor getProveedor() {
        return stock_proveedor;
    }
	public void setProveedor(Proveedor proveedor) {
		this.stock_proveedor=proveedor;
		
	}
	
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
