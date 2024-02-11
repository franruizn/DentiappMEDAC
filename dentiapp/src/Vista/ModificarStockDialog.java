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

public class ModificarStockDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private JTextField txtCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarStockDialog dialog = new ModificarStockDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModificarStockDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 760, 449);
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
		btnCancelar.setBounds(481, 398, 109, 36);
		contentPanel.add(btnCancelar);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtCantidad.setBackground(new Color(246, 246, 246));
		txtCantidad.setBounds(57, 274, 294, 47);
		contentPanel.add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidad.setBounds(60, 250, 80, 18);
		contentPanel.add(lblCantidad);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProveedor.setBounds(416, 125, 80, 18);
		contentPanel.add(lblProveedor);
		
		JComboBox cmbProveedor = new JComboBox();
		cmbProveedor.setBounds(416, 146, 291, 47);
		contentPanel.add(cmbProveedor);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaterial.setBounds(60, 121, 80, 18);
		contentPanel.add(lblMaterial);
		
		JComboBox cmbMaterial = new JComboBox();
		cmbMaterial.setBounds(60, 146, 291, 47);
		contentPanel.add(cmbMaterial);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cantidad = txtCantidad.getText();
					String idproveedor = con.selectWhere("proveedor", "idproveedor", "nombre",cmbProveedor.getSelectedItem().toString());
					String idstock = con.selectWhere("stock", "idstock", "nombre",cmbMaterial.getSelectedItem().toString());
					con.modificarStock(idstock, idproveedor, cantidad);
					JOptionPane.showMessageDialog(null, "Stock Modificado con Exito",
							"Stock Modificado", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error al modificar Stock - Los datos introducidos no son correctos.\n Asegurese de que el proveedor y cantidad son correctos",
							"Error al modificar stock", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(610, 398, 109, 36);
		contentPanel.add(btnAceptar);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarStockDialog.class.getResource("/fotos/mod_stock.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
		
		cmbMaterial.setModel(rellenarDatos("stock","nombre",modeloDatos));
		cmbProveedor.setModel(rellenarDatos("proveedor","nombre",modeloDatos));
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
