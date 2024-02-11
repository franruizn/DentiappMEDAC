package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ControladorSQL;
import paqGUI.BotonPersonalizadoBean;
import java.awt.Font;

public class BuscarDoctorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ControladorSQL con = new ControladorSQL();
	@SuppressWarnings("unused")
	private ArrayList<String[]> consultas = new ArrayList<>();
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private JTextField txtPaciente;
	private String paciente;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarDoctorDialog dialog = new BuscarDoctorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BuscarDoctorDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 765, 447);
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
		
		
		
		
		
		JLabel lblBaja = new JLabel("Baja");
		lblBaja.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblBaja.setBounds(103, 74, 46, 32);
		contentPanel.add(lblBaja);
		
		JComboBox cmbDoctor = new JComboBox();
		cmbDoctor.setBounds(177, 106, 254, 41);
		contentPanel.add(cmbDoctor);
		
		txtPaciente = new JTextField();
		txtPaciente.setColumns(10);
		txtPaciente.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPaciente.setBackground(new Color(246, 246, 246));
		txtPaciente.setBounds(441, 106, 220, 41);
		contentPanel.add(txtPaciente);
		cmbDoctor.setModel(rellenarDatos("doctor", "nombre", modeloDatos,"1"));
        
		txtPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbDoctor.setModel(rellenarDatos("doctor", "nombre", modeloDatos,"1"));
				String textoBusqueda = txtPaciente.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbDoctor.getItemCount(); i++) {
					if (cmbDoctor.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbDoctor.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbDoctor.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbDoctor.setPopupVisible(true);
			}
		});
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaDoctorDialog dialog = new AltaDoctorDialog(cmbDoctor.getSelectedItem().toString());
				dialog.setModal(true);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
			}
		});
		btnAlta.setBounds(628, 380, 96, 34);
		contentPanel.add(btnAlta);
		
		JComboBox cmbBaja = new JComboBox();
		cmbBaja.setFont(new Font("SansSerif", Font.BOLD, 16));
		cmbBaja.setBounds(103, 106, 64, 41);
		contentPanel.add(cmbBaja);
		btnprsnlzdbnCerrar.setBounds(628, 11, 107, 51);
		contentPanel.add(btnprsnlzdbnCerrar);
		cmbBaja.addItem("SI");
		cmbBaja.addItem("NO");
		cmbBaja.setSelectedItem("SI");
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BorrarDoctorDialog dialog = new BorrarDoctorDialog(cmbDoctor.getSelectedItem().toString());
					dialog.setModal(true);
					dialog.setVisible(true);
					dialog.setLocationRelativeTo(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBaja.setBounds(628, 380, 96, 34);
		contentPanel.add(btnBaja);
		
		cmbBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(cmbBaja.getSelectedItem().equals("SI")) {
					cmbDoctor.setModel(rellenarDatos("doctor","nombre",modeloDatos,"1"));
					btnAlta.setVisible(true);
					btnAlta.setEnabled(true);
					btnBaja.setVisible(false);
					btnBaja.setEnabled(false);
				} else {
					cmbDoctor.setModel(rellenarDatos("doctor","nombre",modeloDatos,"0"));
					btnAlta.setVisible(false);
					btnAlta.setEnabled(false);
					btnBaja.setVisible(true);
					btnBaja.setEnabled(true);
				}
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDoctorDialog dialog = new ModificarDoctorDialog(cmbDoctor.getSelectedItem().toString());
				dialog.setVisible(true);
				dialog.setModal(true);
				dialog.setLocationRelativeTo(null);
			}
		});
		btnModificar.setBounds(56, 374, 96, 34);
		contentPanel.add(btnModificar);
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BuscarDoctorDialog.class.getResource("/fotos/buscar_doctor.png")));
		lblFondo.setBounds(0, 0, 765, 447);
		contentPanel.add(lblFondo);
		btnBaja.setVisible(false);
		btnBaja.setEnabled(false);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultComboBoxModel rellenarDatos(String nombreTabla, String campo,
			DefaultComboBoxModel<String> comboDatos, String condicion) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBoxWhere(nombreTabla, campo, condicion, "baja");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}
}
	


	


				
		
	


