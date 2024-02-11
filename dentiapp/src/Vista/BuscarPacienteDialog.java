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

public class BuscarPacienteDialog extends JDialog {

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
			BuscarPacienteDialog dialog = new BuscarPacienteDialog();
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
	public BuscarPacienteDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 565, 420);
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
		btnprsnlzdbnCerrar.setBounds(459, 25, 96, 50);
		contentPanel.add(btnprsnlzdbnCerrar);

        
		
		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.setBounds(113, 107, 221, 47);
		contentPanel.add(cmbPaciente);
		
		txtPaciente = new JTextField();
		txtPaciente.setColumns(10);
		txtPaciente.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtPaciente.setBackground(new Color(246, 246, 246));
		txtPaciente.setBounds(335, 104, 181, 49);
		contentPanel.add(txtPaciente);
		btnprsnlzdbnCerrar.setBounds(459, 25, 96, 50);
		contentPanel.add(btnprsnlzdbnCerrar);
		cmbPaciente.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
		
		JButton btnOdontograma = new JButton("Odontograma");
        btnOdontograma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					paciente = con.selectWhere("paciente", "dni", "nombre", cmbPaciente.getSelectedItem().toString())+"-"+cmbPaciente.getSelectedItem().toString();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                OdontogramaDialog dialog = new OdontogramaDialog(paciente);
                dialog.setModal(true);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
            }
        });
        btnOdontograma.setBounds(28, 370, 150, 40);
        contentPanel.add(btnOdontograma);
		
        JButton btnMostrarConsultas = new JButton("Historial del Paciente");
        btnMostrarConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					paciente = con.selectWhere("paciente", "dni", "nombre", cmbPaciente.getSelectedItem().toString())+"-"+cmbPaciente.getSelectedItem().toString();
					HistorialPacienteDialog dialog = new HistorialPacienteDialog(paciente);
	                dialog.setModal(true);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnMostrarConsultas.setBounds(200, 370, 161, 40);
        contentPanel.add(btnMostrarConsultas);
        
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
		
		JButton btnPagos = new JButton("Pagos");
		btnPagos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					paciente = con.selectWhere("paciente", "dni", "nombre", cmbPaciente.getSelectedItem().toString())+"-"+cmbPaciente.getSelectedItem().toString();
					HistorialPagosDialog dialog = new HistorialPagosDialog(paciente);
					dialog.setModal(true);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPagos.setBounds(387, 370, 150, 40);
		contentPanel.add(btnPagos);
		
	
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BorrarUsuarioDialog.class.getResource("/fotos/buscar_paciente.PNG")));
		lblFondo.setBounds(0, 0, 564, 421);
		contentPanel.add(lblFondo);
	
			
			
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	


	


				
		
	


