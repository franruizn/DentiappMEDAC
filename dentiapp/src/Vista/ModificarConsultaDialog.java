package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

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
	private ControladorSQL con= new ControladorSQL();
	private ArrayList<String[]> consultas=new ArrayList<>();
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
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("derek bobo");
				
			}
		});
		dateChooser.setBounds(60, 158, 185, 31);
		contentPanel.add(dateChooser);
		
		txtDoctor = new JTextField();
		txtDoctor.setBounds(364, 286, 86, 20);
		contentPanel.add(txtDoctor);
		txtDoctor.setColumns(10);
		
		txtTratamiento = new JTextField();
		txtTratamiento.setBounds(113, 286, 86, 20);
		contentPanel.add(txtTratamiento);
		txtTratamiento.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				for(int i =0; i<consultas.size();i++) {
					if(comboBox.getSelectedItem().equals(consultas.get(i)[0])) {
						txtDoctor.setText(consultas.get(i)[1]);
						txtTratamiento.setText(consultas.get(i)[2]);
					}
				}
			}
		});
		
		comboBox.setBounds(446, 158, 169, 22);
		contentPanel.add(comboBox);
		
		
		
		JButton btnNewButton = new JButton("Comprobar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha= "'"+sdf.format(dateChooser.getDate())+"'";
				try {
					
					consultas= con.obtenerConsulta(fecha);
					for (int i=0;i<consultas.size();i++) {
						comboBox.addItem(consultas.get(i)[0]);
					}
						
					
				} catch (SQLException e1) {
					// Error en la fecha
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(255, 158, 89, 23);
		contentPanel.add(btnNewButton);
		
		
		

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarConsultaDialog.class.getResource("/fotos/mod_consulta.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
		}

}
