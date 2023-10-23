package dentiapp;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class doctorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorFrame frame = new doctorFrame();
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
	public doctorFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPedido = new JButton("PEDIDO");
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnEliminarTratamiento = new JButton("Eliminar");
		btnEliminarTratamiento.setBounds(767, 456, 89, 23);
		contentPane.add(btnEliminarTratamiento);
		
		textField = new JTextField();
		textField.setBounds(128, 170, 322, 275);
		contentPane.add(textField);
		textField.setColumns(10);
		btnPedido.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnPedido.setBounds(341, 30, 118, 29);
		contentPane.add(btnPedido);
		btnPedido.setBackground(new Color(55,4,102));
		btnPedido.setForeground(Color.WHITE);
		
		JButton btnPacientes = new JButton("PACIENTES");
		btnPacientes.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnPacientes.setBounds(189, 30, 118, 29);
		contentPane.add(btnPacientes);
		btnPacientes.setBackground(new Color(55,4,102));
		btnPacientes.setForeground(Color.WHITE);
		
		JButton btnTratamiento = new JButton("TRATAMIENTO");
		btnTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnTratamiento.setBounds(37, 30, 118, 29);
		contentPane.add(btnTratamiento);
		btnTratamiento.setBackground(new Color(55,4,102));
		btnTratamiento.setForeground(Color.WHITE);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(534, 170, 322, 275);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("LISTA PACIENTES");
		lblNewLabel.setBounds(128, 137, 322, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblListaTratamientos = new JLabel("LISTA TRATAMIENTOS");
		lblListaTratamientos.setBounds(534, 137, 322, 29);
		contentPane.add(lblListaTratamientos);
		
		JButton btnActualizarPaciente = new JButton("Actualizar");
		btnActualizarPaciente.setBounds(361, 456, 89, 23);
		contentPane.add(btnActualizarPaciente);
		
		JButton btnAñadirTratamiento = new JButton("Añadir");
		btnAñadirTratamiento.setBounds(544, 456, 89, 23);
		contentPane.add(btnAñadirTratamiento);
		
		JButton btnModificarTratamiento = new JButton("Modificar");
		btnModificarTratamiento.setBounds(655, 456, 89, 23);
		contentPane.add(btnModificarTratamiento);
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setIcon(new ImageIcon(doctorFrame.class.getResource("/dentiapp/ventana_doctor.PNG")));
		lblImagenFondo.setBounds(0, 0, 956, 596);
		contentPane.add(lblImagenFondo);
	}
}
