package Controlador;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Vista.adminFrame;
import Vista.doctorFrame;
import Vista.loginFrame;

public class ControladorSQL {

	private ConexionMySQL cn = new ConexionMySQL();
	private DatabaseMetaData metaDatos;

	public void insertarDatos(String nombreTabla, DefaultTableModel modeloDatos) throws SQLException {
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();
		String newValues = "";
		String nombreColumnas = "(";

		for (int i = 0; i < modeloDatos.getRowCount(); i++) {
			newValues = "";
			for (int j = 0; j < modeloDatos.getColumnCount(); j++) {

				if (modeloDatos.getValueAt(i, j) != null) {
					nombreColumnas += "`" + modeloDatos.getColumnName(j) + "`,";
					newValues += "'" + modeloDatos.getValueAt(i, j).toString() + "',";
				}

			}
			newValues = newValues.substring(0, newValues.length() - 1);
			nombreColumnas = nombreColumnas.substring(0, nombreColumnas.length() - 1) + ")";
			// Insertamos los valores en la tabla
			String consulta = "INSERT INTO `dentiapp`.`" + nombreTabla + "`" + nombreColumnas + " VALUES (" + newValues + ");";
			System.out.println(consulta);
			cn.ejecutarIDU(consulta);
		}
		cn.desconectar();
	}


