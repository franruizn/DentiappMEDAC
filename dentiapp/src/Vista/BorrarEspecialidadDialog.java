package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrarEspecialidadDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtNombreBuscar;
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarEspecialidadDialog dialog = new BorrarEspecialidadDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setUndecorated(true);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BorrarEspecialidadDialog() {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		//setBounds(100, 100, 575, 457);
		setBounds(100, 100, 764, 447);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().setLayout(null);
		
		JComboBox cmbNombre = new JComboBox();
		cmbNombre.setBounds(56, 160, 142, 22);
		getContentPane().add(cmbNombre);
		
		cmbNombre.setModel(rellenarDatos("especialidad", "nombre", modeloDatos));
		txtNombreBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbNombre.setModel(rellenarDatos("paciente", "nombre", modeloDatos));
				String textoBusqueda = txtNombreBuscar.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbNombre.getItemCount(); i++) {
					if (cmbNombre.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbNombre.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbNombre.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbNombre.setPopupVisible(true);
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String idEspecialidad=con.selectWhere("especialidad", "idespecialidad", "nombre",
							cmbNombre.getSelectedItem().toString());
					int op = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere borrar la especialidad " + cmbNombre.getSelectedItem() + "?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(op == 0) {
						con.borrarId(idEspecialidad);
						}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(665, 413, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(566, 413, 89, 23);
		getContentPane().add(btnCancelar);
		
		txtNombreBuscar = new JTextField();
		txtNombreBuscar.setBounds(208, 161, 142, 20);
		getContentPane().add(txtNombreBuscar);
		txtNombreBuscar.setColumns(10);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BorrarConsultaDialog.class.getResource("/fotos/borrar_especialidad.PNG")));
		lblFondo.setBounds(0, 0, 765, 447);
		getContentPane().add(lblFondo);
		

		setContentPane(contentPanel);
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
