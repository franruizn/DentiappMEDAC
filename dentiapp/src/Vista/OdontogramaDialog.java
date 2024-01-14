package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JCheckBox chckbxAusencias = new JCheckBox("Ausencias");
		chckbxAusencias.setBounds(538, 315, 86, 21);
		contentPanel.add(chckbxAusencias);
		
		JCheckBox chckbxProtesis = new JCheckBox("Prótesis");
		chckbxProtesis.setBounds(538, 361, 86, 22);
		contentPanel.add(chckbxProtesis);
		
		JCheckBox chckbxSangrado = new JCheckBox("Sangrado");
		chckbxSangrado.setBounds(652, 362, 93, 21);
		contentPanel.add(chckbxSangrado);
		
		JTextArea txtaDescripcion = new JTextArea();
		txtaDescripcion.setBounds(744, 222, 119, 69);
		contentPanel.add(txtaDescripcion);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(847, 27, 85, 21);
		contentPanel.add(btnSalir);
		
		JCheckBox chckbxCaries = new JCheckBox("Caries");
		chckbxCaries.setBounds(652, 314, 93, 22);
		contentPanel.add(chckbxCaries);
		
		JCheckBox chckbxImplantes = new JCheckBox("Implantes");
		chckbxImplantes.setBounds(770, 315, 93, 22);
		contentPanel.add(chckbxImplantes);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(652, 435, 93, 21);
		contentPanel.add(btnGuardar);
		
		JLabel lblDescripcion = new JLabel("Descripción :");
		lblDescripcion.setBounds(674, 228, 60, 13);
		contentPanel.add(lblDescripcion);
		
		JCheckBox chckbxRayos = new JCheckBox("Rayos X");
		chckbxRayos.setBounds(770, 362, 93, 21);
		contentPanel.add(chckbxRayos);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblPaciente.setBounds(593, 175, 223, 37);
		contentPanel.add(lblPaciente);
		
		txtNumDiente = new JTextField();
		txtNumDiente.setBounds(593, 225, 34, 19);
		contentPanel.add(txtNumDiente);
		txtNumDiente.setColumns(10);
		
		JLabel lblNumDiente = new JLabel("nº Diente :");
		lblNumDiente.setBounds(538, 228, 52, 13);
		contentPanel.add(lblNumDiente);
		

		JLabel lblDatos = new JLabel("DATOS");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblDatos.setBounds(629, 132, 146, 33);
		contentPanel.add(lblDatos);
		
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/odontograma.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPanel.add(lblFondo);
		
		JButton btnd2 = new JButton("");
		btnd2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd2.setBounds(303, 207, 18, 21);
		contentPanel.add(btnd2);
		
		JButton btnd3 = new JButton("3");
		btnd3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd3.setBounds(313, 225, 18, 21);
		contentPanel.add(btnd3);
		
		JButton btnd4 = new JButton("4");
		btnd4.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd4.setBounds(330, 250, 18, 21);
		contentPanel.add(btnd4);
		
		JButton btnd5 = new JButton("5");
		btnd5.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd5.setBounds(336, 278, 18, 21);
		contentPanel.add(btnd5);
		
		JButton btnd6 = new JButton("6");
		btnd6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd6.setBounds(269, 194, 14, 21);
		contentPanel.add(btnd6);
		
		JButton btnd7 = new JButton("7");
		btnd7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd7.setBounds(249, 206, 16, 21);
		contentPanel.add(btnd7);
		
		JButton btnd8 = new JButton("8");
		btnd8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd8.setBounds(236, 225, 15, 21);
		contentPanel.add(btnd8);
		
		JButton btnd9 = new JButton("9");
		btnd9.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd9.setBounds(222, 251, 23, 21);
		contentPanel.add(btnd9);
		
		JButton btnd10 = new JButton("10");
		btnd10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd10.setBounds(218, 280, 20, 21);
		contentPanel.add(btnd10);
		
		JButton btnd11 = new JButton("11");
		btnd11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd11.setBounds(329, 324, 18, 21);
		contentPanel.add(btnd11);
		
		JButton btnd12 = new JButton("12");
		btnd12.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd12.setBounds(328, 349, 20, 21);
		contentPanel.add(btnd12);
		
		JButton btnd13 = new JButton("13");
		btnd13.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd13.setBounds(314, 370, 20, 21);
		contentPanel.add(btnd13);
		
		JButton btnd14 = new JButton("14");
		btnd14.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd14.setBounds(299, 384, 18, 21);
		contentPanel.add(btnd14);
		
		JButton btnd15 = new JButton("15");
		btnd15.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd15.setBounds(286, 387, 11, 21);
		contentPanel.add(btnd15);
		
		JButton btnd16 = new JButton("16");
		btnd16.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd16.setBounds(222, 324, 15, 21);
		contentPanel.add(btnd16);
		
		JButton btnd17 = new JButton("17");
		btnd17.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd17.setBounds(224, 351, 14, 21);
		contentPanel.add(btnd17);
		
		JButton btnd18 = new JButton("18");
		btnd18.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd18.setBounds(240, 370, 11, 21);
		contentPanel.add(btnd18);
		
		JButton btnd19 = new JButton("19");
		btnd19.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd19.setBounds(257, 383, 11, 21);
		contentPanel.add(btnd19);
		
		JButton btnd20 = new JButton("20");
		btnd20.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd20.setBounds(273, 387, 10, 21);
		contentPanel.add(btnd20);	

		// Establecemos el cursor de mano
		btnd2.setOpaque(false);
		btnd2.setBackground(new Color(0, 0, 0, 0));

		// Establecemos el cursor de mano
		btnd2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JButton btnd1 = new JButton("");
		btnd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			}
		});
		btnd1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd1.setBounds(285, 194, 18, 21);
		
		btnd1.setOpaque(false);
		btnd1.setBackground(new Color(0, 0, 0, 0));
		
		
		contentPanel.add(btnd1);
		

	}
}
