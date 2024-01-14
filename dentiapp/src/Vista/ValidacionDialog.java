package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ValidacionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblSolRecibidas;
	private JTextField txtCantidadMat;

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
		txtCantidadMat.setBounds(682, 268, 96, 19);
		contentPanel.add(txtCantidadMat);
		
		JComboBox cmbxProveedores = new JComboBox();
		cmbxProveedores.setBounds(682, 308, 96, 21);
		contentPanel.add(cmbxProveedores);
		
		JButton btnEnvDatos = new JButton("Enviar Solicitud");
		btnEnvDatos.setBounds(634, 417, 103, 21);
		contentPanel.add(btnEnvDatos);
		
		JLabel lblValidacion = new JLabel("VALIDACIÓN DE SOLICITUDES");
		lblValidacion.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblValidacion.setBounds(254, 35, 444, 55);
		contentPanel.add(lblValidacion);
		
		JComboBox cmbxNombreMat = new JComboBox();
		cmbxNombreMat.setBounds(682, 222, 96, 21);
		contentPanel.add(cmbxNombreMat);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/ventana_admin.PNG")));
			lblFondo.setBounds(0, 0, 954, 594);
			contentPanel.add(lblFondo);
		}
	}
}
