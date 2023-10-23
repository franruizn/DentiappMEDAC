package dentiapp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
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
	private JTextField txtID;
	private DefaultComboBoxModel modeloEspecialidades = new DefaultComboBoxModel();
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
		setBounds(100, 100, 781, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 16));
			btnCancelar.setBounds(619, 367, 97, 41);
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBackground(new Color(55, 4, 102));
			contentPanel.add(btnCancelar);
		}
		{
			JLabel lblDoctorId = new JLabel("ID");
			lblDoctorId.setFont(new Font("SansSerif", Font.PLAIN, 18));
			lblDoctorId.setBounds(323, 123, 46, 14);
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
		txtNombre.setBounds(55, 152, 206, 41);
		txtNombre.setBackground(new Color(246, 246, 246));
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtID = new JTextField();
		txtID.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtID.setBackground(new Color(246, 246, 246));
		txtID.setBounds(356, 152, 63, 41);
		contentPanel.add(txtID);
		txtID.setColumns(10);

		JComboBox comboEspecialidades = new JComboBox();
		comboEspecialidades.setBounds(513, 152, 206, 41);

		contentPanel.add(comboEspecialidades);
		{
			JButton btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearDoctor(comboEspecialidades);
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

	private void crearDoctor(JComboBox comboEspecialidades) throws SQLException {
		cn.conectar();

		String nombre = txtNombre.getText();
		int id = Integer.parseInt(txtID.getText());
		String especialidad = comboEspecialidades.getSelectedItem().toString();
		int idEspecialidad = obtenerIdEspecialidad(especialidad);
		
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

	private void rellenarComboEspecialidades(JComboBox comboEspecialidades) {
		for (Especialidad especialidad1 : listaEspecialidades) {
			modeloEspecialidades.addElement(especialidad1.getNombre());
		}

		comboEspecialidades.setModel(modeloEspecialidades);
	}

	private int obtenerIdEspecialidad(String especialidad) throws SQLException {
		cn.conectar();
		int id = 0;

		// Consulta a ejecutar
		String consulta = "SELECT id FROM especialidad WHERE nombre = '"+especialidad+"';";
		ResultSet rset = cn.ejecutarSelect(consulta);
		
		if(rset.next()) {
			id = rset.getInt("idespecialidad");
		}
		
		return id;
	}
}
