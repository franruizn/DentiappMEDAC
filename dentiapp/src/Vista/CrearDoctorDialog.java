package Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ConexionMySQL;
import Controlador.ControladorSQL;
import Modelo.Especialidad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class CrearDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL cn = new ControladorSQL();
	private JTextField txtNombre;
	private JTextField txtDNI;
	private DefaultComboBoxModel<String> modeloEspecialidades = new DefaultComboBoxModel<String>();
	private ArrayList<Especialidad> listaEspecialidades = new ArrayList<Especialidad>();
	private JTable tblDatos;
	private DefaultTableModel modeloDatos = new DefaultTableModel();
	private String[] listaDatos = new String[4];
	private JTextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearDoctorDialog dialog = new CrearDoctorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public CrearDoctorDialog() throws SQLException {
		setResizable(false);
		setBounds(100, 100, 781, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			JLabel lblPass = new JLabel("Contraseña");
			lblPass.setFont(new Font("SansSerif", Font.PLAIN, 18));
			lblPass.setBounds(493, 235, 102, 32);
			contentPanel.add(lblPass);
			btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 16));
			btnCancelar.setBounds(619, 367, 97, 41);
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBackground(new Color(55, 4, 102));
			contentPanel.add(btnCancelar);
		}
		{
			JLabel lblDoctorId = new JLabel("DNI");
			lblDoctorId.setFont(new Font("SansSerif", Font.PLAIN, 18));
			lblDoctorId.setBounds(39, 253, 46, 14);
			contentPanel.add(lblDoctorId);
		}
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 18));
			lblNombre.setBounds(39, 120, 89, 21);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblEspecialidad = new JLabel("Especialidad");
			lblEspecialidad.setFont(new Font("SansSerif", Font.PLAIN, 18));
			lblEspecialidad.setBounds(493, 114, 102, 32);
			contentPanel.add(lblEspecialidad);
		}

		txtNombre = new JTextField();
		txtNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNombre.setBounds(55, 152, 293, 41);
		txtNombre.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtDNI.setBackground(new Color(246, 246, 246));
		txtDNI.setBounds(55, 280, 293, 41);
		contentPanel.add(txtDNI);
		txtDNI.setColumns(10);

		JComboBox<String> comboEspecialidades = new JComboBox<String>();
		comboEspecialidades.setBounds(426, 152, 279, 41);

		contentPanel.add(comboEspecialidades);
		{
			JButton btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String columnasDoctor = "nombre,fk_idusuario,fk_idespecialidad";
						String valoresDoctor = "'"+ txtNombre.getText() + "','" +txtDNI.getText() + "','" + cn.obtenerIdEspecialidad(comboEspecialidades.getSelectedItem().toString()) + "'";
						String columnasUser = "idusuario,pass,rol";
						String valoresUser = "'"+txtDNI.getText() + "','" + txtPass.getText() + "'," + "2";
						cn.insertarConsulta("usuario",columnasUser, valoresUser);
						cn.insertarConsulta("doctor",columnasDoctor,valoresDoctor);
						JOptionPane.showMessageDialog(null, "Doctor Creado con Exito",
								"Doctor Creado", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Error al crear Doctor - Los datos introducidos no son correctos.\n Asegurese de que el DNI y Nombre son correctos",
								"Error al crear doctor", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
						e1.printStackTrace();
					}
					//dispose();
				}

				private void obtenerDatos(JComboBox<String> comboEspecialidades) throws SQLException {
					listaDatos[0]=(null);
					listaDatos[1] = (txtDNI.getText());
					listaDatos[3]=(txtNombre.getText());
					listaDatos[2]=(cn.obtenerIdEspecialidad(comboEspecialidades.getSelectedItem().toString()));
					
					modeloDatos.setRowCount(1);
					modeloDatos.setValueAt(listaDatos[0], 0, 0);
					modeloDatos.setValueAt(listaDatos[1], 0, 1);
					modeloDatos.setValueAt(listaDatos[2], 0, 2);
					modeloDatos.setValueAt(listaDatos[3], 0, 3);
					tblDatos.setModel(modeloDatos);
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

		rellenarListaEspecialidades();
		rellenarComboEspecialidades(comboEspecialidades);
				
				txtPass = new JTextField();
				txtPass.setColumns(10);
				txtPass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				txtPass.setBackground(new Color(246, 246, 246));
				txtPass.setBounds(423, 280, 293, 41);
				contentPanel.add(txtPass);
				
						JLabel lblFondo = new JLabel("");
						lblFondo.setIcon(new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/dialog_add_doctor.PNG")));
						lblFondo.setBounds(0, 0, 763, 449);
						contentPanel.add(lblFondo);
						
								tblDatos = new JTable();
								tblDatos.setBounds(416, 204, 300, 139);
								contentPanel.add(tblDatos);
		
		modeloDatos.addColumn("iddoctor");
		modeloDatos.addColumn("fk_idusuario");
		modeloDatos.addColumn("fk_idespecialidad");
		modeloDatos.addColumn("nombre");

	}

	/*@SuppressWarnings({ "unused" })
	private void crearDoctor(JComboBox<String> comboEspecialidades) throws SQLException {
		cn.conectar();

		String nombre = txtNombre.getText();
		String dni = txtDNI.getText();
		String especialidad = comboEspecialidades.getSelectedItem().toString();
		int idEspecialidad = obtenerIdEspecialidad(especialidad);

		String consulta = "INSERT INTO doctor VALUES (0,'" + dni + "'," + idEspecialidad + ",'" + nombre + "');";
		// cn.ejecutarInsertDeleteUpdate(consulta);
		JOptionPane.showMessageDialog(null, "Doctor agregado con éxito", "Doctor agregado", JOptionPane.OK_OPTION);
	}*/

	private void rellenarListaEspecialidades() throws SQLException {
		ConexionMySQL cnn = new ConexionMySQL();
		cnn.conectar();

		// Consulta a ejecutar
		String consulta = "SELECT * FROM especialidad;";
		ResultSet rset = cnn.ejecutarSelect(consulta);

		listaEspecialidades = new ArrayList<Especialidad>();
		while (rset.next()) {
			String nombre = rset.getString("nombre");
			int idEspecialidad = rset.getInt("idespecialidad");

			@SuppressWarnings("unused")
			Especialidad especialidad1;
			listaEspecialidades.add(especialidad1 = new Especialidad(idEspecialidad, nombre));
		}
	}

	private void rellenarComboEspecialidades(JComboBox<String> comboEspecialidades) {
		for (Especialidad especialidad1 : listaEspecialidades) {
			modeloEspecialidades.addElement(especialidad1.getNombre());
		}

		comboEspecialidades.setModel(modeloEspecialidades);
	}
	
	
}
