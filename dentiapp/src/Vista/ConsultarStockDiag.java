package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.ControladorSQL;
import paqGUI.BotonPersonalizadoBean;

public class ConsultarStockDiag extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private DefaultTableModel modeloStock = new DefaultTableModel();
	private JTable tblStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsultarStockDiag dialog = new ConsultarStockDiag();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
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
	public ConsultarStockDiag() throws SQLException {
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
		btnprsnlzdbnCerrar_1.setBounds(827, 36, 85, 34);
		contentPanel.add(btnprsnlzdbnCerrar_1);
		
		tblStock = new JTable();
		tblStock.setBounds(313, 166, 336, 289);
		contentPanel.add(tblStock);
		tblStock.setModel(con.cargarDatos("stock", modeloStock));
		tblStock.getTableHeader().setVisible(false);
		tblStock.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblStock.getColumnModel().getColumn(1).setPreferredWidth(25);
		tblStock.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		JScrollPane scrStock = new JScrollPane(tblStock);
		scrStock.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrStock.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrStock.setBounds(313, 166, 336, 289);
		contentPanel.add(scrStock);
		
		JLabel lblTitulo = new JLabel("LISTADO DE STOCK");
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblTitulo.setBounds(313, 36, 344, 58);
		contentPanel.add(lblTitulo);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ConsultarStockDiag.class.getResource("/fotos/plantilla1azul.png")));
		lblFondo.setBounds(0, 0, 965, 594);
		contentPanel.add(lblFondo);
		
		
		
	}
}
