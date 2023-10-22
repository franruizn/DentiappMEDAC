package dentiapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class adminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtListaPacientes;
	private JTextField txtListaDoctores;

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
	 */
	public adminFrame() {
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
		lblListaPacientes.setBounds(175, 191, 195, 26);
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
		
		txtListaDoctores = new JTextField();
		txtListaDoctores.setColumns(10);
		txtListaDoctores.setBounds(552, 234, 285, 248);
		contentPane.add(txtListaDoctores);
		
		txtListaPacientes = new JTextField();
		txtListaPacientes.setBounds(142, 234, 285, 248);
		contentPane.add(txtListaPacientes);
		txtListaPacientes.setColumns(10);
		
		JButton btnActListaDoctores = new JButton("ACTUALIZAR");
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
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/dentiapp/ventana_administrador.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPane.add(lblFondo);
	}
}
