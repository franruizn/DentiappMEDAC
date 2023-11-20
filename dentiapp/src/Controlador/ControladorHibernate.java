package Controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Modelo.Doctor;
import Modelo.Especialidad;
import Modelo.Usuario;
import Modelo.Consulta;
import Modelo.Facturacion;
import Modelo.Paciente;
import Modelo.Tratamiento;
import Modelo.Stock;
import Modelo.Solicitudes;
import Modelo.Proveedor;
import Modelo.Pedido;

public class ControladorHibernate {
	private SessionFactory instancia;
	private Session sesion;
	
	public ControladorHibernate() {
		instancia = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(Especialidad.class)
                .addAnnotatedClass(Usuario.class)
                .addAnnotatedClass(Consulta.class)
                .addAnnotatedClass(Facturacion.class)
                .addAnnotatedClass(Paciente.class)
                .addAnnotatedClass(Tratamiento.class)
                .addAnnotatedClass(Stock.class)
                .addAnnotatedClass(Solicitudes.class)
                .addAnnotatedClass(Proveedor.class)
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();
	}
	
	
}
