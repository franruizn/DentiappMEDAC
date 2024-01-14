package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class OdontogramaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumDiente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OdontogramaDialog dialog = new OdontogramaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OdontogramaDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 953, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDatos = new JLabel("DATOS");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblDatos.setBounds(629, 132, 146, 33);
		contentPanel.add(lblDatos);
		
		JLabel lblNumDiente = new JLabel("nº Diente :");
		lblNumDiente.setBounds(538, 228, 52, 13);
		contentPanel.add(lblNumDiente);
		
		JLabel lblDescripcion = new JLabel("Descripción :");
		lblDescripcion.setBounds(674, 228, 60, 13);
		contentPanel.add(lblDescripcion);
		
		txtNumDiente = new JTextField();
		txtNumDiente.setBounds(593, 225, 34, 19);
		contentPanel.add(txtNumDiente);
		txtNumDiente.setColumns(10);
		
		JTextArea txtaDescripcion = new JTextArea();
		txtaDescripcion.setBounds(744, 222, 119, 69);
		contentPanel.add(txtaDescripcion);
		
		JCheckBox chckbxAusencias = new JCheckBox("Ausencias");
		chckbxAusencias.setBounds(538, 315, 86, 21);
		contentPanel.add(chckbxAusencias);
		
		JCheckBox chckbxCaries = new JCheckBox("Caries");
		chckbxCaries.setBounds(652, 314, 93, 22);
		contentPanel.add(chckbxCaries);
		
		JCheckBox chckbxImplantes = new JCheckBox("Implantes");
		chckbxImplantes.setBounds(770, 315, 93, 22);
		contentPanel.add(chckbxImplantes);
		
		JCheckBox chckbxProtesis = new JCheckBox("Prótesis");
		chckbxProtesis.setBounds(538, 361, 86, 22);
		contentPanel.add(chckbxProtesis);
		
		JCheckBox chckbxSangrado = new JCheckBox("Sangrado");
		chckbxSangrado.setBounds(652, 362, 93, 21);
		contentPanel.add(chckbxSangrado);
		
		JCheckBox chckbxRayos = new JCheckBox("Rayos X");
		chckbxRayos.setBounds(770, 362, 93, 21);
		contentPanel.add(chckbxRayos);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(652, 435, 93, 21);
		contentPanel.add(btnGuardar);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPaciente.setBounds(593, 175, 223, 37);
		contentPanel.add(lblPaciente);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(847, 27, 85, 21);
		contentPanel.add(btnSalir);
		
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/odontograma.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPanel.add(lblFondo);
		
		JButton btnd5 = new JButton("5");
		btnd5.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd5.setBounds(330, 277, 25, 21);
		contentPanel.add(btnd5);
		
		JButton btnd9 = new JButton("9");
		btnd9.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd9.setBounds(224, 250, 24, 21);
		contentPanel.add(btnd9);
		
		JButton btnd16 = new JButton("16");
		btnd16.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd16.setBounds(207, 326, 41, 21);
		contentPanel.add(btnd16);
		
		JButton btnd13 = new JButton("13");
		btnd13.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd13.setBounds(314, 371, 20, 21);
		contentPanel.add(btnd13);
		
		JButton btnd10 = new JButton("10");
		btnd10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd10.setBounds(207, 277, 41, 21);
		contentPanel.add(btnd10);
		
		JButton btnd17 = new JButton("17");
		btnd17.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd17.setBounds(207, 348, 41, 21);
		contentPanel.add(btnd17);
		
		JButton btnd20 = new JButton("20");
		btnd20.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd20.setBounds(264, 391, 20, 21);
		contentPanel.add(btnd20);	
		
		JButton btnd18 = new JButton("18");
		btnd18.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd18.setBounds(223, 371, 34, 21);
		contentPanel.add(btnd18);
		
		JButton btnd2 = new JButton("2");
		btnd2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd2.setBounds(302, 206, 20, 21);
		contentPanel.add(btnd2);
		
		JButton btnd6 = new JButton("6");
		btnd6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd6.setBounds(264, 194, 20, 21);
		contentPanel.add(btnd6);
		
		JButton btnd4 = new JButton("4");
		btnd4.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd4.setBounds(326, 245, 20, 30);
		contentPanel.add(btnd4);
		
		JButton btnd15 = new JButton("15");
		btnd15.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd15.setBounds(285, 391, 20, 21);
		contentPanel.add(btnd15);
		
		JButton btnd7 = new JButton("7");
		btnd7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd7.setBounds(244, 206, 20, 21);
		contentPanel.add(btnd7);
		
		JButton btnd12 = new JButton("12");
		btnd12.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd12.setBounds(326, 348, 20, 21);
		contentPanel.add(btnd12);
		
		JButton btnd3 = new JButton("3");
		btnd3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd3.setBounds(314, 226, 20, 21);
		contentPanel.add(btnd3);
		
		JButton btnd14 = new JButton("14");
		btnd14.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd14.setBounds(302, 382, 12, 30);
		contentPanel.add(btnd14);
		
		JButton btnd1 = new JButton("1");
		btnd1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd1.setBounds(285, 194, 20, 21);
		contentPanel.add(btnd1);
		
		JButton btnd8 = new JButton("8");
		btnd8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd8.setBounds(230, 226, 34, 21);
		contentPanel.add(btnd8);
		
		JButton btnd11 = new JButton("11");
		btnd11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd11.setBounds(326, 326, 41, 21);
		contentPanel.add(btnd11);
		
		JButton btnd19 = new JButton("19");
		btnd19.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd19.setBounds(244, 391, 20, 21);
		contentPanel.add(btnd19);
	}
}
