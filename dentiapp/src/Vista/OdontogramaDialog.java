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

public class OdontogramaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

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
		
		JButton btnd1 = new JButton("1");
		btnd1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd1.setBounds(314, 156, 41, 21);
		contentPanel.add(btnd1);
		
		JButton btnd2 = new JButton("2");
		btnd2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd2.setBounds(346, 194, 41, 21);
		contentPanel.add(btnd2);
		
		JButton btnd3 = new JButton("3");
		btnd3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd3.setBounds(356, 225, 41, 21);
		contentPanel.add(btnd3);
		
		JButton btnd4 = new JButton("4");
		btnd4.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd4.setBounds(377, 254, 41, 21);
		contentPanel.add(btnd4);
		
		JButton btnd5 = new JButton("5");
		btnd5.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd5.setBounds(377, 285, 41, 21);
		contentPanel.add(btnd5);
		
		JButton btnd6 = new JButton("6");
		btnd6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd6.setBounds(224, 156, 41, 21);
		contentPanel.add(btnd6);
		
		JButton btnd7 = new JButton("7");
		btnd7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd7.setBounds(185, 194, 41, 21);
		contentPanel.add(btnd7);
		
		JButton btnd8 = new JButton("8");
		btnd8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd8.setBounds(168, 225, 41, 21);
		contentPanel.add(btnd8);
		
		JButton btnd9 = new JButton("9");
		btnd9.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd9.setBounds(157, 254, 41, 21);
		contentPanel.add(btnd9);
		
		JButton btnd10 = new JButton("10");
		btnd10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd10.setBounds(141, 285, 41, 21);
		contentPanel.add(btnd10);
		
		JButton btnd11 = new JButton("11");
		btnd11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd11.setBounds(377, 326, 41, 21);
		contentPanel.add(btnd11);
		
		JButton btnd12 = new JButton("12");
		btnd12.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd12.setBounds(366, 363, 41, 21);
		contentPanel.add(btnd12);
		
		JButton btnd13 = new JButton("13");
		btnd13.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd13.setBounds(356, 391, 41, 21);
		contentPanel.add(btnd13);
		
		JButton btnd14 = new JButton("14");
		btnd14.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd14.setBounds(330, 422, 41, 21);
		contentPanel.add(btnd14);
		
		JButton btnd15 = new JButton("15");
		btnd15.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd15.setBounds(314, 449, 41, 21);
		contentPanel.add(btnd15);
		
		JButton btnd16 = new JButton("16");
		btnd16.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd16.setBounds(157, 316, 41, 21);
		contentPanel.add(btnd16);
		
		JButton btnd17 = new JButton("17");
		btnd17.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd17.setBounds(157, 349, 41, 21);
		contentPanel.add(btnd17);
		
		JButton btnd18 = new JButton("18");
		btnd18.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd18.setBounds(168, 380, 41, 21);
		contentPanel.add(btnd18);
		
		JButton btnd19 = new JButton("19");
		btnd19.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd19.setBounds(203, 411, 41, 21);
		contentPanel.add(btnd19);
		
		JButton btnd20 = new JButton("20");
		btnd20.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd20.setBounds(224, 449, 41, 21);
		contentPanel.add(btnd20);	
		
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/odontograma.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPanel.add(lblFondo);
	}
}
