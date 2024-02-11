package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarEspecialidadDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private JTextField txtNombreBuscar;
	private ControladorSQL con = new ControladorSQL();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException{
				try {
					ModificarEspecialidadDialog dialog = new ModificarEspecialidadDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setUndecorated(true);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	public ModificarEspecialidadDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 765, 448);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(420, 161, 278, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JComboBox cmbNombre = new JComboBox();
		cmbNombre.setBounds(61, 160, 142, 22);
		contentPanel.add(cmbNombre);
		
		cmbNombre.setModel(rellenarDatos("especialidad", "nombre", modeloDatos));
		
		
		JLabel lblEspecialidad = new JLabel("New label");
		lblEspecialidad.setBounds(157, 123, 46, 14);
		contentPanel.add(lblEspecialidad);
		
		JLabel lblNombre = new JLabel("New label");
		lblNombre.setBounds(541, 123, 46, 14);
		contentPanel.add(lblNombre);
		
		txtNombreBuscar = new JTextField();
		txtNombreBuscar.setBounds(213, 161, 129, 20);
		contentPanel.add(txtNombreBuscar);
		txtNombreBuscar.setColumns(10);
		txtNombreBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbNombre.setModel(rellenarDatos("especialidad", "nombre", modeloDatos));
				String textoBusqueda = txtNombreBuscar.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbNombre.getItemCount(); i++) {
					if (cmbNombre.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbNombre.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbNombre.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbNombre.setPopupVisible(true);
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = "";
					String idespecialidad = con.selectWhere("especialidad", "idespecialidad", "nombre",
							 cmbNombre.getSelectedItem().toString());
					
					con.modificarEspecialidad(idespecialidad, nombre);
					JOptionPane.showMessageDialog(null, "Especialidad Modificada con Exito",
							"Especialidad Modificada", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
				}catch(SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error al modificar Paciente - Los datos introducidos no son correctos.\n Asegurese de que el nombre y el DNI es correcto",
							"Error al modificar paciente", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(664, 413, 89, 23);
		contentPanel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(565, 413, 89, 23);
		contentPanel.add(btnCancelar);
		
		JLabel lblFondo = new JLabel("New label");
		
		lblFondo.setIcon(new ImageIcon(CrearStockDialog.class.getResource("/fotos/modificar_especialidad.PNG")));
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
