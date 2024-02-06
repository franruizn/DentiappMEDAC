package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSQL;
import paqGUI.BotonPersonalizadoBean;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class doctorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String initialize = ""; 
	private DefaultTableModel modeloConsulta = new DefaultTableModel();
	private ControladorSQL con = new ControladorSQL();
	private JTable tblConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorFrame frame = new doctorFrame(initialize);
					frame.setLocationRelativeTo(null);
					frame.setUndecorated(true);
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
	public doctorFrame(String nombreDoc) throws SQLException {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("BIENVENIDO");
		lblBienvenido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBienvenido.setBounds(508, 25, 420, 49);
		contentPane.add(lblBienvenido);
		
		lblBienvenido.setText(lblBienvenido.getText() + " " + nombreDoc);
		
		tblConsulta = new JTable();
		tblConsulta.setBounds(310, 162, 345, 298);
		contentPane.add(tblConsulta);
		
		JButton btnActualizarConsulta = new JButton("ACTUALIZAR");
		btnActualizarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tblConsulta.setModel(con.cargarDatos("consulta", modeloConsulta));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualizarConsulta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnActualizarConsulta.setBounds(434, 471, 109, 23);
		contentPane.add(btnActualizarConsulta);
		btnActualizarConsulta.setBackground(new Color(55,4,102));
		btnActualizarConsulta.setForeground(Color.WHITE);
		
		BotonPersonalizadoBean btnprsnlzdbnCerrar = new BotonPersonalizadoBean();
		btnprsnlzdbnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFrame login = new loginFrame();
				login.setVisible(true);
				dispose();
			}
		});
	BotonPersonalizadoBean btnprsnlzdbnCerrar_1 = new BotonPersonalizadoBean();
		btnprsnlzdbnCerrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnprsnlzdbnCerrar_1.setTexto("Salir");
		btnprsnlzdbnCerrar_1.setBounds(129, 75, 85, 34);
		contentPane.add(btnprsnlzdbnCerrar_1);
		btnprsnlzdbnCerrar.setTexto("Cerrar Sesion");
		btnprsnlzdbnCerrar.setBounds(34, 75, 85, 34);
		contentPane.add(btnprsnlzdbnCerrar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1092, 22);
		contentPane.add(menuBar);
		
		JMenu mnPacientes = new JMenu("Pacientes");
		menuBar.add(mnPacientes);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Odontograma");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OdontogramaDialog dialOdonto;
				dialOdonto = new OdontogramaDialog();
				dialOdonto.setModal(true);
				dialOdonto.setLocationRelativeTo(null);
				dialOdonto.setVisible(true);
			}
		});
		mnPacientes.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Buscar Paciente");
		mnPacientes.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_3 = new JMenu("Material");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Peticiones de material");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidacionDialog dialVali;
				try {
					dialVali = new ValidacionDialog();
					dialVali.setModal(true);
					dialVali.setLocationRelativeTo(null);
					dialVali.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("Stock");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Consultar Stock");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarStockDiag dialStock;
				try {
					dialStock = new ConsultarStockDiag();
					dialStock.setModal(true);
					dialStock.setLocationRelativeTo(null);
					dialStock.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Ayuda");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Archivo javahelp");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setIcon(new ImageIcon(doctorFrame.class.getResource("/fotos/plantilla1azul.png")));
		lblImagenFondo.setBounds(0, 0, 956, 596);
		contentPane.add(lblImagenFondo);

	}
}
