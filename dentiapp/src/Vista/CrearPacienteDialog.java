package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ControladorSQL;

public class CrearPacienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private JTextField txtNombre;
	private JTextField txtDni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearPacienteDialog dialog = new CrearPacienteDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearPacienteDialog() {
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 764, 447);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String consulta = "INSERT INTO paciente VALUES (0,'" + txtNombre.getText().toString() + "','" + txtDni.getText().toString() + "')";
				try {
					con.ejecutarInsertar(consulta);
					con.crearOdontograma(txtDni.getText().toString());
					JOptionPane.showMessageDialog(null, "Paciente Creado con Exito",
							"Paciente Creado", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error al crear Paciente - Los datos introducidos no son correctos.\n Asegurese de que el DNI y Nombre son correctos",
							"Error al crear paciente", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
				
			}
		});
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCrear.setBackground(new Color(55, 4, 102));
		btnCrear.setActionCommand("OK");
		btnCrear.setBounds(515, 373, 96, 41);
		contentPanel.add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCancelar.setBackground(new Color(55, 4, 102));
		btnCancelar.setActionCommand("Cancel");
		btnCancelar.setBounds(621, 373, 97, 41);
		contentPanel.add(btnCancelar);

		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(432, 155, 279, 35);
		txtDni.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtDni.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtDni);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNombre.setBounds(58, 118, 81, 26);
		contentPanel.add(lblNombre);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDni.setBounds(432, 118, 81, 26);
		contentPanel.add(lblDni);

		txtNombre = new JTextField();
		txtNombre.setBounds(58, 155, 290, 35);
		txtNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombre.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CrearPacienteDialog.class.getResource("/fotos/crear_paciente.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
	}
}
