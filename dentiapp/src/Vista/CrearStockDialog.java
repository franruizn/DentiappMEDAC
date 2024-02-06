package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearStockDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCantidad;
	private JTextField txtProveedor;
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearStockDialog dialog = new CrearStockDialog();
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
	public CrearStockDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 763, 447);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnAdd = new JButton("Añadir Proveedor");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * CREAR PROVEEDOR DIALOG
				 */
			}
		});
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(485, 390, 107, 23);
		contentPanel.add(btnCancelar);
		
		JComboBox cmbProveedor = new JComboBox();
		cmbProveedor.setBounds(417, 151, 148, 40);
		contentPanel.add(cmbProveedor);
		
		JButton btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idProveedor = con.selectWhere("proveedor", "idproveedor", "nombre", cmbProveedor.getSelectedItem().toString());
					String values = "0,"+idProveedor+",'"+txtNombre.getText()+"',"+txtCantidad.getText();
					con.insertarConsulta("stock", "idstock,fk_idproveedor,nombre,cantidad",values);
					
					JOptionPane.showMessageDialog(null, "Material Añadido con Exito",
							"Material Añadido", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error al añadir Material - Los datos introducidos no son correctos.\n Asegurese de que el Nombre, Cantidad y Proveedor son correctos",
							"Error al añadir material", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
				
			}
		});
		btnCrear.setBounds(602, 390, 107, 23);
		contentPanel.add(btnCrear);
		btnAdd.setBounds(563, 204, 148, 23);
		contentPanel.add(btnAdd);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProveedor.setBounds(417, 122, 332, 18);
		contentPanel.add(lblProveedor);
		
		

		txtProveedor = new JTextField();
		txtProveedor.setColumns(10);
		txtProveedor.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtProveedor.setBackground(new Color(246, 246, 246));
		txtProveedor.setBounds(572, 151, 139, 41);
		contentPanel.add(txtProveedor);
		
		txtProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbProveedor.setModel(rellenarDatos("proveedor", "nombre", modeloDatos));
				String textoBusqueda = txtProveedor.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbProveedor.getItemCount(); i++) {
					if (cmbProveedor.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbProveedor.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbProveedor.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbProveedor.setPopupVisible(true);
			}
		});
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtCantidad.setBackground(new Color(246, 246, 246));
		txtCantidad.setBounds(48, 278, 309, 41);
		contentPanel.add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad Material");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad.setBounds(48, 254, 332, 18);
		contentPanel.add(lblCantidad);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(48, 150, 309, 41);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombre.setBackground(new Color(246, 246, 246));
		
		JLabel lblNombre = new JLabel("Nombre Material");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(48, 121, 332, 18);
		contentPanel.add(lblNombre);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CrearStockDialog.class.getResource("/fotos/crear_stock.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
		
		cmbProveedor.setModel(rellenarDatos("proveedor", "nombre", modeloDatos));
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
