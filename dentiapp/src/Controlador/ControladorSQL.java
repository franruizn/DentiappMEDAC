package Controlador;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

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
			String consulta = "INSERT INTO `dentiapp`.`" + nombreTabla + "`" + nombreColumnas + " VALUES (" + newValues
					+ ");";
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
		JOptionPane.showMessageDialog(null, "Se ha eliminado con éxito", nombreTabla + " eliminado",
				JOptionPane.INFORMATION_MESSAGE);
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

	public DefaultComboBoxModel<?> rellenarComboBox(String nombreTabla, String campo) throws SQLException {
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();
		DefaultComboBoxModel<String> modeloDatos = new DefaultComboBoxModel<>();

		String consulta = "SELECT " + campo + " FROM " + nombreTabla;
		ResultSet rset = cn.ejecutarSelect(consulta);
		ArrayList<String> datos = new ArrayList<>();
		while (rset.next()) {
			datos.add(rset.getString(campo));
		}

		for (int i = 0; i < datos.size(); i++) {
			modeloDatos.addElement(datos.get(i));
		}

		cn.desconectar();

		return modeloDatos;
	}
	
	public DefaultComboBoxModel<?> rellenarComboBoxDoble(String nombreTabla, String campo, String campo2) throws SQLException {
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();
		DefaultComboBoxModel<String> modeloDatos = new DefaultComboBoxModel<>();

		String consulta = "SELECT " + campo + "," + campo2 + " FROM " + nombreTabla;
		ResultSet rset = cn.ejecutarSelect(consulta);
		ArrayList<String> datos = new ArrayList<>();
		while (rset.next()) {
			String resultado = rset.getString(campo2) + "-" +rset.getString(campo);
			datos.add(resultado);
		}

		for (int i = 0; i < datos.size(); i++) {
			modeloDatos.addElement(datos.get(i));
		}

		cn.desconectar();

		return modeloDatos;
	}

	public ArrayList<String[]> obtenerConsulta(String fecha, String hora) throws SQLException {
		cn.conectar();

		ArrayList<String[]> consultas = new ArrayList();
		// Consulta a ejecutar
		String consulta = "SELECT fk_idpaciente, fk_iddoctor, fk_idtratamiento, observaciones FROM consulta WHERE fecha="
				+ fecha + "AND hora=" + hora + ";";
		System.out.print(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);

		while (rset.next()) {
			String[] consultaNueva = new String[4];
			consultaNueva[0] = rset.getString("fk_idpaciente");
			consultaNueva[1] = rset.getString("fk_iddoctor");
			consultaNueva[2] = rset.getString("fk_idtratamiento");
			consultaNueva[3] = rset.getString("observaciones");
			consultas.add(consultaNueva);
		}

		cn.desconectar();
		return consultas;
	}
	
	public ArrayList<String[]> obtenerOdontograma(String idPaciente, int diente) throws SQLException {
		cn.conectar();

		ArrayList<String[]> consultas = new ArrayList();
		// Consulta a ejecutar
		String consulta = "SELECT iddiente, observaciones, ausencias, caries, implantes, protesis, sangrado, rayosx FROM odontograma WHERE fk_idpaciente="
				+ idPaciente + " AND iddiente = " + diente + ";";
		System.out.println(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);

		while (rset.next()) {
			String[] consultaNueva = new String[8];
			consultaNueva[0] = rset.getString("iddiente");
			consultaNueva[1] = rset.getString("observaciones");
			consultaNueva[2] = rset.getString("ausencias");
			consultaNueva[3] = rset.getString("caries");
			consultaNueva[4] = rset.getString("implantes");
			consultaNueva[5] = rset.getString("protesis");
			consultaNueva[6] = rset.getString("sangrado");
			consultaNueva[7] = rset.getString("rayosx");
			consultas.add(consultaNueva);
		}
		
		for(int i = 0; i < consultas.size(); i++) {
			System.out.println(consultas.get(i).toString());
		}

		cn.desconectar();
		return consultas;
	}

	public String selectWhere(String nombreTabla, String campoBuscar, String campo, String valor) throws SQLException {
		cn.conectar();

		String consulta = "SELECT " + campoBuscar + " FROM " + nombreTabla + " WHERE " + campo + " = '" + valor + "'";
		System.out.println(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);
		String resultado = null;
		if (rset.next()) {
			resultado = rset.getString(campoBuscar);
		}
		cn.desconectar();
		return resultado;

	}

	public String selectWhereDoble(String nombreTabla, String campoBuscar, String campo, String valor, String campo2,
			String valor2) throws SQLException {
		cn.conectar();

		String consulta = "SELECT " + campoBuscar + " FROM " + nombreTabla + " WHERE " + campo + " = " + valor + "and "
				+ campo2 + " = " + valor2;
		System.out.println(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);
		String resultado = null;
		if (rset.next()) {
			resultado = rset.getString(campoBuscar);
		}
		cn.desconectar();
		return resultado;
	}

	public void updateSQL(String nombreTabla, String[] nombreColumnas, String[] newValues) throws SQLException {
		cn.conectar();
		ResultSet rset = metaDatos.getPrimaryKeys(null, null, nombreTabla);
		String campoPrimario = "";
		String valorId = "";
		while (rset.next()) {
			campoPrimario = rset.getString("COLUMN_NAME");
			// Obtenemos la columna del campo primario
		}
		String consulta = "UPDATE " + nombreTabla + " SET ";
		for (int i = 0; i < nombreColumnas.length; i++) {
			if (!nombreColumnas[i].equals(campoPrimario)) {
				consulta += nombreColumnas[i] + "=";
				consulta += newValues[i] + ",";
			} else {
				valorId = newValues[i];
			}
		}

		consulta = consulta.substring(0, consulta.length() - 1) + " WHERE " + campoPrimario + " = " + valorId;
		System.out.println(consulta);
		cn.ejecutarIDU(consulta);
		cn.desconectar();
	}

	public void insertarConsulta(String nombreTabla, String nombreColumnas, String newValues) throws SQLException {
		cn.conectar();
		String consulta = "INSERT INTO `dentiapp`.`" + nombreTabla + "` (" + nombreColumnas + ") VALUES (" + newValues
				+ ");";
		System.out.println(consulta);
		cn.ejecutarIDU(consulta);
		cn.desconectar();
	}

	public void borrarConsulta(String id, String fecha) throws SQLException {
		cn.conectar();
		String nombreTabla = "consulta";
		String consulta = "DELETE FROM " + nombreTabla + " WHERE fk_idpaciente = " + id + " and fecha = " + fecha;
		cn.ejecutarIDU(consulta);
		cn.desconectar();

	}

	public DefaultComboBoxModel<String> obtenerProveedor(String idmaterial) throws SQLException {
		cn.conectar();
		// String consulta = "SELECT nombre FROM proveedor WHERE idproveedor = (SELECT
		// fk_idproveedor FROM stock WHERE nombre = '" + idmaterial +"');";
		String consulta = "SELECT proveedor.nombre FROM  stock INNER JOIN proveedor ON stock.fk_idproveedor = proveedor.idproveedor WHERE stock.nombre = '"
				+ idmaterial + "';";
		System.out.println(consulta);
		cn.ejecutarSelect(consulta);
		DefaultComboBoxModel<String> modeloDatos = new DefaultComboBoxModel<>();
		ResultSet rset = cn.ejecutarSelect(consulta);
		ArrayList<String> datos = new ArrayList<>();
		while (rset.next()) {
			datos.add(rset.getString("proveedor.nombre"));
		}

		for (int i = 0; i < datos.size(); i++) {
			modeloDatos.addElement(datos.get(i));
		}

		cn.desconectar();

		return modeloDatos;
	}

	public DefaultComboBoxModel<?> rellenarComboBoxWhere(String nombreTabla, String campo, String condicion,
			String campoCondicion) throws SQLException {
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();
		DefaultComboBoxModel<String> modeloDatos = new DefaultComboBoxModel<>();

		String consulta = "SELECT " + campo + " FROM " + nombreTabla + " WHERE " + campoCondicion + " = '" + condicion
				+ "';";
		// String consulta = "SELECT " + campo + " FROM " + nombreTabla + " INNER JOIN
		// proveedor ON stock.fk_idproveedor WHERE proveedor.nombre = '" + condicion +
		// "';";
		System.out.println(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);
		ArrayList<String> datos = new ArrayList<>();
		while (rset.next()) {
			datos.add(rset.getString(campo));
		}

		for (int i = 0; i < datos.size(); i++) {
			modeloDatos.addElement(datos.get(i));
		}

		cn.desconectar();

		return modeloDatos;
	}

	public void borrarConsultaDoble(String id, String fecha, String hora) throws SQLException {
		cn.conectar();
		String nombreTabla = "consulta";
		String consulta = "DELETE FROM " + nombreTabla + " WHERE fk_idpaciente = " + id + " and fecha = " + fecha
				+ " and hora = " + hora;
		cn.ejecutarIDU(consulta);
		cn.desconectar();

	}
	
	public DefaultTableModel cargarSolicitudesAceptadas(String nombreTabla, DefaultTableModel modeloDatos, String[] columnas) throws SQLException {
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();

		// Se ejecuta una consulta SQL para obtener los datos de la tabla.
		ResultSet rset = cn.ejecutarSelect("SELECT " + String.join(",", columnas) + " FROM " + nombreTabla + " WHERE idsolicitudes = 1");
		modeloDatos.setRowCount(0);

		// Se establece el número de columnas del modelo de datos.
		modeloDatos.setColumnCount(columnas.length);

		// Se establece el nombre de las columnas del modelo de datos.
		modeloDatos.setColumnIdentifiers(columnas);

		while (rset.next()) {
			modeloDatos.setRowCount(modeloDatos.getRowCount() + 1);

			// Se insertan los datos de la fila actual en el modelo de datos.
			for (int i = 0; i < columnas.length; i++) {
				modeloDatos.setValueAt(rset.getObject(i + 1), modeloDatos.getRowCount() - 1, i);
			}
		}
		
		cn.desconectar();
		return modeloDatos;
	}
	
	public void ejecutarInsertar(String consulta) throws SQLException {
		cn.conectar();
		cn.ejecutarIDU(consulta);
		cn.desconectar();
	}
	
	public void crearOdontograma(String dni) throws SQLException {
		cn.conectar();
		String idPaciente = selectWhere("paciente","idpaciente","dni",dni);
		
		for(int i = 1; i <= 20; i++) {
			String consulta = "INSERT INTO odontograma VALUES (0,'" + idPaciente + "'," + i + ",'',0,0,0,0,0,0)";
			cn.ejecutarIDU(consulta);
		}
		cn.desconectar();
	}
	
	public void CambiarContraseña(String idusuario) {
		cn.conectar();
		try {
			String contraseña = "'" + JOptionPane.showInputDialog("Añada la nueva contraseña") + "'";
			String consulta = "UPDATE dentiapp.usuario SET pass=" + contraseña + " WHERE idusuario='" + idusuario + "'";
			cn.ejecutarIDU(consulta);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cambiarAceptado(String idsolicitudes) throws SQLException {
		cn.conectar();
		String consulta = "UPDATE solicitudes SET aceptado = 1 WHERE idsolicitudes = " + idsolicitudes;
		cn.ejecutarIDU(consulta);
		cn.desconectar();
	}
	
	public DefaultTableModel cargarSolicitudesPendientes(String nombreTabla, DefaultTableModel modeloDatos, String[] columnas) throws SQLException {
		cn.conectar();
		metaDatos = cn.getConnection().getMetaData();

		// Se ejecuta una consulta SQL para obtener los datos de la tabla.
		ResultSet rset = cn.ejecutarSelect("SELECT " + String.join(",", columnas) + " FROM " + nombreTabla + " WHERE aceptado = 0");
		modeloDatos.setRowCount(0);

		// Se establece el número de columnas del modelo de datos.
		modeloDatos.setColumnCount(columnas.length);

		// Se establece el nombre de las columnas del modelo de datos.
		modeloDatos.setColumnIdentifiers(columnas);

		while (rset.next()) {
			modeloDatos.setRowCount(modeloDatos.getRowCount() + 1);

			// Se insertan los datos de la fila actual en el modelo de datos.
			for (int i = 0; i < columnas.length; i++) {
				modeloDatos.setValueAt(rset.getObject(i + 1), modeloDatos.getRowCount() - 1, i);
			}
		}
		
		cn.desconectar();
		return modeloDatos;
	}

}
