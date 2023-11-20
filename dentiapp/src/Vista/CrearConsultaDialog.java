package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CrearConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

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
		setBounds(100, 100, 959, 578);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 959, 556);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(385, 359, 231, 30);
		contentPanel.add(dateChooser);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha= "'"+sdf.format(dateChooser.getDate())+"'";
				
			}
		});
		btnAceptar.setBounds(699, 481, 89, 23);
		contentPanel.add(btnAceptar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(85, 201, 341, 22);
		contentPanel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(535, 201, 341, 22);
		contentPanel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(85, 359, 231, 22);
		contentPanel.add(comboBox_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 959, 556);
		lblNewLabel.setIcon(new ImageIcon(CrearConsultaDialog.class.getResource("/fotos/crear_consulta.PNG")));
		contentPanel.add(lblNewLabel);
		{
			{
				
			}
			{
				
			}
		}
	}
}
