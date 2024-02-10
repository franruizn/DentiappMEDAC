package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import Controlador.ControladorSQL;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModificarPacienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreBuscar;
	private ControladorSQL con = new ControladorSQL();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private JTextField txtNombre;
	private JTextField txtDni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarPacienteDialog dialog = new ModificarPacienteDialog();
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
	public ModificarPacienteDialog() {
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 758, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(490, 398, 109, 36);
		contentPanel.add(btnCancelar);

		JLabel lblDni = new JLabel("Modificar DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(419, 207, 96, 18);
		contentPanel.add(lblDni);

		JLabel lblModNom = new JLabel("Modificar Nombre");
		lblModNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModNom.setBounds(59, 210, 114, 18);
		contentPanel.add(lblModNom);

		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtDni.setBackground(new Color(246, 246, 246));
		txtDni.setBounds(418, 236, 291, 46);
		contentPanel.add(txtDni);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombre.setBackground(new Color(246, 246, 246));
		txtNombre.setBounds(59, 239, 291, 46);
		contentPanel.add(txtNombre);

		JLabel lblNombre = new JLabel("Buscar Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(419, 117, 96, 18);
		contentPanel.add(lblNombre);

		JLabel lblPaciente = new JLabel("Paciente Seleccionado:");
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaciente.setBounds(59, 117, 156, 18);
		contentPanel.add(lblPaciente);

		txtNombreBuscar = new JTextField();
		txtNombreBuscar.setColumns(10);
		txtNombreBuscar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombreBuscar.setBackground(new Color(246, 246, 246));
		txtNombreBuscar.setBounds(419, 146, 291, 46);
		contentPanel.add(txtNombreBuscar);

		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(59, 146, 286, 48);
		contentPanel.add(cmbPaciente);

		cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
		txtNombreBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
				String textoBusqueda = txtNombreBuscar.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbPaciente.getItemCount(); i++) {
					if (cmbPaciente.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbPaciente.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbPaciente.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbPaciente.setPopupVisible(true);
			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(609, 398, 109, 36);
		contentPanel.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idpaciente;
				try {
					String nombre = "", dni = "";
					idpaciente = con.selectWhere("paciente", "idpaciente", "dni",
							con.selectWhere("paciente", "dni", "nombre", cmbPaciente.getSelectedItem().toString()));
					
					con.modificarPaciente(idpaciente, nombre, dni);
					JOptionPane.showMessageDialog(null, "Paciente Modificado con Exito",
							"Paciente Modificado", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error al modificar Paciente - Los datos introducidos no son correctos.\n Asegurese de que el nombre y el DNI es correcto",
							"Error al modificar paciente", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
			}
		});

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Aquí se ejecuta el código cuando se selecciona un item
				try {
					JComboBox comboBox = (JComboBox) e.getSource();
					String itemSeleccionado = (String) comboBox.getSelectedItem().toString();

					txtNombre.setText(itemSeleccionado);
					txtDni.setText(con.selectWhere("paciente", "dni", "nombre", itemSeleccionado));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		// Añadir el ActionListener al JComboBox
		cmbPaciente.addActionListener(listener);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarPacienteDialog.class.getResource("/fotos/mod_paciente.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
	}

	public DefaultComboBoxModel rellenarDatos(String nombreTabla, String campo,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBox(nombreTabla, campo);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}
}
