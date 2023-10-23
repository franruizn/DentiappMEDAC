package dentiapp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class CrearDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ConexionMySQL cn = new ConexionMySQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearDoctorDialog dialog = new CrearDoctorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearDoctorDialog() {
		setBounds(100, 100, 727, 461);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnCrear = new JButton("Crear");
			btnCrear.setFont(new Font("SansSerif", Font.PLAIN, 11));
			btnCrear.setBounds(512, 384, 82, 32);
			btnCrear.setBackground(new Color(55,4,102));
			btnCrear.setForeground(Color.WHITE);
			btnCrear.setActionCommand("OK");
			contentPanel.add(btnCrear);
			getRootPane().setDefaultButton(btnCrear);
		}
		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 11));
			btnCancelar.setBounds(601, 384, 82, 32);
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBackground(new Color(55,4,102));
			contentPanel.add(btnCancelar);
		}
		{
			JLabel lblDoctorId = new JLabel("ID");
			lblDoctorId.setFont(new Font("SansSerif", Font.PLAIN, 18));
			lblDoctorId.setBounds(304, 123, 46, 14);
			contentPanel.add(lblDoctorId);
		}
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
			lblNewLabel.setBounds(39, 120, 63, 21);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CrearDoctorDialog.class.getResource("/dentiapp/dialog_add_doctor.PNG")));
		lblFondo.setBounds(0, 0, 716, 427);
		contentPanel.add(lblFondo);
	}
	
	private void crearDoctor() {
		
	}
}
