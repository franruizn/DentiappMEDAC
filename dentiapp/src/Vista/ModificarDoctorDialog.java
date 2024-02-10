package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ConexionMySQL;
import Controlador.ControladorSQL;
import Modelo.Especialidad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import paqGUI.BotonPersonalizadoBean;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class ModificarDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDoctor;
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarDoctorDialog dialog = new ModificarDoctorDialog();
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
	public ModificarDoctorDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 764, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cmbDr = new JComboBox();
		cmbDr.setBounds(58, 274, 55, 50);
		contentPanel.add(cmbDr);
		String[] opciones = {"Dr.", "Dra."};
		cmbDr.setModel(new DefaultComboBoxModel<>(opciones));
		
		
		JComboBox cmbEspecialidad = new JComboBox();
		cmbEspecialidad.setBounds(419, 274, 291, 47);
		contentPanel.add(cmbEspecialidad);
		
		cmbEspecialidad.setModel(rellenarDatos("especialidad","nombre",modeloDatos));
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(119, 277, 225, 47);
		txtNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombre.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtNombre);
		
		JComboBox cmbDoctor = new JComboBox();
		cmbDoctor.setBounds(58, 147, 291, 47);
		contentPanel.add(cmbDoctor);
		
		txtDoctor = new JTextField();
		txtDoctor.setBounds(422, 147, 291, 46);
		txtDoctor.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtDoctor.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtDoctor);
		txtDoctor.setColumns(10);
		
		cmbDoctor.setModel(rellenarDatos("doctor", "nombre", modeloDatos));
		txtDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbDoctor.setModel(rellenarDatos("doctor", "nombre", modeloDatos));
				String textoBusqueda = txtDoctor.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbDoctor.getItemCount(); i++) {
					if (cmbDoctor.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbDoctor.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbDoctor.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbDoctor.setPopupVisible(true);
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = cmbDr.getSelectedItem().toString() + " " + txtNombre.getText();
					String especialidad = cmbEspecialidad.getSelectedItem().toString();
					String idespecialidad = con.selectWhere("especialidad", "idespecialidad", "nombre", especialidad);
					String iddoctor = con.selectWhere("doctor", "iddoctor", "nombre", cmbDoctor.getSelectedItem().toString());
					
					con.modificarDoctor(iddoctor, idespecialidad, nombre);
					JOptionPane.showMessageDialog(null, "Doctor Modificado con Exito",
							"Doctor Modificado", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error al modificar Doctor - Los datos introducidos no son correctos.\n Asegurese de que el nombre es correcto",
							"Error al modificar doctor", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(604, 404, 109, 36);
		contentPanel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(485, 404, 109, 36);
		contentPanel.add(btnCancelar);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarDoctorDialog.class.getResource("/fotos/mod_doctor.png")));
		lblFondo.setBounds(0, 0, 764, 451);
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
