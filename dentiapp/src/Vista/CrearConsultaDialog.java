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

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CrearConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearConsultaDialog dialog = new CrearConsultaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearConsultaDialog() {
		setResizable(false);
		// setBounds(100, 100, 959, 578);
		setBounds(100, 100, 781, 486);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 959, 556);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(295, 288, 211, 29);
		contentPanel.add(dateChooser);

		JButton btnAceptar = new JButton("Aceptar");

		btnAceptar.setBounds(637, 420, 89, 23);
		contentPanel.add(btnAceptar);

		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(48, 161, 307, 22);
		contentPanel.add(cmbPaciente);

		JComboBox cmbDoctor = new JComboBox();
		cmbDoctor.setBounds(412, 161, 293, 22);
		contentPanel.add(cmbDoctor);

		JComboBox cmbTratamiento = new JComboBox();
		cmbTratamiento.setBounds(48, 288, 211, 22);
		contentPanel.add(cmbTratamiento);

		JTextArea txtObservaciones = new JTextArea();
		txtObservaciones.setBounds(551, 240, 154, 170);
		contentPanel.add(txtObservaciones);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = "'" + sdf.format(dateChooser.getDate()) + "'";

				try {

					String tratamiento=con.selectWhere("tratamiento", "idtratamiento", "nombre",cmbTratamiento.getSelectedItem().toString());
					String paciente=con.selectWhere("paciente", "idpaciente", "nombre",cmbPaciente.getSelectedItem().toString());
					String doctor=con.selectWhere("doctor", "iddoctor", "nombre",cmbDoctor.getSelectedItem().toString());
					String observaciones=txtObservaciones.getText();
					String nombreColumnas=con.obtenerColumnas("consulta");
					nombreColumnas=nombreColumnas.substring(11,nombreColumnas.length());
					String newValues=""+paciente+","+doctor+","+tratamiento+",'"+observaciones+"',"+fecha;
					con.insertarConsulta("consulta",nombreColumnas, newValues);
					JOptionPane.showMessageDialog(null, "Consulta Creada con Exito",
							"Consulta Creada", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/iconoOk.png")));

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error al crear Consulta",
							"Error al crear consulta", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/iconoOk.png")));
					e1.printStackTrace();
				}

			}
		});
		cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
		cmbDoctor.setModel(rellenarDatos("doctor", "nombre", modeloDatos));
		cmbTratamiento.setModel(rellenarDatos("tratamiento", "nombre", modeloDatos));
		
		JLabel lblPaciente = new JLabel("PACIENTE");
		lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaciente.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPaciente.setBounds(92, 117, 228, 22);
		contentPanel.add(lblPaciente);
		
		JLabel lblDoctor = new JLabel("DOCTOR");
		lblDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctor.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDoctor.setBounds(451, 117, 228, 22);
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
		
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setBounds(0, 0, 959, 449);
				lblNewLabel.setIcon(new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/crear_consulta.PNG")));
				contentPanel.add(lblNewLabel);

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
