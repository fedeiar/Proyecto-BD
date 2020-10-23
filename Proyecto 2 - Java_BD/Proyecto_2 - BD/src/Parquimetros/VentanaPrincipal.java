package parquimetros;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class VentanaPrincipal extends javax.swing.JFrame {
    
    //constantes
    
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;

    //atributos
    private VentanaAdministrador ventAdmin;

    private JDesktopPane jDesktopPane1;

    private JMenuBar jMenuBar1;
    private JMenuItem jMenuSalir;
    private JSeparator jSeparator1;
    private JMenuItem jMenuItemAdmin;
    private JMenu jMenuUsuarios;

    // constructor
    public VentanaPrincipal() {
        super();
        initGUI();

        this.ventAdmin = new VentanaAdministrador();
        this.ventAdmin.setVisible(false);
        this.jDesktopPane1.add(this.ventAdmin);
    }

    // metodos

    private void initGUI() {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        try {

            this.setTitle("Parquimetros");
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            jDesktopPane1 = new JDesktopPane();
            this.getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
            jDesktopPane1.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGTH));

            jMenuBar1 = new JMenuBar();
            setJMenuBar(jMenuBar1);

            jMenuUsuarios = new JMenu();
            jMenuBar1.add(jMenuUsuarios);
            jMenuUsuarios.setText("Usuarios");

            jMenuItemAdmin = new JMenuItem();
            jMenuUsuarios.add(jMenuItemAdmin);
            jMenuItemAdmin.setText("Ingresar como Administrador");
            jMenuItemAdmin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    menuItemAdminActionPerformed(evt);
                }
            });

            jSeparator1 = new JSeparator();
            jMenuUsuarios.add(jSeparator1);

            jMenuSalir = new JMenuItem();
            jMenuUsuarios.add(jMenuSalir);
            jMenuSalir.setText("Salir");
            jMenuSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    menuItemSalirActionPerformed(evt);
                }
            });

            this.setSize(WIDTH, HEIGTH);
            pack();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void menuItemAdminActionPerformed(ActionEvent evt) {
        try {
            this.ventAdmin.setMaximum(true);
        } 
        catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        this.ventAdmin.setVisible(true);
    }

    private void menuItemSalirActionPerformed(ActionEvent evt) {
        this.dispose();
    }

}