package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
	private JTextField txtDoctor;
	private JTextField txtTratamiento;
	private ControladorSQL con = new ControladorSQL();
	private ArrayList<String[]> consultas = new ArrayList<>();

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
		btnModificarConsulta.setBounds(606, 370, 106, 33);
		contentPanel.add(btnModificarConsulta);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("derek bobo");

			}
		});
		dateChooser.setBounds(60, 149, 185, 42);
		contentPanel.add(dateChooser);

		txtDoctor = new JTextField();
		txtDoctor.setBounds(299, 280, 198, 42);
		contentPanel.add(txtDoctor);
		txtDoctor.setColumns(10);

		txtTratamiento = new JTextField();
		txtTratamiento.setBounds(60, 280, 198, 42);
		contentPanel.add(txtTratamiento);
		txtTratamiento.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				for (int i = 0; i < consultas.size(); i++) {
					if (comboBox.getSelectedItem().equals(consultas.get(i)[0])) {
						try {
							txtDoctor.setText(con.selectWhere("doctor", "nombre", "iddoctor", consultas.get(i)[1]));
							txtTratamiento.setText(con.selectWhere("tratamiento", "nombre", "idtratamiento", consultas.get(i)[2]));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		comboBox.setBounds(417, 158, 295, 22);
		contentPanel.add(comboBox);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = "'" + sdf.format(dateChooser.getDate()) + "'";
				try {

					consultas = con.obtenerConsulta(fecha);
					for (int i = 0; i < consultas.size(); i++) {
						comboBox.addItem(consultas.get(i)[0]);
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

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarConsultaDialog.class.getResource("/fotos/mod_consulta.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
	}

}
