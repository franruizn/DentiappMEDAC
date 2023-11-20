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

import com.toedter.calendar.JCalendar;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Controlador.ControladorSQL;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;

public class ModificarConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private ArrayList<String[]> consultas = new ArrayList<>();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private String fecha = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarConsultaDialog dialog = new ModificarConsultaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarConsultaDialog() {
		setResizable(false);
		setBounds(100, 100, 781, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnModificarConsulta = new JButton("Actualizar");
		btnModificarConsulta.setBounds(641, 415, 100, 23);
		contentPanel.add(btnModificarConsulta);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(60, 149, 185, 42);
		contentPanel.add(dateChooser);

		JComboBox cmbPaciente = new JComboBox();

		cmbPaciente.setBounds(428, 149, 275, 43);
		contentPanel.add(cmbPaciente);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbPaciente.removeAll();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fecha = "'" + sdf.format(dateChooser.getDate()) + "'";
				try {

					consultas = con.obtenerConsulta(fecha);
					for (int i = 0; i < consultas.size(); i++) {
						cmbPaciente.addItem(con.selectWhere("paciente", "nombre", "idpaciente", consultas.get(i)[0]));
					}

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No se han encontrado consultas.\nSelecciona una fecha válida",
							"Fecha no válida", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnBuscar.setBounds(256, 158, 100, 23);
		contentPanel.add(btnBuscar);

		JComboBox cmbDoctor = new JComboBox();
		cmbDoctor.setBounds(58, 278, 201, 42);
		contentPanel.add(cmbDoctor);

		JComboBox cmbTratamiento = new JComboBox();
		cmbTratamiento.setBounds(303, 278, 198, 43);
		contentPanel.add(cmbTratamiento);

		JTextArea txtaObservaciones = new JTextArea();
		txtaObservaciones.setBounds(522, 229, 196, 175);
		contentPanel.add(txtaObservaciones);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarConsultaDialog.class.getResource("/fotos/mod_consulta.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);

		cmbPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbDoctor.removeAll();
				cmbTratamiento.removeAll();
				for (int i = 0; i < consultas.size(); i++) {
					try {
						if (cmbPaciente.getSelectedItem()
								.equals(con.selectWhere("paciente", "nombre", "idpaciente", consultas.get(i)[0]))) {
							cmbDoctor.setModel(rellenarDatos("doctor", "nombre", modeloDatos));
							cmbTratamiento.setModel(rellenarDatos("tratamiento", "nombre", modeloDatos));
							cmbPaciente.setSelectedItem(
									con.selectWhere("paciente", "nombre", "idpaciente", consultas.get(i)[0]));
							cmbDoctor.setSelectedItem(
									con.selectWhere("doctor", "nombre", "iddoctor", consultas.get(i)[1]));
							cmbTratamiento.setSelectedItem(
									con.selectWhere("tratamiento", "nombre", "idtratamiento", consultas.get(i)[2]));
							txtaObservaciones.setText(consultas.get(i)[3]);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		btnModificarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreColumnas;
				try {
					String idPaciente = con.selectWhere("paciente", "idpaciente", "nombre",
							cmbPaciente.getSelectedItem().toString());
					String idConsulta = con.selectWhereDoble("consulta", "idconsulta", "fecha", fecha, "fk_idpaciente",
							idPaciente);
					String idDoctor = con.selectWhere("doctor", "iddoctor", "nombre",
							cmbDoctor.getSelectedItem().toString());
					String idTratamiento = con.selectWhere("tratamiento", "idtratamiento", "nombre",
							cmbTratamiento.getSelectedItem().toString());
					
					String newValues = idConsulta + " , " + idPaciente + " , " + idDoctor + " , " + idTratamiento
							+ " , '" + txtaObservaciones.getText() + "' , " + fecha;
					
					nombreColumnas = con.obtenerColumnas("consulta");
					String[] columnas = nombreColumnas.split(",");
					String[] valores = newValues.split(",");
					con.updateSQL("consulta", columnas, valores);
					JOptionPane.showMessageDialog(null, "Consulta Actualizada con Exito",
							"Consulta Actualizada", JOptionPane.WARNING_MESSAGE,new ImageIcon(ModificarConsultaDialog.class.getResource("/fotos/iconoOk.png")));

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error al modificar Consulta",
							"Error al modificar consulta", JOptionPane.WARNING_MESSAGE,new ImageIcon(ModificarConsultaDialog.class.getResource("/fotos/iconoNo.png")));
					e1.printStackTrace();
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
}
