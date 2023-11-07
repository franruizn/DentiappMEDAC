package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class BorrarDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BorrarDoctorDialog dialog = new BorrarDoctorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BorrarDoctorDialog() {
		setBounds(100, 100, 575, 456);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cmbDoctores = new JComboBox();
		cmbDoctores.setBounds(108, 110, 399, 37);
		contentPanel.add(cmbDoctores);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(BorrarDoctorDialog.class.getResource("/fotos/dialog_borrar_doctor.PNG")));
			lblFondo.setBounds(0, 0, 564, 421);
			contentPanel.add(lblFondo);
		}
	}
}
