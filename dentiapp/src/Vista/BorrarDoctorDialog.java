package Vista;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorHibernate;
import Modelo.Doctor;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class BorrarDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultComboBoxModel<String> modeloDocs = new DefaultComboBoxModel<String>();
	private ArrayList<Object> listaDocs = new ArrayList<Object>();
	private ControladorHibernate con = new ControladorHibernate();

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
		setBounds(100, 100, 575, 457);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox<?> cmbDoctores = new JComboBox<Object>();
		cmbDoctores.setBounds(108, 110, 399, 37);
		contentPanel.add(cmbDoctores);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(BorrarDoctorDialog.class.getResource("/fotos/dialog_borrar_doctor.PNG")));
			lblFondo.setBounds(0, 0, 564, 421);
			contentPanel.add(lblFondo);
		}
	}
	
	public void rellenarCombo() {
		listaDocs =con.getDatos(Doctor.class);
		listaDocs.get(0).toString();
	}
}
