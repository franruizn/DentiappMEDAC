package Vista;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrarDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultComboBoxModel<String> modeloDocs = new DefaultComboBoxModel<String>();
	private ControladorSQL con = new ControladorSQL();
	private JTable tblDocs;
	private DefaultTableModel modeloTblDocs = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BorrarDoctorDialog dialog = new BorrarDoctorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public BorrarDoctorDialog() throws SQLException {
		setResizable(false);
		setBounds(100, 100, 575, 457);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JComboBox<?> cmbDoctores = new JComboBox<Object>();
		cmbDoctores.setBounds(108, 110, 399, 37);
		contentPanel.add(cmbDoctores);
		
		JButton btnAceptar = new JButton("Borrar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAceptar.setBackground(new Color(55, 4, 102));
		btnAceptar.setActionCommand("OK");
		btnAceptar.setBounds(364, 344, 157, 50);
		contentPanel.add(btnAceptar);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(BorrarDoctorDialog.class.getResource("/fotos/dialog_borrar_doctor.PNG")));
			lblFondo.setBounds(0, 0, 564, 421);
			contentPanel.add(lblFondo);
		}

		tblDocs = new JTable();
		tblDocs.setBounds(305, 166, 175, 152);
		contentPanel.add(tblDocs);
		cmbDoctores.setModel(rellenarCombo());
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionado = cmbDoctores.getSelectedItem().toString();
				String id;
				int op = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar el doctor " + cmbDoctores.getSelectedItem() + "?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == 0) {
					try {
						confirmarBorrar(seleccionado);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
	}

	public DefaultComboBoxModel rellenarCombo() throws SQLException {
		modeloTblDocs = con.cargarDatos("doctor", modeloTblDocs);
		tblDocs.setModel(modeloTblDocs);

		for (int i = 0; i < modeloTblDocs.getRowCount(); i++) {
			modeloDocs.addElement((String) modeloTblDocs.getValueAt(i, 3));
		}

		return modeloDocs;
	}
	
	public void confirmarBorrar(String seleccionado) throws SQLException {
		String id = null;
		for (int i = 0; i < tblDocs.getRowCount(); i++) {
			if(seleccionado.equals(tblDocs.getValueAt(i, 3))) {
				id = tblDocs.getValueAt(i, 0).toString();
			}
		}
		
		con.eliminarFila("doctor", id);
	}
}
