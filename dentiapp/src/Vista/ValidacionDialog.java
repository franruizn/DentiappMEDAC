package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ValidacionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblSolRecibidas;
	private JTextField txtCantidadMat;
	private DefaultComboBoxModel modeloMateriales = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloProv = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ValidacionDialog dialog = new ValidacionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ValidacionDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 953, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tblSolRecibidas = new JTable();
		tblSolRecibidas.setBounds(137, 182, 298, 256);
		contentPanel.add(tblSolRecibidas);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(186, 448, 85, 21);
		contentPanel.add(btnAceptar);
		
		JButton btnDenegar = new JButton("Denegar");
		btnDenegar.setBounds(304, 448, 85, 21);
		contentPanel.add(btnDenegar);
		
		JLabel lblSolicitudes = new JLabel("Solicitud de Stock");
		lblSolicitudes.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblSolicitudes.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitudes.setBounds(186, 135, 205, 28);
		contentPanel.add(lblSolicitudes);
		
		JLabel lblNombreMat = new JLabel("Nombre del Material: ");
		lblNombreMat.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNombreMat.setBounds(535, 224, 143, 13);
		contentPanel.add(lblNombreMat);
		
		JLabel lblCantidadMat = new JLabel("Cantidad de Material: ");
		lblCantidadMat.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblCantidadMat.setBounds(535, 269, 154, 13);
		contentPanel.add(lblCantidadMat);
		
		JLabel lblProveedor = new JLabel("Solicitar a Proveedor: ");
		lblProveedor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblProveedor.setBounds(535, 310, 154, 13);
		contentPanel.add(lblProveedor);
		
		JLabel lblDatosSol = new JLabel("Datos a Solicitar");
		lblDatosSol.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblDatosSol.setBounds(599, 133, 222, 33);
		contentPanel.add(lblDatosSol);
		
		txtCantidadMat = new JTextField();
		txtCantidadMat.setColumns(10);
		txtCantidadMat.setBounds(682, 268, 154, 19);
		contentPanel.add(txtCantidadMat);
		
		JComboBox cmbProveedores = new JComboBox();
		cmbProveedores.setBounds(682, 308, 154, 21);
		contentPanel.add(cmbProveedores);
		
		JButton btnEnvDatos = new JButton("Enviar Solicitud");
		btnEnvDatos.setBounds(634, 417, 113, 21);
		contentPanel.add(btnEnvDatos);
		
		JLabel lblValidacion = new JLabel("VALIDACIÓN DE SOLICITUDES");
		lblValidacion.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblValidacion.setBounds(254, 35, 444, 55);
		contentPanel.add(lblValidacion);
		
		JComboBox cmbNombreMat = new JComboBox();
		cmbNombreMat.setBounds(682, 222, 154, 21);
		contentPanel.add(cmbNombreMat);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/ventana_admin.PNG")));
			lblFondo.setBounds(0, 0, 954, 594);
			contentPanel.add(lblFondo);
		}
		
		cmbNombreMat.setModel(rellenarDatos("stock","nombre",modeloMateriales));
		
		cmbNombreMat.addItemListener((ItemListener) new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					try {
						cmbProveedores.setModel(con.obtenerProveedor(cmbNombreMat.getSelectedItem().toString()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
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
	
	/*public DefaultComboBoxModel rellenarDatosWhere(String nombreTabla, String campo,
			DefaultComboBoxModel<String> comboDatos, String nombreMat) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBoxWhere(nombreTabla, campo, con.obtenerProveedor(nombreMat),"nombre");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}*/
}


