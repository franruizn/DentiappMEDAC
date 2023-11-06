package Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ConexionMySQL;
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

public class CrearDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ConexionMySQL cn = new ConexionMySQL();
	private JTextField txtNombre;
	private JTextField txtDNI;
	private DefaultComboBoxModel<String> modeloEspecialidades = new DefaultComboBoxModel<String>();
	private ArrayList<Especialidad> listaEspecialidades = new ArrayList<Especialidad>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearDoctorDialog dialog = new CrearDoctorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
			lblNombre.setBounds(39, 120, 63, 21);
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
		txtDNI.setBounds(55, 280, 199, 41);
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
						crearDoctor(comboEspecialidades);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
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

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CrearDoctorDialog.class.getResource("/dentiapp/dialog_add_doctor.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);

		rellenarListaEspecialidades();
		rellenarComboEspecialidades(comboEspecialidades);

	}

	@SuppressWarnings({"unused" })
	private void crearDoctor(JComboBox<String> comboEspecialidades) throws SQLException {
		cn.conectar();

		String nombre = txtNombre.getText();
		String dni = txtDNI.getText();
		String especialidad = comboEspecialidades.getSelectedItem().toString();
		int idEspecialidad = obtenerIdEspecialidad(especialidad);
		
		
		String consulta = "INSERT INTO doctor VALUES (0,'"+dni+"',"+idEspecialidad+",'"+nombre+"');";
		cn.ejecutarInsertDeleteUpdate(consulta);
		JOptionPane.showMessageDialog(null, "Doctor agregado con éxito", "Doctor agregado", JOptionPane.OK_OPTION);
	}

	private void rellenarListaEspecialidades() throws SQLException {
		cn.conectar();

		// Consulta a ejecutar
		String consulta = "SELECT * FROM especialidad;";
		ResultSet rset = cn.ejecutarSelect(consulta);
		
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

	private int obtenerIdEspecialidad(String especialidad) throws SQLException {
		cn.conectar();
		int id = 0;

		// Consulta a ejecutar
		String consulta = "SELECT idespecialidad FROM especialidad WHERE nombre = '"+especialidad+"';";
		ResultSet rset = cn.ejecutarSelect(consulta);
		
		if(rset.next()) {
			id = rset.getInt("idespecialidad");
		}
		
		return id;
	}
	
}
