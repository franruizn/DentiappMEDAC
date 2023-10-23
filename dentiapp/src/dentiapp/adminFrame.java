package dentiapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class adminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPacientes;
	private JTable tblDoctores;
	private ConexionMySQL cn = new ConexionMySQL();
	private ArrayList<Doctor> listaDoctores = new ArrayList<>();
	private ArrayList<Paciente> listaPacientes = new ArrayList<>();
	private String[] columnasDoctores = {"ID","Nombre","Especialidad"};
	private String[] columnasPacientes = {"ID","Nombre","DNI"};
	private DefaultTableModel modeloDoctores = new DefaultTableModel();
	private DefaultTableModel modeloPacientes = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminFrame frame = new adminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public adminFrame() throws SQLException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDoctores = new JLabel("LISTA DOCTORES");
		lblListaDoctores.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDoctores.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblListaDoctores.setBounds(600, 191, 195, 26);
		contentPane.add(lblListaDoctores);
		
		JLabel lblListaPacientes = new JLabel("LISTA PACIENTES");
		lblListaPacientes.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblListaPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaPacientes.setBounds(186, 191, 195, 26);
		contentPane.add(lblListaPacientes);
		
		JButton btnDoctores = new JButton("DOCTORES");
		btnDoctores.setBounds(190, 35, 106, 21);
		contentPane.add(btnDoctores);
		
		JButton btnPacientes = new JButton("PACIENTES");
		btnPacientes.setBounds(40, 35, 112, 21);
		contentPane.add(btnPacientes);
		
		JButton btnFacturacion = new JButton("FACTURACION");
		btnFacturacion.setBounds(791, 35, 124, 21);
		contentPane.add(btnFacturacion);
		
		JButton btnStock = new JButton("STOCK");
		btnStock.setBounds(652, 35, 99, 21);
		contentPane.add(btnStock);
		
		JButton btnActListaDoctores = new JButton("ACTUALIZAR");
		modeloDoctores.setColumnIdentifiers(columnasDoctores);
		
		btnActListaDoctores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					rellenarListaDoctores();
					rellenarTablaDoctores();
					
				} catch(SQLException r) {
					System.out.println(r.getMessage());
				}
				
			}
		});
		
		btnActListaDoctores.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnActListaDoctores.setBounds(728, 505, 109, 21);
		contentPane.add(btnActListaDoctores);
		
		JButton btnBorrarDoctor = new JButton("BORRAR");
		btnBorrarDoctor.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnBorrarDoctor.setBounds(633, 505, 85, 21);
		contentPane.add(btnBorrarDoctor);
		
		JButton btnCrearDoctor = new JButton("CREAR");
		btnCrearDoctor.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnCrearDoctor.setBounds(538, 505, 85, 21);
		contentPane.add(btnCrearDoctor);
		
		JButton btnActListaPacientes = new JButton("ACTUALIZAR");
		modeloPacientes.setColumnIdentifiers(columnasPacientes);
		
		btnActListaPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					rellenarListaDoctores();
					rellenarTablaDoctores();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		btnActListaPacientes.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnActListaPacientes.setBounds(318, 505, 112, 21);
		contentPane.add(btnActListaPacientes);
		
		JButton btnBorrarPaciente = new JButton("BORRAR");
		btnBorrarPaciente.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnBorrarPaciente.setBounds(223, 505, 85, 21);
		contentPane.add(btnBorrarPaciente);
		
		JButton btnCrearPaciente = new JButton("CREAR");
		btnCrearPaciente.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnCrearPaciente.setBounds(128, 505, 85, 21);
		contentPane.add(btnCrearPaciente);
		
		JButton btnConsultas = new JButton("CONSULTAS");
		btnConsultas.setBounds(341, 35, 112, 21);
		contentPane.add(btnConsultas);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setBounds(503, 34, 89, 23);
		contentPane.add(btnUsuarios);
		
		tblPacientes = new JTable();
		tblPacientes.setBounds(124, 225, 324, 276);
		contentPane.add(tblPacientes);
		
		
		tblDoctores = new JTable();
		tblDoctores.setBounds(531, 225, 324, 276);
		contentPane.add(tblDoctores);
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/dentiapp/ventana_administrador.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPane.add(lblFondo);
		
		rellenarListaDoctores();
		rellenarTablaDoctores();
		rellenarListaPacientes();
		rellenarTablaPacientes();
	}


	private void rellenarTablaDoctores() throws SQLException {
		modeloDoctores.setRowCount(0);
		rellenarListaDoctores();
		Object[] fila = new Object[modeloDoctores.getColumnCount()];
		for(int i = 0; i < listaDoctores.size(); i++) {
			fila[0] = listaDoctores.get(i).getIddoctor();
			fila[1] = listaDoctores.get(i).getNombre();
			fila[2] = listaDoctores.get(i).getFk_idespecialidad();
			modeloDoctores.addRow(fila);
		}
		
		tblDoctores.setModel(modeloDoctores);
	}
	
	private void rellenarListaDoctores() throws SQLException {
		cn.conectar();
		
		//Consulta a ejecutar
		String consulta = "SELECT * FROM doctor;";
		ResultSet rset = cn.ejecutarSelect(consulta);
		//Comprobamos si existen resultados, si no hay error y el tipo de rol de usuario
		listaDoctores = new ArrayList<Doctor>();
		while(rset.next()) {
			String nombre = rset.getString("nombre");
			int idDoctor = rset.getInt("iddoctor");
			int fk_idUsuario = rset.getInt("fk_idusuario");
			int fk_idEspecialidad = rset.getInt("fk_idespecialidad");

			@SuppressWarnings("unused")
			Doctor doctor1;
			listaDoctores.add(doctor1 = new Doctor(idDoctor, fk_idUsuario, fk_idEspecialidad, nombre));
			
		}
	}
	
	private void rellenarListaPacientes() throws SQLException {
		cn.conectar();
		
		//Consulta a ejecutar
		String consulta = "SELECT * FROM paciente;";
		ResultSet rset = cn.ejecutarSelect(consulta);
		//Comprobamos si existen resultados, si no hay error y el tipo de rol de usuario
		listaPacientes = new ArrayList<Paciente>();
		while(rset.next()) {
			String nombre = rset.getString("nombre");
			String dni = rset.getString("dni");
			int idPaciente = rset.getInt("id");

			@SuppressWarnings("unused")
			Paciente paciente1;
			listaPacientes.add(paciente1 = new Paciente(idPaciente, dni, nombre));
			
		}
	}
	
	private void rellenarTablaPacientes() throws SQLException {
		modeloDoctores.setRowCount(0);
		rellenarListaPacientes();
		Object[] fila = new Object[modeloDoctores.getColumnCount()];
		for(int i = 0; i < listaPacientes.size(); i++) {
			fila[0] = listaPacientes.get(i).getIdPaciente();
			fila[1] = listaPacientes.get(i).getNombre();
			fila[2] = listaPacientes.get(i).getDni();
			modeloPacientes.addRow(fila);
		}
		
		tblPacientes.setModel(modeloPacientes);
	}
}
