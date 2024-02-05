package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import Controlador.ControladorSQL;
import paqGUI.BotonPersonalizadoBean;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CrearConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private JTextField txtPaciente;
	private JTextField txtDoctor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearConsultaDialog dialog = new CrearConsultaDialog();
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
	public CrearConsultaDialog() {
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		// setBounds(100, 100, 959, 578);
		setBounds(100, 100, 763, 449);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 959, 556);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		BotonPersonalizadoBean btnprsnlzdbnCerrar = new BotonPersonalizadoBean();
		btnprsnlzdbnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnprsnlzdbnCerrar.setBounds(650, 11, 85, 42);
		contentPanel.add(btnprsnlzdbnCerrar);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(295, 288, 211, 29);
		dateChooser.setMinSelectableDate(new Date());
		contentPanel.add(dateChooser);

		JButton btnAceptar = new JButton("Aceptar");

		btnAceptar.setBounds(637, 420, 89, 23);
		contentPanel.add(btnAceptar);

		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(48, 161, 154, 22);
		contentPanel.add(cmbPaciente);

		JComboBox cmbDoctores = new JComboBox();
		cmbDoctores.setBounds(412, 161, 147, 22);
		contentPanel.add(cmbDoctores);

		JComboBox cmbTratamiento = new JComboBox();
		cmbTratamiento.setBounds(48, 288, 211, 22);
		contentPanel.add(cmbTratamiento);

		JComboBox cmbHora = new JComboBox();
		cmbHora.setBounds(295, 366, 211, 22);
		contentPanel.add(cmbHora);

		cmbHora.addItem("16:00");
		cmbHora.addItem("17:00");
		cmbHora.addItem("18:00");
		cmbHora.addItem("19:00");
		cmbHora.addItem("20:00");
		cmbHora.addItem("21:00");

		JTextArea txtObservaciones = new JTextArea();
		txtObservaciones.setBounds(551, 240, 154, 170);
		contentPanel.add(txtObservaciones);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = "'" + sdf.format(dateChooser.getDate()) + "'";

				try {

					String tratamiento = con.selectWhere("tratamiento", "idtratamiento", "nombre",
							cmbTratamiento.getSelectedItem().toString());
					String paciente = con.selectWhere("paciente", "idpaciente", "nombre",
							cmbPaciente.getSelectedItem().toString());
					String doctor = con.selectWhere("doctor", "iddoctor", "nombre",
							cmbDoctores.getSelectedItem().toString());
					String observaciones = txtObservaciones.getText();
					String hora = cmbHora.getSelectedItem().toString();
					String nombreColumnas = con.obtenerColumnas("consulta");
					nombreColumnas = nombreColumnas.substring(11, nombreColumnas.length());
					String newValues = "" + paciente + "," + doctor + "," + tratamiento + ",'" + observaciones + "',"
							+ fecha + "'," + hora;
					con.insertarConsulta("consulta", nombreColumnas, newValues);
					JOptionPane.showMessageDialog(null, "Consulta Creada con Exito", "Consulta Creada",
							JOptionPane.WARNING_MESSAGE,
							new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/iconoOk.png")));

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error al crear Consulta", "Error al crear consulta",
							JOptionPane.WARNING_MESSAGE,
							new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/iconoOk.png")));
					e1.printStackTrace();
				}

			}
		});
		cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
		cmbDoctores.setModel(rellenarDatos("doctor", "nombre", modeloDatos));
		cmbTratamiento.setModel(rellenarDatos("tratamiento", "nombre", modeloDatos));

		JLabel lblPaciente = new JLabel("PACIENTE");
		lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaciente.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPaciente.setBounds(65, 117, 113, 22);
		contentPanel.add(lblPaciente);

		JLabel lblDoctor = new JLabel("DOCTOR");
		lblDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctor.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDoctor.setBounds(431, 117, 113, 22);
		contentPanel.add(lblDoctor);

		JLabel lblTratamiento = new JLabel("TRATAMIENTO");
		lblTratamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTratamiento.setBounds(33, 246, 228, 22);
		contentPanel.add(lblTratamiento);

		JLabel lblObservaciones = new JLabel("OBSERVACIONES");
		lblObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblObservaciones.setBounds(512, 210, 228, 22);
		contentPanel.add(lblObservaciones);

		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblFecha.setBounds(283, 246, 228, 22);
		contentPanel.add(lblFecha);

		JLabel lblHora = new JLabel("HORA");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHora.setBounds(283, 333, 228, 22);
		contentPanel.add(lblHora);

		txtPaciente = new JTextField();
		txtPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
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
		txtPaciente.setBounds(203, 162, 137, 20);
		contentPanel.add(txtPaciente);
		txtPaciente.setColumns(10);

		JLabel lblBuscador = new JLabel("BUSCADOR");
		lblBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscador.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblBuscador.setBounds(210, 117, 113, 22);
		contentPanel.add(lblBuscador);

		txtDoctor = new JTextField();
		txtDoctor.setColumns(10);
		txtDoctor.setBounds(571, 162, 144, 20);
		contentPanel.add(txtDoctor);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 959, 449);
		lblNewLabel.setIcon(new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/crear_consulta.PNG")));
		contentPanel.add(lblNewLabel);

		txtDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cmbDoctores.setModel(rellenarDatos("doctor", "nombre", modeloDatos));
				String textoBusqueda = txtDoctor.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbDoctores.getItemCount(); i++) {
					if (cmbDoctores.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbDoctores.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbDoctores.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbDoctores.setPopupVisible(true);
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
}
