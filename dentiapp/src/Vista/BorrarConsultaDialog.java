package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class BorrarConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL cn = new ControladorSQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BorrarConsultaDialog dialog = new BorrarConsultaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BorrarConsultaDialog() {
	
		setBounds(100, 100, 575, 457);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(131, 124, 358, 22);
		contentPanel.add(comboBox);
		JLabel lblFondo = new JLabel("");
		
		lblFondo.setIcon(new ImageIcon(BorrarConsultaDialog.class.getResource("/fotos/borrar_consulta.PNG")));
		lblFondo.setBounds(0, 0, 564, 421);
		contentPanel.add(lblFondo);
		
		
	}
	/*public ArrayList <String> cambiarControlador() {
		cn.conectar();
		ArrayList <String> consultas = new ArrayList();
		// Consulta a ejecutar
		String consulta = "Select idconsulta from consulta";
		System.out.print(consulta);
		ResultSet rset = cn.ejecutarSelect(consulta);

		if (rset.next()) {
			consultas.add(rset.getString("idconsulta"));
		}
		
		cn.desconectar();
		return consultas;
	}*/

}
