package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSQL;
import paqGUI.BotonPersonalizadoBean;

public class ValidarPedidoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblSoli;
	private ControladorSQL con = new ControladorSQL();
	private DefaultTableModel modeloDatos = new DefaultTableModel();
	private String[] mostrarSoli = {"idsolicitudes","fk_iddoctor","material","cantidad","proveedor"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ValidarPedidoDialog dialog = new ValidarPedidoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ValidarPedidoDialog() throws SQLException {
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
		
		JButton btnHistorial = new JButton("Historial");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HistorialSolicitudes hist = new HistorialSolicitudes();
					hist.setModal(true);
					hist.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnHistorial.setBounds(436, 444, 89, 23);
		contentPanel.add(btnHistorial);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Introduce el ID del pedido que deseas eliminar: ");
				try {
					con.eliminarFila("solicitudes", id);
					tblSoli.setModel(con.cargarSolicitudesPendientes("solicitudes", modeloDatos,mostrarSoli));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRechazar.setBounds(552, 444, 89, 23);
		contentPanel.add(btnRechazar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Introduce el ID del pedido: ");
				try {
					con.cambiarAceptado(id);
					tblSoli.setModel(con.cargarSolicitudesPendientes("solicitudes", modeloDatos,mostrarSoli));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(323, 444, 89, 23);
		contentPanel.add(btnAceptar);
		btnprsnlzdbnCerrar_1.setTexto("Salir");
		btnprsnlzdbnCerrar_1.setBounds(827, 36, 85, 34);
		contentPanel.add(btnprsnlzdbnCerrar_1);
		
		tblSoli = new JTable();
		tblSoli.setEnabled(false);
		tblSoli.setBounds(313, 166, 336, 289);
		contentPanel.add(tblSoli);
		
		JLabel lblTitulo = new JLabel("LISTADO DE SOLICITUD");
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblTitulo.setBounds(295, 37, 410, 58);
		contentPanel.add(lblTitulo);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ConsultarStockDiag.class.getResource("/fotos/plantilla1azul.png")));
		lblFondo.setBounds(0, 0, 965, 594);
		contentPanel.add(lblFondo);
		
		tblSoli.setModel(con.cargarSolicitudesPendientes("solicitudes", modeloDatos,mostrarSoli));
		tblSoli.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblSoli.getColumnModel().getColumn(1).setPreferredWidth(25);
		tblSoli.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblSoli.getColumnModel().getColumn(3).setPreferredWidth(25);
		tblSoli.getColumnModel().getColumn(4).setPreferredWidth(100);
		
	}

}