	public void tryLogin(JTextField txtUsuario, JPasswordField txtPass, loginFrame frame) throws SQLException {
		cn.conectar();
		String user = txtUsuario.getText();
		String pass = String.valueOf((txtPass).getPassword());

		// Consulta a ejecutar
		String consulta = "SELECT rol FROM usuario WHERE idusuario = '" + user + "' AND pass = '" + pass + "';";
		System.out.println(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);

		// Comprobamos si existen resultados, si no hay error y el tipo de rol de
		// usuario
		if (rset.next()) {
			int rol = rset.getInt("rol");
			System.out.println(rol);
			if (rol == 1) {
				frame.dispose();
				adminFrame aframe = new adminFrame();
				aframe.setVisible(true);
			} else {
				frame.dispose();
				doctorFrame dframe = new doctorFrame();
				dframe.setVisible(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error al iniciar sesión - Los credenciales no son correctos",
					"Error al iniciar sesión", JOptionPane.WARNING_MESSAGE);
		}
	}

	public DefaultTableModel cargarDatos(String nombreTabla, DefaultTableModel modeloDatos) throws SQLException {
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();

		// Se ejecuta una consulta SQL para obtener los datos de la tabla.
		ResultSet rset = cn.ejecutarSelect("SELECT * FROM " + nombreTabla);
		modeloDatos.setRowCount(0);
		String nombreColumnas = obtenerColumnas(nombreTabla);
		String[] listaColumnas = nombreColumnas.split(",");
		modeloDatos.setColumnCount(listaColumnas.length);
		modeloDatos.setColumnIdentifiers(listaColumnas);
		while (rset.next()) {
			modeloDatos.setRowCount(modeloDatos.getRowCount() + 1);
			for (int i = 0; i < modeloDatos.getColumnCount(); i++) {
				modeloDatos.setValueAt(rset.getObject(i + 1), modeloDatos.getRowCount() - 1, i);
			}
		}

		// Se cierra la conexión a la base de datos.
		cn.desconectar();
		return modeloDatos;
	}

	public String obtenerColumnas(String nombreTabla) throws SQLException {
		// Método para obtener las columnas de una tabla y guardarlas en un String que
		// pasaremos a array
		String nombreColumnas = "";
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();
		ResultSet rset = metaDatos.getColumns(null, null, nombreTabla, null);
		while (rset.next()) {
			String nombreColumnaActual = rset.getString("COLUMN_NAME");
			nombreColumnas += nombreColumnaActual + ",";
		}
		nombreColumnas = nombreColumnas.substring(0, nombreColumnas.length() - 1); // Elimina la última coma
		return nombreColumnas;
	}

	public void eliminarDatos(String nombreTabla) throws SQLException {
		// Método para eliminar datos de una tabla
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();

		ResultSet rset = metaDatos.getPrimaryKeys(null, null, nombreTabla);
		String campoPrimario = "";
		boolean valido = false;

		while (rset.next()) {
			campoPrimario = rset.getString("COLUMN_NAME");
			// Obtenemos la columna del campo primario
		}

		while (!valido) {
			String valorCampo = JOptionPane
					.showInputDialog("Ingrese el id de " + nombreTabla + " que deseas eliminar:");
			// Le solicitamos el id del dato que quiere eliminar
			if (valorCampo.isEmpty() || valorCampo.equals(null)) {
				JOptionPane.showMessageDialog(null,
						"El dato introducido no es correcto, por favor, introduce un dato válido");
			} else {
				valido = true;
				String consulta = "DELETE FROM " + nombreTabla + " WHERE " + campoPrimario + " = " + valorCampo;
				cn.ejecutarIDU(consulta);
				// Eliminamos el dato de la tabla

				JOptionPane.showMessageDialog(null, "Registro eliminado con éxito en " + nombreTabla + "s",
						"Registro eleminado", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		cn.desconectar();
	}
	
	public void eliminarFila(String nombreTabla, String key) throws SQLException {
		cn.conectar();
		ResultSet rset = metaDatos.getPrimaryKeys(null, null, nombreTabla);
		String campoPrimario = "";
		while (rset.next()) {
			campoPrimario = rset.getString("COLUMN_NAME");
			// Obtenemos la columna del campo primario
		}
		String consulta = "DELETE FROM " + nombreTabla + " WHERE " + campoPrimario + " = " + key;
		cn.ejecutarIDU(consulta);
		JOptionPane.showMessageDialog(null, "Se ha eliminado con éxito",
				nombreTabla + " eliminado", JOptionPane.INFORMATION_MESSAGE);
		cn.desconectar();
	}

	public String obtenerIdEspecialidad(String especialidad) throws SQLException {
		cn.conectar();
		int id = 0;
		String idNum = "";

		// Consulta a ejecutar
		String consulta = "SELECT idespecialidad FROM especialidad WHERE nombre = '" + especialidad + "';";
		ResultSet rset = cn.ejecutarSelect(consulta);

		if (rset.next()) {
			id = rset.getInt("idespecialidad");
			idNum = Integer.toString(id);
		}

		cn.desconectar();
		return idNum;
	}
	
	public DefaultComboBoxModel<?> rellenarComboBox(String nombreTabla, DefaultComboBoxModel<?> modeloDatos, String campo) throws SQLException {
		cn.conectar();
        metaDatos = cn.getConnection().getMetaData();

        // Se ejecuta una consulta SQL para obtener los datos de la tabla.
        ResultSet rset = cn.ejecutarSelect("SELECT * FROM " + nombreTabla);
        
        ResultSet rs = metaDatos.getPrimaryKeys(null, null, nombreTabla);
        @SuppressWarnings("unused")
		String primaryKey = "";
        String datos = "";
        
        String nombreColumnas = obtenerColumnas(nombreTabla);
        String[] listaColumnas = nombreColumnas.split(",");
        
        while (rs.next()) {
            primaryKey = rs.getString("COLUMN_NAME");
        }
        
        while (rset.next()) {
            for(int i = 0; i < listaColumnas.length; i++) {
            	datos += rset.getString(i)+ ",";
            }
        }
        
        datos = datos.substring(0, datos.length() - 1);
        String[] listaDatos = datos.split(",");
        
        HashMap<String,String> valores = new HashMap<String,String>();
        for(int i = 0; i < listaColumnas.length; i++) {
        	valores.put(listaColumnas[i],listaDatos[i]);
        }
        
        
        
        // Se cierra la conexión a la base de datos.
        cn.desconectar();
		
		return modeloDatos;
	}
	
	public  ArrayList <String[]> obtenerConsulta(String fecha) throws SQLException{
		cn.conectar();
		
		
		ArrayList <String[]> consultas = new ArrayList();
		// Consulta a ejecutar
		String consulta = "Select fk_idpaciente, fk_iddoctor, fk_idtratamiento from consulta where fecha="+fecha+";";
		System.out.print(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);

		if (rset.next()) {
			String[] consultaNueva = new String[3];
			consultaNueva[0] = rset.getString("fk_idpaciente");
			consultaNueva[1] = rset.getString("fk_iddoctor");
			consultaNueva[2] = rset.getString("fk_idtratamiento");
			consultas.add(consultaNueva);
		}
		

		cn.desconectar();
		return consultas;
	}

}
