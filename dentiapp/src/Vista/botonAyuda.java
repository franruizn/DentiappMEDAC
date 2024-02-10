package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.awt.event.ActionEvent;

public class botonAyuda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					botonAyuda frame = new botonAyuda();
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
	public botonAyuda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Carga el fichero de ayuda
					File fichero = new File("src/help/help.hs");
					URL hsURL = fichero.toURI().toURL();
				 
					// Crea el HelpSet y el HelpBroker
					HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
					HelpBroker hb = helpset.createHelpBroker();
				 
					
				 
				} catch (Exception p) {
					
				}
			}
		});
		contentPane.add(btnNewButton);
	}

}
