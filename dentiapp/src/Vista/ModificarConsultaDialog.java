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

public class ModificarConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private ArrayList<String[]> consultas = new ArrayList<>();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private String fecha="";

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
		setBounds(100, 100, 781, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnModificarConsulta = new JButton("Actualizar");
		btnModificarConsulta.setBounds(652, 415, 89, 23);
		contentPanel.add(btnModificarConsulta);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(60, 149, 185, 42);
		contentPanel.add(dateChooser);

		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(446, 158, 169, 22);
		contentPanel.add(cmbPaciente);


		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbPaciente.removeAll();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fecha = "'" + sdf.format(dateChooser.getDate()) + "'";
				try {

					consultas = con.obtenerConsulta(fecha);
					for (int i = 0; i < consultas.size(); i++) {
						cmbPaciente.addItem(consultas.get(i)[0]);
					}

				} catch (SQLException e1) {
					// Error en la fecha
					JOptionPane.showMessageDialog(null, "No se han encontrado consultas.\nSelecciona una fecha válida",
							"Fecha no válida", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(256, 158, 100, 23);
		contentPanel.add(btnNewButton);

		JComboBox cmbDoctor = new JComboBox();
		cmbDoctor.setBounds(60, 288, 196, 22);
		contentPanel.add(cmbDoctor);

		JComboBox cmbTratamiento = new JComboBox();
		cmbTratamiento.setBounds(307, 288, 185, 22);
		contentPanel.add(cmbTratamiento);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarConsultaDialog.class.getResource("/fotos/mod_consulta.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);

		cmbPaciente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cmbDoctor.removeAll();
				cmbTratamiento.removeAll();
				for (int i = 0; i < consultas.size(); i++) {
					if (cmbPaciente.getSelectedItem().equals(consultas.get(i)[0])) {
						cmbDoctor.setModel(rellenarDatos("doctor", "nombre", modeloDatos));
						cmbTratamiento.setModel(rellenarDatos("tratamiento", "nombre", modeloDatos));
						try {
							cmbDoctor.setSelectedItem(con.selectWhere("paciente", "nombre", "idpaciente", consultas.get(i)[0]));
							cmbDoctor.setSelectedItem(con.selectWhere("doctor", "nombre", "iddoctor", consultas.get(i)[1]));
							cmbDoctor.setSelectedItem(con.selectWhere("tratamiento", "nombre", "idtratamiento", consultas.get(i)[2]));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println(consultas.get(i)[2]);
					}
				}
				
			}
		});
		btnModificarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreColumnas;
				try {
					String id = con.selectWhereDoble("consulta", "idconsulta", "fecha", fecha,"fk_idpaciente",cmbPaciente.getSelectedItem().toString());
					System.out.println(id);
					nombreColumnas = con.obtenerColumnas("consulta");
					System.out.println(nombreColumnas);
					String newValues=id+" , "+cmbPaciente.getSelectedItem().toString()+" , "+cmbDoctor.getSelectedItem().toString()+" , "+cmbTratamiento.getSelectedItem().toString()+" null,"+fecha;
					System.out.println(newValues);
					con.updateSQL("consulta",nombreColumnas,newValues);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public DefaultComboBoxModel rellenarDatos(String nombreTabla, String campo, DefaultComboBoxModel<String> comboDatos) {
		try {
			
			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBox(nombreTabla, campo);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return comboDatos;
	}

}
