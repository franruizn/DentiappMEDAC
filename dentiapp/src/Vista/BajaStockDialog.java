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

public class BajaStockDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ControladorSQL con = new ControladorSQL();
	private JTable tblDocs;
	private DefaultTableModel modeloTblDocs = new DefaultTableModel();
	private JTextField txtStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BajaStockDialog dialog = new BajaStockDialog();
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
	 * 
	 * @throws SQLException
	 */
	public BajaStockDialog() throws SQLException {
		setLocationRelativeTo(null);	
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 565, 419);
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
		
		JComboBox cmbStock = new JComboBox();
		cmbStock.setBounds(113, 107, 221, 47);
		contentPanel.add(cmbStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtStock.setBackground(new Color(246, 246, 246));
		txtStock.setBounds(335, 104, 181, 49);
		contentPanel.add(txtStock);
		btnprsnlzdbnCerrar.setBounds(459, 25, 96, 50);
		contentPanel.add(btnprsnlzdbnCerrar);
		cmbStock.setModel(rellenarDatos("stock", "nombre", modeloDatos));
		txtStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbStock.setModel(rellenarDatos("stock", "nombre", modeloDatos));
				String textoBusqueda = txtStock.getText().toLowerCase();

				// Filtrar los elementos del combo que coincidan con el texto de búsqueda

				List<String> elementosFiltrados = new ArrayList<>();
				for (int i = 0; i < cmbStock.getItemCount(); i++) {
					if (cmbStock.getItemAt(i).toString().toLowerCase().contains(textoBusqueda)) {
						elementosFiltrados.add(cmbStock.getItemAt(i).toString());
					}
				}

				// Actualizar los elementos del combo con los resultados filtrados

				cmbStock.setModel(new DefaultComboBoxModel<>(elementosFiltrados.toArray(new String[0])));
				cmbStock.setPopupVisible(true);
			}
		});

		JButton btnAceptar = new JButton("DAR BAJA");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAceptar.setBackground(new Color(55, 4, 102));
		btnAceptar.setActionCommand("OK");
		btnAceptar.setBounds(364, 344, 157, 50);
		contentPanel.add(btnAceptar);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(BajaStockDialog.class.getResource("/fotos/borrar_stock.PNG")));
			lblFondo.setBounds(0, 0, 564, 421);
			contentPanel.add(lblFondo);
		}

		tblDocs = new JTable();
		tblDocs.setBounds(305, 166, 175, 152);
		contentPanel.add(tblDocs);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionado = cmbStock.getSelectedItem().toString();
				String id;
				int op = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere dar de baja el producto " + cmbStock.getSelectedItem() + "?", "Confirmar baja", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(op == 0) {
					try {
						con.cambiarBaja(seleccionado,"stock","idstock");
						JOptionPane.showMessageDialog(null, "Baja dada con éxito",
								"Baja dada", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoOk.png")));
						cmbStock.setModel(rellenarDatos("proveedor", "nombre", modeloDatos));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error al dar de Baja al producto",
								"Error al dar de baja", JOptionPane.WARNING_MESSAGE,new ImageIcon(CrearDoctorDialog.class.getResource("/fotos/iconoNo.png")));
						e1.printStackTrace();
					}
				}
			}

		});
	}
	
	
	
	public DefaultComboBoxModel rellenarDatos(String nombreTabla, String campo,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBoxWhere(nombreTabla, campo, "0", "baja");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}
}
