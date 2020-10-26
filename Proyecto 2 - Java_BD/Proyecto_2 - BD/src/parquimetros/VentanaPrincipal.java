package parquimetros;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import quick.dbtable.DBTable;
import java.awt.Color;

@SuppressWarnings("serial")
public class VentanaPrincipal extends javax.swing.JFrame {
    
    //constantes
    
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;

    //atributos
    private VentanaAdministrador ventAdmin;
    private VentanaInspector ventInspector;
    private JDesktopPane jDesktopPane1;
    private JPanel jPanelLogin;

    private JMenuBar jMenuBar1;
    private JMenuItem jMenuSalir;
    private JSeparator jSeparator1;
    private JMenu jMenuGeneral;

    private DBTable tabla;

    private JTextField jTFUser;
    private JPasswordField jPPassword;
    private JLabel jLuser, jLpassword;
    private JButton jBingresar;
    private JButton jbtnNewButton;
    
    // constructor
    public VentanaPrincipal() {
        super();
        initGUI();

        armarPanelLogin();

        this.ventAdmin = new VentanaAdministrador(this, tabla);
        this.ventAdmin.setVisible(false);
        this.jDesktopPane1.add(this.ventAdmin);
        
        this.ventInspector = new VentanaInspector(this, tabla);
        this.ventInspector.setVisible(false);
        this.jDesktopPane1.add(this.ventInspector);
        
    }

    // metodos

    private void initGUI() {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        

            this.setTitle("Parquimetros");
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            jDesktopPane1 = new JDesktopPane();
            this.getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
            jDesktopPane1.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGTH));

            jMenuBar1 = new JMenuBar();
            this.setJMenuBar(jMenuBar1);

            jMenuGeneral = new JMenu();
            jMenuBar1.add(jMenuGeneral);
            jMenuGeneral.setText("Menu");

            jSeparator1 = new JSeparator();
            jMenuGeneral.add(jSeparator1);

            jMenuSalir = new JMenuItem();
            jMenuGeneral.add(jMenuSalir);
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

    private void armarPanelLogin(){

        jPanelLogin = new JPanel();
        jPanelLogin.setBackground(Color.LIGHT_GRAY);
        jPanelLogin.setBounds(0, -11, 800, 611);
        jDesktopPane1.add(jPanelLogin);
        jPanelLogin.setLayout(null);
       
        jLuser = new JLabel("Usuario: ");
        jLuser.setBounds(235, 23, 45, 14);
        jPanelLogin.add(jLuser);

        jTFUser = new JTextField();
        jTFUser.setBounds(290, 20, 186, 20);
        jTFUser.setColumns(20);
        jPanelLogin.add(jTFUser);

        jLpassword = new JLabel("Password: ");
        jLpassword.setBounds(225, 54, 55, 14);
        jPanelLogin.add(jLpassword);

        jPPassword = new JPasswordField();
        jPPassword.setBounds(290, 51, 186, 20);
        jPPassword.setColumns(20);
        jPanelLogin.add(jPPassword);
        
        jBingresar = new JButton("Ingresar");
        jBingresar.setBounds(387, 81, 89, 23);
        jPanelLogin.add(jBingresar);

        jbtnNewButton = new JButton("Dark Mode");
        jbtnNewButton.setBounds(10, 11, 89, 23);
        jbtnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if (jPanelLogin.getBackground().equals(getBackground().black))
        			notDarkMode();
        		else
            		darkMode();
        	}
        });
        
        
        jPanelLogin.add(jbtnNewButton);
        jBingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String user = jTFUser.getText();
                char[] array_clave = jPPassword.getPassword();
                String clave = new String(array_clave);
                conectarBD(user,clave);
            }
        });

        //crea la tabla pero aún no la agrega a ningún frame
        tabla = new DBTable();
        tabla.setEditable(false);    
    }
    
    
    private void darkMode(){
    	jPanelLogin.setBackground(getBackground().black);
    	jLuser.setForeground(getForeground().white);
    	jLpassword.setForeground(getForeground().white);
    	ventAdmin.darkMode();
    	ventInspector.darkMode();
    }
    
    
    private void notDarkMode(){
    	jPanelLogin.setBackground(getBackground().LIGHT_GRAY);
    	jLuser.setForeground(getForeground().black);
    	jLpassword.setForeground(getForeground().black);
    	ventAdmin.notDarkMode();	
    	ventInspector.notDarkMode();
    }

    private void conectarBD(String usuario, String password){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
        	String servidor = "localhost:3306";
        	String baseDatos = "parquimetros"; 
            String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
                                 
            tabla.connectDatabase(driver, uriConexion, usuario, password);
            
            cambiarVentana(usuario);
            
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error al conectarse a la base de datos. \n " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }

    }

    private void cambiarVentana(String usuario){
    	
    	
    		
	        jPanelLogin.setVisible(false);
	        jTFUser.setText("");
	        jPPassword.setText("");
	        try{
	            if(usuario.equals("admin")) {
	            	ventAdmin.setMaximum(true);
	                ventAdmin.setVisible(true);
	            } else {
	            	ventInspector.setVisible(true);
	            }
	        }
	        catch(PropertyVetoException e){
	            e.printStackTrace();
	        }
    	
    }

    public void restaurarVentanaPrincipal(){
        jPanelLogin.setVisible(true);
    }

    private void menuItemSalirActionPerformed(ActionEvent evt) {
        this.dispose();
    }

}