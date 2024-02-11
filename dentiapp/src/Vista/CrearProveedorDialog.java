package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import paqGUI.BotonPersonalizadoBean;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSQL;
import Modelo.Especialidad;

import java.awt.Font;

public class CrearProveedorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL cn = new ControladorSQL();
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTable tblDatos;
	private DefaultTableModel modeloDatos = new DefaultTableModel();
	private String[] listaDatos = new String[4];
	private JTextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearProveedorDialog dialog = new CrearProveedorDialog();
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
	public CrearProveedorDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 765, 448);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		BotonPersonalizadoBean btnprsnlzdbnCerrar = new BotonPersonalizadoBean();
		btnprsnlzdbnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnprsnlzdbnCerrar.setBounds(650, 11, 85, 42);
		contentPanel.add(btnprsnlzdbnCerrar);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombre.setBackground(new Color(246, 246, 246));
		txtNombre.setBounds(55, 151, 293, 41);
		contentPanel.add(txtNombre);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNombre.setBounds(52, 120, 89, 21);
		contentPanel.add(lblNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtTelefono.setBackground(new Color(246, 246, 246));
		txtTelefono.setBounds(421, 151, 293, 41);
		contentPanel.add(txtTelefono);
		
		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblTelfono.setBounds(421, 120, 89, 21);
		contentPanel.add(lblTelfono);
		
		{JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String columnasProveedor = "nombre,telefono";
					String ValoresProveedor = "'"+ txtNombre.getText() + "','" +txtTelefono.getText() + "'";
					cn.insertarConsulta("proveedor",columnasProveedor,ValoresProveedor);
					JOptionPane.showMessageDialog(null, "Proveedor Creado con Exito",
							"Proveedor Creado", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error al crear Proveedor - Los datos introducidos no son correctos.\n Asegurese de que el Telefono y Nombre son correctos",
							"Error al crear Proveedor", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
				}
				//dispose();
			}

		});
		btnCrear.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCrear.setBounds(499, 367, 96, 41);
		btnCrear.setBackground(new Color(55, 4, 102));
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setActionCommand("OK");
		contentPanel.add(btnCrear);
		getRootPane().setDefaultButton(btnCrear);
		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCancelar.setBounds(619, 367, 97, 41);
		btnCancelar.setActionCommand("Cancel");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(55, 4, 102));
		contentPanel.add(btnCancelar);
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/crear_proveedor.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
	}
}
				
		
	


