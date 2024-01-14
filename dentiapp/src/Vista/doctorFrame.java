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

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class doctorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPacientes;
	private DefaultTableModel modeloPacientes = new DefaultTableModel();
	private DefaultTableModel modeloTratamientos = new DefaultTableModel();
	private ControladorSQL con = new ControladorSQL();
	private JTable tblTratamientos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorFrame frame = new doctorFrame();
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
	public doctorFrame() throws SQLException {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tblTratamientos = new JTable();
		tblTratamientos.setEnabled(false);
		tblTratamientos.setBounds(534, 170, 324, 276);
		contentPane.add(tblTratamientos);
		
		tblPacientes = new JTable();
		tblPacientes.setEnabled(false);
		tblPacientes.setBounds(126, 170, 324, 276);
		contentPane.add(tblPacientes);
		
		JButton btnPedido = new JButton("PEDIDO");
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ValidacionDialog pedido = new ValidacionDialog();
					pedido.setModal(true);
					pedido.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPedido.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnPedido.setBounds(341, 30, 118, 29);
		contentPane.add(btnPedido);	
		btnPedido.setBackground(new Color(55,4,102));
		btnPedido.setForeground(Color.WHITE);
		
		
		
		JButton btnPacientes = new JButton("PACIENTES");
		btnPacientes.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnPacientes.setBounds(191, 30, 118, 29);
		contentPane.add(btnPacientes);
		btnPacientes.setBackground(new Color(55,4,102));
		btnPacientes.setForeground(Color.WHITE);
		
		JButton btnOdontograma = new JButton("ODONTOGRAMA");
		btnOdontograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OdontogramaDialog odonto = new OdontogramaDialog();
				odonto.setModal(true);
				odonto.setVisible(true);
			}
		});
		btnOdontograma.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnOdontograma.setBounds(37, 30, 127, 29);
		contentPane.add(btnOdontograma);
		btnOdontograma.setBackground(new Color(55,4,102));
		btnOdontograma.setForeground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("LISTA PACIENTES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(128, 137, 322, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblListaTratamientos = new JLabel("LISTA TRATAMIENTOS");
		lblListaTratamientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaTratamientos.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblListaTratamientos.setBounds(534, 137, 322, 29);
		contentPane.add(lblListaTratamientos);
		
		JButton btnActualizarPaciente = new JButton("ACTUALIZAR");
		btnActualizarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tblPacientes.setModel(con.cargarDatos("paciente", modeloPacientes));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualizarPaciente.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnActualizarPaciente.setBounds(341, 456, 109, 23);
		contentPane.add(btnActualizarPaciente);
		btnActualizarPaciente.setBackground(new Color(55,4,102));
		btnActualizarPaciente.setForeground(Color.WHITE);
		
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
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setIcon(new ImageIcon(doctorFrame.class.getResource("/fotos/ventana_doctor.PNG")));
		lblImagenFondo.setBounds(0, 0, 956, 596);
		contentPane.add(lblImagenFondo);
		tblPacientes.setModel(con.cargarDatos("paciente", modeloPacientes));
		tblTratamientos.setModel(con.cargarDatos("tratamiento", modeloTratamientos));
		
		tblTratamientos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblTratamientos.getColumnModel().getColumn(1).setPreferredWidth(150);
	}
}
