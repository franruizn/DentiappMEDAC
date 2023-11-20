package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class CrearFacturacionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearFacturacionDialog dialog = new CrearFacturacionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearFacturacionDialog() {
		setBounds(100, 100, 781, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CrearFacturacionDialog.class.getResource("/fotos/crear_factura1.PNG")));
		lblFondo.setBounds(0, 0, 763, 457);
		contentPanel.add(lblFondo);
		
		
		{
			{
				
			}
			{
				
			}
		}
	}

}
