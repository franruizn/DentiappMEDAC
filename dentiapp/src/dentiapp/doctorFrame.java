package dentiapp;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class doctorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorFrame frame = new doctorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public doctorFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("PEDIDO");
		btnNewButton_2.setBounds(354, 33, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("INICIO");
		btnNewButton.setBounds(49, 33, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("TRATAMIENTO");
		btnNewButton_1.setBounds(206, 33, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setIcon(new ImageIcon(doctorFrame.class.getResource("/dentiapp/ventana_doctor.PNG")));
		lblImagenFondo.setBounds(0, 0, 935, 592);
		contentPane.add(lblImagenFondo);
	}
}
