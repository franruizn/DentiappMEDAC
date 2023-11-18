package Modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facturacion")

public class Facturacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfacturacion")
	private int idFacturacion;

	@Column(name = "fk_idpaciente")
	private int fk_idPaciente;

	@Column(name = "pagado")
	private int pagado;

	@Column(name = "pagar")
	private int pagar;

	//Constructores
	public Facturacion() {
	}

	public Facturacion(int idFacturacion, int fk_idPaciente, int pagado, int pagar) {
		super();
		this.idFacturacion = idFacturacion;
		this.fk_idPaciente = fk_idPaciente;
		this.pagado = pagado;
		this.pagar = pagar;
	}
	
	// Métodos
	public int getIdFacturacion() {
		return idFacturacion;
	}

	public void setIdFacturacion(int idFacturacion) {
		this.idFacturacion = idFacturacion;
	}

	public int getFk_idPaciente() {
		return fk_idPaciente;
	}

	public void setFk_idPaciente(int fk_idPaciente) {
		this.fk_idPaciente = fk_idPaciente;
	}

	public int getPagado() {
		return pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}

	public int getPagar() {
		return pagar;
	}

	public void setPagar(int pagar) {
		this.pagar = pagar;
	}

}
