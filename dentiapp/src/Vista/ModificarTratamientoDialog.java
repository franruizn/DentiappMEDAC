package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ControladorSQL;

public class ModificarTratamientoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPrecio;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarTratamientoDialog dialog = new ModificarTratamientoDialog();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModificarTratamientoDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 764, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cmbMaterial = new JComboBox();
		cmbMaterial.setBounds(419, 259, 291, 47);
		contentPanel.add(cmbMaterial);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaterial.setBounds(419, 233, 80, 18);
		contentPanel.add(lblMaterial);
		
		JLabel lblDoctor = new JLabel("Tratamiento Seleccionado:");
		lblDoctor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDoctor.setBounds(58, 118, 172, 18);
		contentPanel.add(lblDoctor);
		
		JLabel lblEspecialidad = new JLabel("Precio");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEspecialidad.setBounds(58, 233, 80, 18);
		contentPanel.add(lblEspecialidad);
		
		JLabel lblModNom = new JLabel("Modificar Nombre");
		lblModNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModNom.setBounds(419, 118, 114, 18);
		contentPanel.add(lblModNom);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(58, 259, 294, 47);
		txtPrecio.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPrecio.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtPrecio);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(419, 147, 291, 47);
		txtNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombre.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtNombre);
		
		JComboBox cmbTratamiento = new JComboBox();
		cmbTratamiento.setBounds(58, 147, 291, 47);
		contentPanel.add(cmbTratamiento);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idtratamiento = con.selectWhere("tratamiento", "idtratamiento", "nombre",cmbTratamiento.getSelectedItem().toString());
					String nombre = txtNombre.getText();
					int precio = Integer.parseInt(txtPrecio.getText());
					String idmaterial = con.selectWhere("stock", "idstock", "nombre",cmbMaterial.getSelectedItem().toString());
					
					con.modificarTratamiento(idtratamiento, nombre, precio, idmaterial);
					JOptionPane.showMessageDialog(null, "Tratamiento Modificado con Exito",
							"Tratamiento Modificado", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error al modificar Tratamiento - Los datos introducidos no son correctos.\n Asegurese de que el nombre, precio y material es correcto",
							"Error al modificar tratamiento", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
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
		lblFondo.setIcon(new ImageIcon(ModificarTratamientoDialog.class.getResource("/fotos/modificar_tratamiento.png")));
		lblFondo.setBounds(0, 0, 764, 451);
		contentPanel.add(lblFondo);
		
		cmbMaterial.setModel(rellenarDatos("stock","nombre",modeloDatos));
		cmbTratamiento.setModel(rellenarDatos("tratamiento","nombre",modeloDatos));
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
