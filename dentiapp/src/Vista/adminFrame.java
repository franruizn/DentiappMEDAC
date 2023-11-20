package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class adminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPacientes;
	private JTable tblDoctores;
	private ControladorSQL cn = new ControladorSQL();
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
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
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
		setBounds(100, 100, 963, 651);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPaciente = new JMenu("Pacientes");
		menuBar.add(mnPaciente);
		
		JMenu mnGestionarPacientes = new JMenu("Gestionar Pacientes");
		mnPaciente.add(mnGestionarPacientes);
		
		JMenuItem mntmCrearPaciente = new JMenuItem("Crear Paciente");
		mntmCrearPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				CrearPacienteDialog dialPaciente1;
				dialPaciente1 = new CrearPacienteDialog();
				dialPaciente1.setModal(true);
				dialPaciente1.setVisible(true);
			}
		});
		mnGestionarPacientes.add(mntmCrearPaciente);
		
		JMenuItem mntmBorrarPaciente = new JMenuItem("Borrar Paciente");
		mntmBorrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarPacienteDialog dialPaciente2;
				dialPaciente2 = new BorrarPacienteDialog();
				dialPaciente2.setModal(true);
				dialPaciente2.setVisible(true);
			}
		});
		mnGestionarPacientes.add(mntmBorrarPaciente);
		
		JMenuItem mntmModificarPaciente = new JMenuItem("Modificar Paciente");
		mntmModificarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarPacienteDialog dialPaciente3;
				dialPaciente3 = new ModificarPacienteDialog();
				dialPaciente3.setModal(true);
				dialPaciente3.setVisible(true);
			}
		});
		mnGestionarPacientes.add(mntmModificarPaciente);
		
		JMenu mnDoctores = new JMenu("Doctores");
		menuBar.add(mnDoctores);
		
		JMenu mnGestionarDoctores = new JMenu("Gestionar Doctores");
		mnDoctores.add(mnGestionarDoctores);
		
		JMenuItem mntmCrearDoctor = new JMenuItem("Crear Doctor");
		mntmCrearDoctor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		mntmCrearDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearDoctorDialog dialDoctor1;
				try {
					dialDoctor1 = new CrearDoctorDialog();
					dialDoctor1.setModal(true);
					dialDoctor1.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnGestionarDoctores.add(mntmCrearDoctor);
		
		JMenuItem mntmBorrarDoctor = new JMenuItem("Borrar Doctor");
		mntmBorrarDoctor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		mntmBorrarDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarDoctorDialog dialDoctor2;
				try {
					dialDoctor2 = new BorrarDoctorDialog();
					dialDoctor2.setModal(true);
					dialDoctor2.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnGestionarDoctores.add(mntmBorrarDoctor);
		
		JMenuItem mntmModificarDoctor = new JMenuItem("Modificar Doctor");
		mntmModificarDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDoctorDialog dialDoctor3;
				dialDoctor3 = new ModificarDoctorDialog();
				dialDoctor3.setModal(true);
				dialDoctor3.setVisible(true);
			}
		});
		mnGestionarDoctores.add(mntmModificarDoctor);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenu mnGestionarConsultas = new JMenu("Gestionar Consultas");
		mnConsultas.add(mnGestionarConsultas);
		
		JMenuItem mntmCrearConsulta = new JMenuItem("Crear Consulta");
		mntmCrearConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mntmCrearConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearConsultaDialog dialConsulta1;
				dialConsulta1 = new CrearConsultaDialog();
				dialConsulta1.setModal(true);
				dialConsulta1.setVisible(true);
			}
		});
		mnGestionarConsultas.add(mntmCrearConsulta);
		
		JMenuItem mntmBorrarConsulta = new JMenuItem("Borrar Consulta");
		mntmBorrarConsulta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		mntmBorrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarConsultaDialog dialConsulta2;
				try {
					dialConsulta2 = new BorrarConsultaDialog();
					dialConsulta2.setModal(true);
					dialConsulta2.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnGestionarConsultas.add(mntmBorrarConsulta);
		
		JMenuItem mntmModificarConsulta = new JMenuItem("Modificar Consulta");
		mntmModificarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarConsultaDialog dialConsulta3;
				dialConsulta3 = new ModificarConsultaDialog();
				dialConsulta3.setModal(true);
				dialConsulta3.setVisible(true);
			}
		});
		mnGestionarConsultas.add(mntmModificarConsulta);
		
		JMenu mnUsuarios = new JMenu("Usuarios\r\n");
		menuBar.add(mnUsuarios);
		
		JMenu mnGestionarUsuarios = new JMenu("Gestionar Usuarios");
		mnUsuarios.add(mnGestionarUsuarios);
		
		JMenuItem mntmCrearUsuario = new JMenuItem("Crear Usuario");
		mntmCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuarioDialog dialUsuario1;
				dialUsuario1 = new CrearUsuarioDialog();
				dialUsuario1.setModal(true);
				dialUsuario1.setVisible(true);
			}
		});
		mnGestionarUsuarios.add(mntmCrearUsuario);
		
		JMenuItem mntmBorrarUsuario = new JMenuItem("Borrar Usuario");
		mntmBorrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarUsuarioDialog dialUsuario2;
				dialUsuario2 = new BorrarUsuarioDialog();
				dialUsuario2.setModal(true);
				dialUsuario2.setVisible(true);
			}
		});
		mnGestionarUsuarios.add(mntmBorrarUsuario);
		
		JMenuItem mntmModificarUsuario = new JMenuItem("Modificar Usuario");
		mntmModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuarioDialog dialUsuario3;
				dialUsuario3 = new ModificarUsuarioDialog();
				dialUsuario3.setModal(true);
				dialUsuario3.setVisible(true);
			}
		});
		mnGestionarUsuarios.add(mntmModificarUsuario);
		
		JMenu mnStock = new JMenu("Stock");
		menuBar.add(mnStock);
		
		JMenu mnGestionarStock = new JMenu("Gestionar Stock");
		mnStock.add(mnGestionarStock);
		
		JMenuItem mntmCrearStock = new JMenuItem("Crear Stock");
		mntmCrearStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearStockDialog dialStock1;
				dialStock1 = new CrearStockDialog();
				dialStock1.setModal(true);
				dialStock1.setVisible(true);
			}
		});
		mnGestionarStock.add(mntmCrearStock);
		
		JMenuItem mntmBorrarStock = new JMenuItem("Borrar Stock");
		mntmBorrarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarStockDialog dialStock2;
				dialStock2 = new BorrarStockDialog();
				dialStock2.setModal(true);
				dialStock2.setVisible(true);
			}
		});
		mnGestionarStock.add(mntmBorrarStock);
		
		JMenuItem mntmModificarStock = new JMenuItem("Modificar Stock");
		mntmModificarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarStockDialog dialStock3;
				dialStock3 = new ModificarStockDialog();
				dialStock3.setModal(true);
				dialStock3.setVisible(true);
			}
		});
		mnGestionarStock.add(mntmModificarStock);
		
		JMenu mnFacturacion = new JMenu("Facturacion");
		menuBar.add(mnFacturacion);
		
		JMenu mnGestionarFacturacion = new JMenu("Gestionar Facturación");
		mnFacturacion.add(mnGestionarFacturacion);
		
		JMenuItem mntmCrearFacturacion = new JMenuItem("Crear Facturación");
		mntmCrearFacturacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CrearFacturacionDialog dialFactura1;
				dialFactura1 = new CrearFacturacionDialog();
				dialFactura1.setModal(true);
				dialFactura1.setVisible(true);
			}
		});
		mnGestionarFacturacion.add(mntmCrearFacturacion);
		
		JMenuItem mntmBorrarFacturacion = new JMenuItem("Borrar Facturación");
		mntmBorrarFacturacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarFacturacionDialog dialFactura2;
				dialFactura2 = new BorrarFacturacionDialog();
				dialFactura2.setModal(true);
				dialFactura2.setVisible(true);
			}
		});
		mnGestionarFacturacion.add(mntmBorrarFacturacion);
		
		JMenuItem mntmModificarFacturacion = new JMenuItem("Modificar Facturacion");
		mntmModificarFacturacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarFacturacionDialog dialFactura3;
				dialFactura3 = new ModificarFacturacionDialog();
				dialFactura3.setModal(true);
				dialFactura3.setVisible(true);
			}
		});
		mnGestionarFacturacion.add(mntmModificarFacturacion);
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
					
					tblDoctores.setModel(cn.cargarDatos("doctor", modeloDoctores));
					
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
		    
		btnBorrarDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarDoctorDialog dialDoctor;
				try {
					
					dialDoctor = new BorrarDoctorDialog();
					dialDoctor.setModal(true);
					dialDoctor.setUndecorated(true);
					dialDoctor.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
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
					dialDoctor.setUndecorated(true);
					dialDoctor.setVisible(true);
					
					dialDoctor.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							try {
								tblPacientes.setModel(cn.cargarDatos("paciente", modeloPacientes));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("fdlksjfslkdfjsdlkñ");
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
					tblPacientes.setModel(cn.cargarDatos("paciente", modeloPacientes));
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
		btnCrearPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		tblPacientes.setEnabled(false);
		contentPane.add(tblPacientes);
		
		
		tblDoctores = new JTable();
		tblDoctores.setBounds(531, 170, 324, 276);
		tblDoctores.setEnabled(false);
		contentPane.add(tblDoctores);
		
		tblDoctores.setModel(cn.cargarDatos("doctor", modeloDoctores));
		tblPacientes.setModel(cn.cargarDatos("paciente", modeloPacientes));
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/ventana_admin.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPane.add(lblFondo);
		
	}
}
