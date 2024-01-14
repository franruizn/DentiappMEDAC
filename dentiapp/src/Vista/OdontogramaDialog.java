package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorSQL;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class OdontogramaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumDiente;
	private JButton[] listaBotones = new JButton[20];
	private JTextField txtPaciente;
	private ControladorSQL con = new ControladorSQL();
	private DefaultComboBoxModel modeloDatos = new DefaultComboBoxModel();
	private ArrayList<String[]> consultas = new ArrayList<>();
	protected int a;
	protected int b;
	protected int c;
	protected int d;
	protected int f;
	protected int g;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OdontogramaDialog dialog = new OdontogramaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OdontogramaDialog() {
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 953, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(678, 168, 46, 14);
		contentPanel.add(lblNombre);

		JComboBox cmbPaciente = new JComboBox();
		cmbPaciente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});
		cmbPaciente.setBounds(535, 186, 189, 22);
		contentPanel.add(cmbPaciente);
		cmbPaciente.setModel(rellenarDatosDoble("paciente", "nombre", "dni", modeloDatos));

		txtPaciente = new JTextField();
		txtPaciente.setBounds(730, 185, 128, 25);
		contentPanel.add(txtPaciente);
		txtPaciente.setColumns(10);

		txtPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				cmbPaciente.setModel(rellenarDatosDoble("paciente", "nombre", "dni", modeloDatos));
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

		JCheckBox chckbxAusencias = new JCheckBox("Ausencias");
		chckbxAusencias.setBounds(538, 315, 86, 21);
		contentPanel.add(chckbxAusencias);

		JCheckBox chckbxProtesis = new JCheckBox("Prótesis");
		chckbxProtesis.setBounds(538, 361, 86, 22);
		contentPanel.add(chckbxProtesis);

		JCheckBox chckbxSangrado = new JCheckBox("Sangrado");
		chckbxSangrado.setBounds(652, 362, 93, 21);
		contentPanel.add(chckbxSangrado);

		JTextArea txtaDescripcion = new JTextArea();
		txtaDescripcion.setText(" ");
		txtaDescripcion.setColumns(20);
		txtaDescripcion.setRows(20);
		txtaDescripcion.setBounds(730, 219, 128, 86);
		contentPanel.add(txtaDescripcion);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(847, 27, 85, 21);
		contentPanel.add(btnSalir);

		JCheckBox chckbxCaries = new JCheckBox("Caries");
		chckbxCaries.setBounds(652, 314, 93, 22);
		contentPanel.add(chckbxCaries);

		JCheckBox chckbxImplantes = new JCheckBox("Implantes");
		chckbxImplantes.setBounds(770, 315, 93, 22);
		contentPanel.add(chckbxImplantes);
		
		JCheckBox chckbxRayos = new JCheckBox("Rayos X");
		chckbxRayos.setBounds(770, 362, 93, 21);
		contentPanel.add(chckbxRayos);

		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAusencias.isSelected()) {
					 a=1;
				}else {
					a=0;
				}
				if(chckbxCaries.isSelected()) {
					b=1;
				}else {
					b=0;
				}
				if(chckbxImplantes.isSelected()) {
					c=1;
				}else {
					c=0;
				}
				if(chckbxProtesis.isSelected()) {
					d=1;
				}else {
					d=0;
				}
				if(chckbxSangrado.isSelected()) {
					f=1;
				}else {
					f=0;
				}
				if(chckbxRayos.isSelected()) {
					g=1;
				}else {
					g=0;
				}
				
				try {
					String[] id = cmbPaciente.getSelectedItem().toString().split("-");
					con.CambiarOdontograma(con.selectWhere("paciente","idpaciente","dni",id[0]), txtNumDiente.getText().toString(), txtaDescripcion.getText().toString(),a , b, c, d, f, g);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGuardar.setBounds(652, 435, 93, 21);
		contentPanel.add(btnGuardar);

		JLabel lblDescripcion = new JLabel("Descripción :");
		lblDescripcion.setBounds(652, 228, 82, 13);
		contentPanel.add(lblDescripcion);

		

		txtNumDiente = new JTextField();
		txtNumDiente.setBounds(603, 224, 34, 19);
		contentPanel.add(txtNumDiente);
		txtNumDiente.setColumns(10);

		JLabel lblNumDiente = new JLabel("nº Diente :");
		lblNumDiente.setBounds(538, 228, 65, 13);
		contentPanel.add(lblNumDiente);

		JLabel lblDatos = new JLabel("DATOS PACIENTE");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblDatos.setBounds(570, 134, 279, 33);
		contentPanel.add(lblDatos);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(adminFrame.class.getResource("/fotos/odontograma.PNG")));
		lblFondo.setBounds(0, 0, 954, 594);
		contentPanel.add(lblFondo);

		JButton btnd2 = new JButton("2");
		btnd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd2.setBounds(303, 207, 18, 21);
		contentPanel.add(btnd2);

		JButton btnd3 = new JButton("3");
		btnd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd3.setBounds(313, 225, 18, 21);
		contentPanel.add(btnd3);

		JButton btnd4 = new JButton("4");
		btnd4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 4;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd4.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd4.setBounds(330, 250, 18, 21);
		contentPanel.add(btnd4);

		JButton btnd5 = new JButton("5");
		btnd5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 5;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd5.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd5.setBounds(336, 278, 18, 21);
		contentPanel.add(btnd5);

		JButton btnd6 = new JButton("6");
		btnd6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 6;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd6.setBounds(269, 194, 14, 21);
		contentPanel.add(btnd6);

		JButton btnd7 = new JButton("7");
		btnd7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 7;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd7.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd7.setBounds(249, 206, 16, 21);
		contentPanel.add(btnd7);

		JButton btnd8 = new JButton("8");
		btnd8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 8;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd8.setBounds(236, 225, 15, 21);
		contentPanel.add(btnd8);

		JButton btnd9 = new JButton("9");
		btnd9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 9;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd9.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd9.setBounds(222, 251, 23, 21);
		contentPanel.add(btnd9);

		JButton btnd10 = new JButton("10");
		btnd10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 10;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd10.setBounds(218, 280, 20, 21);
		contentPanel.add(btnd10);

		JButton btnd11 = new JButton("11");
		btnd11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 11;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd11.setBounds(329, 324, 18, 21);
		contentPanel.add(btnd11);

		JButton btnd12 = new JButton("12");
		btnd12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 12;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd12.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd12.setBounds(328, 349, 20, 21);
		contentPanel.add(btnd12);

		JButton btnd13 = new JButton("13");
		btnd13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 13;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd13.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd13.setBounds(314, 370, 20, 21);
		contentPanel.add(btnd13);

		JButton btnd14 = new JButton("14");
		btnd14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 14;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd14.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd14.setBounds(299, 384, 18, 21);
		contentPanel.add(btnd14);

		JButton btnd15 = new JButton("15");
		btnd15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 15;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd15.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd15.setBounds(286, 387, 11, 21);
		contentPanel.add(btnd15);

		JButton btnd16 = new JButton("16");
		btnd16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 16;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd16.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd16.setBounds(222, 324, 15, 21);
		contentPanel.add(btnd16);

		JButton btnd17 = new JButton("17");
		btnd17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 17;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd17.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd17.setBounds(224, 351, 14, 21);
		contentPanel.add(btnd17);

		JButton btnd18 = new JButton("18");
		btnd18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 18;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd18.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd18.setBounds(240, 370, 11, 21);
		contentPanel.add(btnd18);

		JButton btnd19 = new JButton("19");
		btnd19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 19;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd19.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd19.setBounds(257, 383, 11, 21);
		contentPanel.add(btnd19);

		JButton btnd20 = new JButton("20");
		btnd20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 20;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}
		});
		btnd20.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd20.setBounds(273, 387, 10, 21);
		contentPanel.add(btnd20);

		// Establecemos el cursor de mano
		btnd2.setOpaque(false);
		btnd2.setBackground(new Color(0, 0, 0, 0));

		// Establecemos el cursor de mano
		btnd2.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JButton btnd1 = new JButton("1");
		btnd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				rellenarDatosDiente(cmbPaciente, i, txtaDescripcion, chckbxAusencias, chckbxProtesis, chckbxSangrado,
						chckbxCaries, chckbxImplantes, chckbxRayos);
			}

		});
		btnd1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnd1.setBounds(285, 194, 18, 21);

		arreglarBotones(btnd2, btnd3, btnd4, btnd5, btnd6, btnd7, btnd8, btnd9, btnd10, btnd11, btnd12, btnd13, btnd14,
				btnd15, btnd16, btnd17, btnd18, btnd19, btnd20, btnd1);

		contentPanel.add(btnd1);

	}

	private void arreglarBotones(JButton btnd2, JButton btnd3, JButton btnd4, JButton btnd5, JButton btnd6,
			JButton btnd7, JButton btnd8, JButton btnd9, JButton btnd10, JButton btnd11, JButton btnd12, JButton btnd13,
			JButton btnd14, JButton btnd15, JButton btnd16, JButton btnd17, JButton btnd18, JButton btnd19,
			JButton btnd20, JButton btnd1) {
		listaBotones[0] = btnd1;
		listaBotones[1] = btnd2;
		listaBotones[2] = btnd3;
		listaBotones[3] = btnd4;
		listaBotones[4] = btnd5;
		listaBotones[5] = btnd6;
		listaBotones[6] = btnd7;
		listaBotones[7] = btnd8;
		listaBotones[8] = btnd9;
		listaBotones[9] = btnd10;
		listaBotones[10] = btnd11;
		listaBotones[11] = btnd12;
		listaBotones[12] = btnd13;
		listaBotones[13] = btnd14;
		listaBotones[14] = btnd15;
		listaBotones[15] = btnd16;
		listaBotones[16] = btnd17;
		listaBotones[17] = btnd18;
		listaBotones[18] = btnd19;
		listaBotones[19] = btnd20;

		for (int i = 0; i < listaBotones.length; i++) {
			listaBotones[i].setOpaque(false);
			listaBotones[i].setBackground(new Color(0, 0, 0, 0));
		}
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

	public DefaultComboBoxModel rellenarDatosDoble(String nombreTabla, String campo, String campo2,
			DefaultComboBoxModel<String> comboDatos) {
		try {

			comboDatos = (DefaultComboBoxModel<String>) con.rellenarComboBoxDoble(nombreTabla, campo, campo2);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return comboDatos;
	}

	private void rellenarDatosDiente(JComboBox cmbPaciente, int i, JTextArea txtaDescripcion, JCheckBox chckbxAusencias,
			JCheckBox chckbxProtesis, JCheckBox chckbxSangrado, JCheckBox chckbxCaries, JCheckBox chckbxImplantes,
			JCheckBox chckbxRayosx) {
		try {
			String[] dni = cmbPaciente.getSelectedItem().toString().split("-");
			consultas = con.obtenerOdontograma(con.selectWhere("paciente", "idpaciente", "dni", dni[0]), i);
			txtNumDiente.setText(consultas.get(0)[0].toString());
			txtaDescripcion.setText(consultas.get(0)[1].toString());
			if (consultas.get(0)[2].toString().equals("1")) {
				chckbxAusencias.setSelected(true);
			} else {
				chckbxAusencias.setSelected(false);
			}
			if (consultas.get(0)[3].toString().equals("1")) {
				chckbxCaries.setSelected(true);
			} else {
				chckbxCaries.setSelected(false);
			}
			if (consultas.get(0)[4].toString().equals("1")) {
				chckbxImplantes.setSelected(true);
			} else {
				chckbxImplantes.setSelected(false);
			}
			if (consultas.get(0)[5].toString().equals("1")) {
				chckbxProtesis.setSelected(true);
			} else {
				chckbxProtesis.setSelected(false);
			}
			if (consultas.get(0)[6].toString().equals("1")) {
				chckbxSangrado.setSelected(true);
			} else {
				chckbxSangrado.setSelected(false);
			}
			if (consultas.get(0)[7].toString().equals("1")) {
				chckbxRayosx.setSelected(true);
			} else {
				chckbxRayosx.setSelected(false);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
