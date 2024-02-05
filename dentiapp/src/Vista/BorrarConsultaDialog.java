package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import paqGUI.BotonPersonalizadoBean;

public class BorrarConsultaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private String fecha = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BorrarConsultaDialog dialog = new BorrarConsultaDialog();
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
	public BorrarConsultaDialog() throws SQLException {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		//setBounds(100, 100, 575, 457);
		setBounds(100, 100, 764, 447);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		BotonPersonalizadoBean btnprsnlzdbnCerrar = new BotonPersonalizadoBean();
		btnprsnlzdbnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblHora = new JLabel("HORA");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblHora.setBounds(33, 242, 228, 22);
		contentPanel.add(lblHora);
		
		JComboBox cmbHora = new JComboBox();
		cmbHora.setBounds(52, 287, 208, 22);
		contentPanel.add(cmbHora);
		btnprsnlzdbnCerrar.setBounds(650, 11, 85, 42);
		contentPanel.add(btnprsnlzdbnCerrar);
		
		cmbHora.addItem("16:00");
		cmbHora.addItem("17:00");
		cmbHora.addItem("18:00");
		cmbHora.addItem("19:00");
		cmbHora.addItem("20:00");
		cmbHora.addItem("21:00");
		
		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(52, 161, 301, 22);
		contentPanel.add(cmbPaciente);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(429, 150, 254, 42);
		contentPanel.add(dateChooser);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		Date currentDate = new Date();
		dateChooser.setMinSelectableDate(currentDate);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fecha = "'" + sdf.format(dateChooser.getDate()) + "'";
				String hora=cmbHora.getSelectedItem().toString();
				try {
					String idPaciente=con.selectWhereDoble("paciente", "idpaciente", "nombre",
							cmbPaciente.getSelectedItem().toString(),"hora",cmbHora.getSelectedItem().toString());
					int op = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar la consulta del paciente " + cmbPaciente.getSelectedItem() + "?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(op == 0) {
						con.borrarConsultaDoble(idPaciente, fecha, hora);
						}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(628, 418, 85, 21);
		contentPanel.add(btnAceptar);
		
		cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
		
		JLabel lblConsultas = new JLabel("CONSULTAS");
		lblConsultas.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblConsultas.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultas.setBounds(92, 112, 228, 22);
		contentPanel.add(lblConsultas);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblFecha.setBounds(445, 112, 228, 22);
		contentPanel.add(lblFecha);
		JLabel lblFondo = new JLabel("");
		
		lblFondo.setIcon(new ImageIcon(BorrarConsultaDialog.class.getResource("/fotos/borrar_consulta.PNG")));
		lblFondo.setBounds(0, 0, 765, 447);
		contentPanel.add(lblFondo);
		
	}
	
	public DefaultComboBoxModel rellenarDatos(String nombreTabla, String campo,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBox(nombreTabla, campo);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}
}
