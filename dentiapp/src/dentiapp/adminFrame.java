package dentiapp;

import java.awt.Color;
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
		setBounds(100, 100, 963, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDoctores = new JLabel("LISTA DOCTORES");
		lblListaDoctores.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDoctores.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblListaDoctores.setBounds(600, 139, 195, 26);
		contentPane.add(lblListaDoctores);
		
		JLabel lblListaPacientes = new JLabel("LISTA PACIENTES");
		lblListaPacientes.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblListaPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaPacientes.setBounds(186, 139, 195, 26);
		contentPane.add(lblListaPacientes);
		
		JButton btnDoctores = new JButton("DOCTORES");
		btnDoctores.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnDoctores.setBounds(186, 30, 122, 34);
		contentPane.add(btnDoctores);
		btnDoctores.setBackground(new Color(55,4,102));
		btnDoctores.setForeground(Color.WHITE);
		
		JButton btnPacientes = new JButton("PACIENTES");
		btnPacientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnPacientes.setBounds(34, 30, 118, 34);
		contentPane.add(btnPacientes);
		btnPacientes.setBackground(new Color(55,4,102));
		btnPacientes.setForeground(Color.WHITE);
		
		JButton btnFacturacion = new JButton("FACTURACION");
		btnFacturacion.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnFacturacion.setBounds(793, 30, 122, 34);
		contentPane.add(btnFacturacion);
		btnFacturacion.setBackground(new Color(55,4,102));
		btnFacturacion.setForeground(Color.WHITE);
		
		JButton btnStock = new JButton("STOCK");
		btnStock.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnStock.setBounds(642, 30, 118, 34);
		contentPane.add(btnStock);
		btnStock.setBackground(new Color(55,4,102));
		btnStock.setForeground(Color.WHITE);
		
		JButton btnActListaDoctores = new JButton("ACTUALIZAR");
		btnActListaDoctores.setFont(new Font("SansSerif", Font.PLAIN, 12));
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
		btnActListaDoctores.setBounds(746, 457, 109, 21);
		contentPane.add(btnActListaDoctores);
		btnActListaDoctores.setBackground(new Color(55,4,102));
		btnActListaDoctores.setForeground(Color.WHITE);
		
		
		JButton btnBorrarDoctor = new JButton("BORRAR");
		btnBorrarDoctor.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnBorrarDoctor.setBounds(642, 457, 85, 21);
		contentPane.add(btnBorrarDoctor);
		btnBorrarDoctor.setBackground(new Color(55,4,102));
		btnBorrarDoctor.setForeground(Color.WHITE);
		
		JButton btnCrearDoctor = new JButton("CREAR");
		btnCrearDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearDoctorDialog dialDoctor;
				try {
					dialDoctor = new CrearDoctorDialog();
					dialDoctor.setModal(true);
					dialDoctor.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCrearDoctor.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnCrearDoctor.setBounds(531, 457, 85, 21);
		contentPane.add(btnCrearDoctor);
		btnCrearDoctor.setBackground(new Color(55,4,102));
		btnCrearDoctor.setForeground(Color.WHITE); 
		
		JButton btnActListaPacientes = new JButton("ACTUALIZAR");
		btnActListaPacientes.setFont(new Font("SansSerif", Font.PLAIN, 10));
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
		btnActListaPacientes.setBounds(335, 457, 112, 21);
		contentPane.add(btnActListaPacientes);
		btnActListaPacientes.setBackground(new Color(55,4,102));
		btnActListaPacientes.setForeground(Color.WHITE);
		
		JButton btnBorrarPaciente = new JButton("BORRAR");
		btnBorrarPaciente.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnBorrarPaciente.setBounds(228, 457, 85, 21);
		contentPane.add(btnBorrarPaciente);
		btnBorrarPaciente.setBackground(new Color(55,4,102));
		btnBorrarPaciente.setForeground(Color.WHITE);
		
		JButton btnCrearPaciente = new JButton("CREAR");
		btnCrearPaciente.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnCrearPaciente.setBounds(123, 457, 85, 21);
		contentPane.add(btnCrearPaciente);
		btnCrearPaciente.setBackground(new Color(55,4,102));
		btnCrearPaciente.setForeground(Color.WHITE);
		
		JButton btnConsultas = new JButton("CONSULTAS");
		btnConsultas.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnConsultas.setBounds(336, 30, 124, 34);
		contentPane.add(btnConsultas);
		btnConsultas.setBackground(new Color(55,4,102));
		btnConsultas.setForeground(Color.WHITE);
		
		JButton btnUsuarios = new JButton("USUARIOS");
		btnUsuarios.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnUsuarios.setBounds(489, 30, 122, 34);
		contentPane.add(btnUsuarios);
		btnUsuarios.setBackground(new Color(55,4,102));
		btnUsuarios.setForeground(Color.WHITE);
		
		tblPacientes = new JTable();
		tblPacientes.setBounds(123, 170, 324, 276);
		contentPane.add(tblPacientes);
		
		
		tblDoctores = new JTable();
		tblDoctores.setBounds(531, 170, 324, 276);
		contentPane.add(tblDoctores);
		
		rellenarListaDoctores();
		rellenarTablaDoctores();
		rellenarListaPacientes();
		rellenarTablaPacientes();
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/dentiapp/ventana_admin.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPane.add(lblFondo);
		
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
		
		cn.desconectar();
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
		
		cn.desconectar();
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
