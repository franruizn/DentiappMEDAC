package Controlador;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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

@SuppressWarnings("deprecation")
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
	
	public void insertar(Object objeto) {
		sesion = instancia.openSession();
		sesion.beginTransaction();
		sesion.save(objeto);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public void delete(Object objeto) {
		sesion = instancia.openSession();
		sesion.beginTransaction();
		sesion.delete(objeto);
		sesion.getTransaction().commit();
        sesion.close();
	}
	
	public void update(Object objeto) {
        sesion = instancia.openSession();
        sesion.beginTransaction();
        sesion.update(objeto);
        sesion.getTransaction().commit();
        sesion.close();
    }	
	
	@SuppressWarnings({ "unchecked" })
	public ArrayList<Object> getDatos(Class<?> clase) {
        sesion = instancia.openSession();
        sesion.beginTransaction();

        String hql = "FROM " + clase.getSimpleName().toLowerCase();

		Query<Object> consulta = (Query<Object>) sesion.createQuery(hql, clase);
        List<Object> result = consulta.getResultList();
        sesion.close();

        return (ArrayList<Object>) result;
    }
	
	@SuppressWarnings({ "unchecked" })
	public ArrayList<Object> getDatosWhere(Class<?> clase, String condicion, String valor) {
        sesion = instancia.openSession();
        sesion.beginTransaction();

        String hql = "FROM " + clase.getSimpleName().toLowerCase() + " WHERE " + condicion + " =: " + valor;

        Query<Object> consulta = (Query<Object>) sesion.createQuery(hql, clase);
        consulta.setParameter(condicion, valor);
        List<Object> result = consulta.getResultList();
        sesion.close();

        return (ArrayList<Object>) result;
    }
	
}
