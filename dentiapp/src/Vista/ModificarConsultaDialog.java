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
import com.toedter.calendar.JTextFieldDateEditor;

import Controlador.ControladorSQL;
import paqGUI.BotonPersonalizadoBean;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

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
	public ModificarConsultaDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 764, 447);
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
		
		JComboBox cmbHora = new JComboBox();
		cmbHora.setBounds(182, 159, 65, 22);
		contentPanel.add(cmbHora);
		btnprsnlzdbnCerrar.setBounds(650, 11, 85, 42);
		contentPanel.add(btnprsnlzdbnCerrar);
		
		cmbHora.addItem("16:00");
		cmbHora.addItem("17:00");
		cmbHora.addItem("18:00");
		cmbHora.addItem("19:00");
		cmbHora.addItem("20:00");
		cmbHora.addItem("21:00");

		JButton btnModificarConsulta = new JButton("Actualizar");
		btnModificarConsulta.setBounds(641, 415, 100, 23);
		contentPanel.add(btnModificarConsulta);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(55, 149, 122, 42);
		contentPanel.add(dateChooser);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		Date currentDate = new Date();
		dateChooser.setMinSelectableDate(currentDate);

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
					String hora=cmbHora.getSelectedItem().toString();
					consultas = con.obtenerConsulta(fecha,hora);
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
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblFecha.setBounds(-21, 110, 228, 22);
		contentPanel.add(lblFecha);
		
		JLabel lblPaciente = new JLabel("PACIENTE");
		lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaciente.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPaciente.setBounds(442, 110, 228, 22);
		contentPanel.add(lblPaciente);
		
		JLabel lblDoctor = new JLabel("DOCTOR");
		lblDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctor.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDoctor.setBounds(43, 235, 228, 22);
		contentPanel.add(lblDoctor);
		
		JLabel lblTratamiento = new JLabel("TRATAMIENTO");
		lblTratamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblTratamiento.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblTratamiento.setBounds(284, 235, 228, 22);
		contentPanel.add(lblTratamiento);
		
		JLabel lblObservaciones = new JLabel("OBSERVACIONES");
		lblObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservaciones.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblObservaciones.setBounds(513, 203, 228, 22);
		contentPanel.add(lblObservaciones);
				
				JLabel lblHora = new JLabel("HORA");
				lblHora.setHorizontalAlignment(SwingConstants.CENTER);
				lblHora.setFont(new Font("SansSerif", Font.PLAIN, 20));
				lblHora.setBounds(122, 110, 172, 22);
				contentPanel.add(lblHora);
		
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
