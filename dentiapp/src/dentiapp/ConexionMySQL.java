package dentiapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMySQL {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/dentiapp?useSSL=false";
	private static final String USUARIO = "root";
	private static final String CLAVE = "1234";
	Statement stm = null;
	ResultSet resultado = null;
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
			stm = cn.createStatement();
			System.out.println("Conexión OK");

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		
		return cn;
	
	}
	
	public void desconectar()throws SQLException{
        if(cn!=null && cn.isClosed()){
        cn.close();  
        }
    }
    
	
	public ResultSet ejecutarSelect(String consulta)throws SQLException{
        Statement stmt=cn.createStatement();
        ResultSet rset=stmt.executeQuery(consulta);
        
        return rset;
    }
    
     public int ejecutarInsertDeleteUpdate(String consulta) throws SQLException{
        Statement stmt=cn.createStatement();
        int fila=stmt.executeUpdate(consulta);
        return fila;
    }
     
     
}