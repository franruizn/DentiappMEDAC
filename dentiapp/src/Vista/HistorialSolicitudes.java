package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTable;
import paqGUI.BotonPersonalizadoBean;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistorialSolicitudes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private DefaultTableModel modeloDatos = new DefaultTableModel();
	private String[] mostrarSoli = {"fk_iddoctor","material","cantidad","proveedor"};
	private JTable tblHistorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistorialSolicitudes dialog = new HistorialSolicitudes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public HistorialSolicitudes() throws SQLException {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 965, 594);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		BotonPersonalizadoBean btnprsnlzdbnCerrar_1 = new BotonPersonalizadoBean();
		btnprsnlzdbnCerrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnprsnlzdbnCerrar_1.setTexto("Salir");
		btnprsnlzdbnCerrar_1.setBounds(836, 534, 85, 34);
		contentPanel.add(btnprsnlzdbnCerrar_1);
		
		tblHistorial = new JTable();
		tblHistorial.setEnabled(false);
		tblHistorial.setBounds(313, 166, 336, 289);
		contentPanel.add(tblHistorial);
		
		JLabel lblTitulo = new JLabel("HISTORIAL");
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblTitulo.setBounds(313, 36, 344, 58);
		contentPanel.add(lblTitulo);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(HistorialSolicitudes.class.getResource("/fotos/plantilla1azul.png")));
		lblFondo.setBounds(0, 0, 965, 594);
		contentPanel.add(lblFondo);
		
		tblHistorial.setModel(con.cargarSolicitudesAceptadas("solicitudes", modeloDatos, mostrarSoli));
		tblHistorial.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblHistorial.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblHistorial.getColumnModel().getColumn(2).setPreferredWidth(25);
		tblHistorial.getColumnModel().getColumn(3).setPreferredWidth(100);
	}
}
