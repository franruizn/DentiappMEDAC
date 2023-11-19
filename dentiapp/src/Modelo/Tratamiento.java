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

	
	//Relacion con consulta
    @OneToMany(mappedBy = "consulta_tratamiento", cascade = CascadeType.ALL)
    
    private List<Consulta> consulta;
    
    public List<Consulta> getConsulta(){
        return consulta;
    }
    
    public void addTratamiento(Consulta p){
        if (consulta == null) consulta=new ArrayList<>();
        consulta.add(p);
        p.setTratamiento(this);
    }
    
  //relacion con stock
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "idstock",referencedColumnName="idstock",insertable=false,updatable=false)
    private Stock tratamiento_stock; 

    public Stock getStock() {
        return tratamiento_stock;
    }
	public void setStock(Stock stock) {
		this.tratamiento_stock=stock;
		
	}
	
	
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
