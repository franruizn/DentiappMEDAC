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

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import Controlador.ControladorSQL;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarFacturacionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtPagado;
	private JTextField txtPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarFacturacionDialog dialog = new ModificarFacturacionDialog();
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
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModificarFacturacionDialog() throws SQLException {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 763, 453);
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
		
		JComboBox cmbFactura = new JComboBox();
		cmbFactura.setBounds(422, 260, 291, 41);
		contentPanel.add(cmbFactura);
		btnCancelar.setBounds(488, 406, 109, 36);
		contentPanel.add(btnCancelar);
		
		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(58, 152, 290, 47);
		contentPanel.add(cmbPaciente);
		
		txtPaciente = new JTextField();
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(59, 114, 291, 28);
		txtPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbPaciente.setModel(rellenarDatosDoble("paciente", "nombre", "dni", modeloDatos));
				String textoBusqueda = txtPaciente.getText().toLowerCase();

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
		contentPanel.add(txtPaciente);
		
		JComboBox cmbTratamiento = new JComboBox();
		cmbTratamiento.setBounds(422, 152, 291, 47);
		contentPanel.add(cmbTratamiento);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEuro.setBounds(328, 263, 49, 40);
		contentPanel.add(lblEuro);
		
		txtPagado = new JTextField();
		txtPagado.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtPagado.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPagado.setColumns(10);
		txtPagado.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPagado.setBackground(new Color(246, 246, 246));
		txtPagado.setBounds(58, 256, 260, 47);
		contentPanel.add(txtPagado);
		
		JLabel lblCantidadPagada = new JLabel("Cantidad Pagada");
		lblCantidadPagada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantidadPagada.setBounds(53, 228, 172, 18);
		contentPanel.add(lblCantidadPagada);
		
		JLabel lblTratamiento = new JLabel("Tratamiento");
		lblTratamiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTratamiento.setBounds(422, 127, 172, 18);
		contentPanel.add(lblTratamiento);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaciente.setBounds(57, 90, 172, 18);
		contentPanel.add(lblPaciente);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				try {
					Date fecha = new Date();
					String fechaFormato = sdf.format(fecha);
					String[] dni = cmbPaciente.getSelectedItem().toString().split("-");
					String[] idfactura = cmbFactura.getSelectedItem().toString().split((" - "));
					String idpaciente = con.selectWhere("paciente", "idpaciente", "dni", dni[0]);
					int pagar = Integer.parseInt(con.selectWhere("facturacion", "pagar","idfacturacion",idfactura[0]));
					if(txtPagado.getText().equals("")) {
						pagar += 0;
					} else {
						pagar += Integer.parseInt(txtPagado.getText());
					}
					int total = Integer.parseInt(con.selectWhere("tratamiento", "precio", "nombre", cmbTratamiento.getSelectedItem().toString()));
					int pagado = 0;
					if(pagar == total) {
						pagado = 1;
					}
					
					con.modificarFactura(idfactura[0], pagado, pagar);
					JOptionPane.showMessageDialog(null, "Factura Modificada con Exito",
							"Factura Modificada", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error al modificar la factura\nComprueba que los datos son correctos",
							"Error", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
				
			}
		});
		btnAceptar.setBounds(607, 406, 109, 36);
		contentPanel.add(btnAceptar);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarFacturacionDialog.class.getResource("/fotos/mod_factura1.PNG")));
		lblFondo.setBounds(0, 0, 763, 457);
		contentPanel.add(lblFondo);
		
		cmbPaciente.setModel(rellenarDatosDoble("paciente", "nombre", "dni", modeloDatos));
		cmbTratamiento.setModel(rellenarDatos("tratamiento","nombre",modeloDatos));
		
		cmbPaciente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] dni = cmbPaciente.getSelectedItem().toString().split("-");
				try {
					String idpaciente = con.selectWhere("paciente", "idpaciente", "dni", dni[0]);
					cmbFactura.setModel(rellenarDatosTriple("facturacion","fecha","idfacturacion","total",idpaciente, modeloDatos));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
			}
		});
		
		String[] dni = cmbPaciente.getSelectedItem().toString().split("-");
		String idpaciente = con.selectWhere("paciente", "idpaciente", "dni", dni[0]);
		cmbFactura.setModel(rellenarDatosTriple("facturacion","fecha","idfacturacion","total",idpaciente, modeloDatos));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DefaultComboBoxModel rellenarDatos(String nombreTabla, String campo,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBox(nombreTabla, campo);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return comboDatos;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DefaultComboBoxModel rellenarDatosDoble(String nombreTabla, String campo, String campo2,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBoxDoble(nombreTabla, campo, campo2);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DefaultComboBoxModel rellenarDatosTriple(String nombreTabla, String campo, String campo2, String campo3, String idpaciente,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBoxTriple(nombreTabla, campo, campo2, campo3, idpaciente);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}
}
