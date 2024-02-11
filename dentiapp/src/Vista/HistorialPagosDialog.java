package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import javax.swing.JTextArea;

public class HistorialPagosDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private DefaultTableModel modeloDatos = new DefaultTableModel();
	private String[] mostrarSoli = {"fk_iddoctor","material","cantidad","proveedor"};
	private String paciente = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HistorialPagosDialog dialog = new HistorialPagosDialog("");
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
	public HistorialPagosDialog(String paciente) throws SQLException {
		this.paciente = paciente; 
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
		
		JLabel lblTitulo = new JLabel("HISTORIAL");
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 35));
		lblTitulo.setBounds(381, 47, 193, 58);
		contentPanel.add(lblTitulo);
        
        JTextArea txtaHist = new JTextArea();
        txtaHist.setBounds(315, 162, 334, 299);
        txtaHist.setColumns(5);
        txtaHist.setRows(20);
		txtaHist.setEditable(false);
		
        JScrollPane scrHist = new JScrollPane(txtaHist);
        scrHist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrHist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrHist.setBounds(315, 162, 334, 299);

        contentPanel.add(scrHist);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(HistorialPagosDialog.class.getResource("/fotos/plantilla1azul.png")));
		lblFondo.setBounds(0, 0, 965, 594);
		contentPanel.add(lblFondo);
		rellenarTextArea(txtaHist);
	}
	
	public void rellenarTextArea(JTextArea txtaHist) throws SQLException {
		String texto = con.rellenarPagos(paciente);
		txtaHist.setText(texto);
	}
}
