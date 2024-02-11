package Vista;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controlador.ControladorSQL;
import paqGUI.BotonPersonalizadoBean;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class AltaPacienteDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();
	private JTable tblPac;
	@SuppressWarnings("unused")
	private DefaultTableModel modeloTblDocs = new DefaultTableModel();
	private JTextField txtPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaPacienteDialog dialog = new AltaPacienteDialog("");
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
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AltaPacienteDialog(String nombre) {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 765, 454);
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
		
		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(177, 107, 266, 49);
		contentPanel.add(cmbPaciente);
		
		txtPaciente = new JTextField();
		txtPaciente.setColumns(10);
		txtPaciente.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPaciente.setBackground(new Color(246, 246, 246));
		txtPaciente.setBounds(441, 108, 157, 48);
		contentPanel.add(txtPaciente);
		btnprsnlzdbnCerrar.setBounds(40, 10, 96, 50);
		contentPanel.add(btnprsnlzdbnCerrar);
		cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
		txtPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
				String textoBusqueda = txtPaciente.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbPaciente.getItemCount(); i++) {
					if (cmbPaciente.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbPaciente.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbPaciente.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbPaciente.setPopupVisible(true);
			}
		});

		JButton btnAceptar = new JButton("DAR ALTA");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAceptar.setBackground(new Color(55, 4, 102));
		btnAceptar.setActionCommand("OK");
		btnAceptar.setBounds(576, 372, 157, 50);
		contentPanel.add(btnAceptar);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(BorrarDoctorDialog.class.getResource("/fotos/alta_paciente.PNG")));
			lblFondo.setBounds(0, 0, 764, 453);
			contentPanel.add(lblFondo);
		}

		tblPac = new JTable();
		tblPac.setBounds(305, 166, 175, 152);
		contentPanel.add(tblPac);
		btnAceptar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				String seleccionado = cmbPaciente.getSelectedItem().toString();
				String id;
				int op = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere dar de alta al paciente " + cmbPaciente.getSelectedItem() + "?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == 0) {
					try {
						con.cambiarAlta(seleccionado,"paciente","idpaciente");
						JOptionPane.showMessageDialog(null, "Alta dada con Exito",
								"Alta dada", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
						cmbPaciente.setModel(rellenarDatos("proveedor", "nombre", modeloDatos));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error al dar de Alta al paciente",
								"Error al dar de alta", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
						e1.printStackTrace();
					}
				}
			}

		});
		
		cmbPaciente.setSelectedItem(nombre);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel rellenarDatos(String nombreTabla, String campo,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBoxWhere(nombreTabla, campo, "1", "baja");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}

}
