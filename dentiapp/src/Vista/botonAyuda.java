package Vista;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.help.HelpSet;
import javax.help.HelpBroker;
import java.io.File;
import java.net.URL;

public class botonAyuda extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    botonAyuda frame = new botonAyuda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public botonAyuda() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JButton btnAyuda = new JButton("Ayuda");
        btnAyuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Carga el fichero de ayuda
                    File fichero = new File("src/help/help_set.hs");
                    URL hsURL = fichero.toURI().toURL();

                    // Crea el HelpSet y el HelpBroker
                    HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
                    HelpBroker hb = helpset.createHelpBroker();
                    // Muestra el menú de ayuda
                    hb.setDisplayed(true);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(btnAyuda, BorderLayout.NORTH);
    }
}
