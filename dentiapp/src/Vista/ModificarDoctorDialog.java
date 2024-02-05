package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import paqGUI.BotonPersonalizadoBean;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class ModificarDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEstamosTrabajandoEn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarDoctorDialog dialog = new ModificarDoctorDialog();
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
	public ModificarDoctorDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 781, 486);
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
		
		txtEstamosTrabajandoEn = new JTextField();
		txtEstamosTrabajandoEn.setEditable(false);
		txtEstamosTrabajandoEn.setFont(new Font("Tahoma", Font.PLAIN, 46));
		txtEstamosTrabajandoEn.setText("Estamos trabajando en ello");
		txtEstamosTrabajandoEn.setBounds(115, 11, 565, 62);
		contentPanel.add(txtEstamosTrabajandoEn);
		txtEstamosTrabajandoEn.setColumns(10);
		btnprsnlzdbnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 37));
		btnprsnlzdbnCerrar.setTexto("Salir");
		btnprsnlzdbnCerrar.setBounds(10, 355, 200, 120);
		contentPanel.add(btnprsnlzdbnCerrar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ModificarDoctorDialog.class.getResource("/fotos/creando.jpg")));
		lblNewLabel.setBounds(0, 0, 793, 545);
		contentPanel.add(lblNewLabel);
		
		{
			{
				
			}
			{
				
			}
		}
	}
}
