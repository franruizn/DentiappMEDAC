package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
<<<<<<< Updated upstream
=======
import com.toedter.calendar.JCalendar;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
>>>>>>> Stashed changes

public class ModificarConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

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
		
<<<<<<< Updated upstream
=======
		textField = new JTextField();
		textField.setBounds(64, 163, 282, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(424, 163, 282, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(60, 292, 205, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnModificarConsulta = new JButton("Actualizar");
		btnModificarConsulta.setBounds(652, 415, 89, 23);
		contentPanel.add(btnModificarConsulta);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("derek bobo");
			}
		});
		dateChooser.setBounds(309, 286, 185, 31);
		contentPanel.add(dateChooser);
		
>>>>>>> Stashed changes
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificarConsultaDialog.class.getResource("/fotos/mod_consulta.PNG")));
		lblFondo.setBounds(0, 0, 763, 449);
		contentPanel.add(lblFondo);
		{
			{
				
			}
			{
				
			}
		}
	}

}
