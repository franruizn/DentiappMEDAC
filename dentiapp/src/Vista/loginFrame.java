package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

import Controlador.ConexionMySQL;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class loginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPass;
	private ConexionMySQL cn = new ConexionMySQL();

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
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
	public loginFrame() {
		// Declaración del aspecto del panel
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("LOGIN");
		
		//Indicamos que busque en la base de datos si el usuario existe
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.conectar();
					String user = txtUsuario.getText();
					String pass = String.valueOf(txtPass.getPassword());
					
					//Consulta a ejecutar
					String consulta = "SELECT rol FROM usuario WHERE idusuario = '" + user + "' AND pass = '"+ pass + "';";
					ResultSet rset = cn.ejecutarSelect(consulta);
					
					//Comprobamos si existen resultados, si no hay error y el tipo de rol de usuario
					if(rset.next()) {
						int rol = rset.getInt("rol");
						System.out.println(rol);
						if(rol == 1) {
							dispose();
							adminFrame aframe = new adminFrame();
							aframe.setVisible(true);
						} else {
							dispose();
							doctorFrame dframe = new doctorFrame();
							dframe.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Error al iniciar sesión - Los credenciales no son correctos", "Error al iniciar sesión", JOptionPane.WARNING_MESSAGE);
					}
					
					
				} catch(SQLException r) {
					System.out.println(r.getMessage());
				}
			}
		});
		
		
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 32));
		btnLogin.setBounds(390, 419, 197, 69);
		btnLogin.setBackground(new Color(55,4,102));
		btnLogin.setForeground(Color.WHITE);
		contentPane.add(btnLogin);
		
		txtUsuario = new JTextField();
		txtUsuario.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtUsuario.setBounds(390, 237, 197, 20);
		txtUsuario.setBackground(new Color(246,246,246));
		contentPane.add(txtUsuario);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(390, 338, 197, 20);
		txtPass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPass.setBackground(new Color(246,246,246));
		contentPane.add(txtPass);
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setIcon(new ImageIcon(loginFrame.class.getResource("/fotos/login2.png")));
		lblImagenFondo.setBounds(0, 0, 951, 592);
		contentPane.add(lblImagenFondo);
	}
}
