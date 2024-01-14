package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValidacionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblSolRecibidas;
	private JTextField txtCantidadMat;
	private DefaultComboBoxModel modeloMateriales = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloProv = new DefaultComboBoxModel();
	private DefaultComboBoxModel modeloDoctores = new DefaultComboBoxModel();
	private DefaultTableModel modeloSolicitudes = new DefaultTableModel();
	private ControladorSQL con = new ControladorSQL();
	private String[] mostrarSoli = {"fk_iddoctor","material","cantidad","proveedor"};
	private JTextField txtDoctor;

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
	 * @throws SQLException 
	 */
	public ValidacionDialog() throws SQLException {
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
		lblDatosSol.setBounds(599, 133, 193, 33);
		contentPanel.add(lblDatosSol);
		
		txtCantidadMat = new JTextField();
		txtCantidadMat.setColumns(10);
		txtCantidadMat.setBounds(682, 268, 154, 19);
		contentPanel.add(txtCantidadMat);
		
		JComboBox cmbProveedores = new JComboBox();
		cmbProveedores.setBounds(682, 308, 154, 21);
		contentPanel.add(cmbProveedores);
		
		JComboBox cmbDoctores = new JComboBox();
		cmbDoctores.setBounds(682, 372, 154, 21);
		contentPanel.add(cmbDoctores);
		cmbDoctores.setModel(rellenarDatos("doctor", "nombre", modeloDoctores));
		
		JComboBox cmbNombreMat = new JComboBox();
		cmbNombreMat.setBounds(682, 222, 154, 21);
		contentPanel.add(cmbNombreMat);
		
		cmbNombreMat.setModel(rellenarDatos("stock","nombre",modeloMateriales));
		
		JButton btnEnvDatos = new JButton("Enviar Solicitud");
		btnEnvDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombreColumnas = con.obtenerColumnas("solicitudes");
					String material=cmbNombreMat.getSelectedItem().toString();
					String cantidad=txtCantidadMat.getText().toString();
					String doctor=con.selectWhere("doctor", "iddoctor", "nombre",cmbDoctores.getSelectedItem().toString());
					String proveedor=cmbProveedores.getSelectedItem().toString();
					nombreColumnas=nombreColumnas.substring(14,nombreColumnas.length());
					System.out.println(nombreColumnas);
					String newValues="'"+doctor+"','"+material+"',"+cantidad+",0,'"+proveedor+"'";
					con.insertarConsulta("solicitudes",nombreColumnas, newValues);
					JOptionPane.showMessageDialog(null, "Solicitud Creada con Exito",
							"Solicitud Creada", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/iconoOk.png")));
					tblSolRecibidas.setModel(con.cargarSolicitudes("solicitudes", modeloSolicitudes, mostrarSoli));
					tblSolRecibidas.getColumnModel().getColumn(0).setPreferredWidth(25);
					tblSolRecibidas.getColumnModel().getColumn(1).setPreferredWidth(150);
					tblSolRecibidas.getColumnModel().getColumn(2).setPreferredWidth(25);
					tblSolRecibidas.getColumnModel().getColumn(3).setPreferredWidth(100);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnvDatos.setBounds(634, 417, 113, 21);
		contentPanel.add(btnEnvDatos);
		
		JLabel lblValidacion = new JLabel("VALIDACIÓN DE SOLICITUDES");
		lblValidacion.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblValidacion.setBounds(254, 35, 444, 55);
		contentPanel.add(lblValidacion);
		
		
		
		JLabel lblDoctor = new JLabel("Doctor Solicitante:");
		lblDoctor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblDoctor.setBounds(535, 348, 154, 13);
		contentPanel.add(lblDoctor);
		
		
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
		
		tblSolRecibidas.setModel(con.cargarSolicitudes("solicitudes", modeloSolicitudes, mostrarSoli));
		
		txtDoctor = new JTextField();
		txtDoctor.setBounds(681, 344, 155, 20);
		contentPanel.add(txtDoctor);
		txtDoctor.setColumns(10);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/ventana_admin.PNG")));
			lblFondo.setBounds(0, 0, 954, 594);
			contentPanel.add(lblFondo);
		}
		
		txtDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cmbDoctores.setModel(rellenarDatos("doctor", "nombre", modeloDoctores));
				String textoBusqueda = txtDoctor.getText().toLowerCase();

                // Filtrar los elementos del combo que coincidan con el texto de búsqueda
				
                List<String> elementosFiltrados = new ArrayList<>();
                for (int i = 0; i<cmbDoctores.getItemCount();i++) {
                    if (cmbDoctores.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
                        elementosFiltrados.add(cmbDoctores.getItemAt(i).toString());
                    }
                }

                // Actualizar los elementos del combo con los resultados filtrados
                
                cmbDoctores.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
                cmbDoctores.setPopupVisible(true);
			}
		});
		
		tblSolRecibidas.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblSolRecibidas.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblSolRecibidas.getColumnModel().getColumn(2).setPreferredWidth(25);
		tblSolRecibidas.getColumnModel().getColumn(3).setPreferredWidth(100);
		
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


