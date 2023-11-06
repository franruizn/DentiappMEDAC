package Controlador;

import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMySQL {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/practicajdbc?useSSL=false";
	private static final String USUARIO = "root";
	private static final String CLAVE = "1234";
	Statement stm = null;
	DatabaseMetaData databaseMetaData;
	Connection cn = null;

	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	public Connection conectar() {

		try {
			cn = DriverManager.getConnection(URL, USUARIO, CLAVE);
			databaseMetaData = cn.getMetaData();
			ResultSet tables = databaseMetaData.getTables(null, null, "%", null);
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
			}
			System.out.println("Conexión OK");

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return cn;

	}

	public void desconectar() throws SQLException {
		if (cn != null && cn.isClosed()) {
			cn.close();
		}
	}

	public ResultSet ejecutarSelect(String consulta) throws SQLException {
		Statement stmt = cn.createStatement();
		ResultSet rset = stmt.executeQuery(consulta);
		return rset;
	}

	public int ejecutarIDU(String consulta) throws SQLException {
		Statement stmt = cn.createStatement();
		int fila = stmt.executeUpdate(consulta);
		return fila;
	}

	public DatabaseMetaData getDatabaseMetaData() {
		return databaseMetaData;
	}

	public Connection getConnection() {
		return cn;
	}

}