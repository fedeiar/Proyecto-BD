package parquimetros;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.sql.Statement;
import quick.dbtable.DBTable;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class VentanaPrincipal extends javax.swing.JFrame {
    
    //constantes
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;

    //atributos
    private VentanaAdministrador ventAdmin;
    private VentanaInspector ventInspector;
    private VentanaParquimetro ventParquimetro;
    private JDesktopPane jDesktopPane1;
    private JPanel jPanelLogin;

    private JMenuBar jMenuBar1;
    private JMenuItem jMenuSalir;
    private JSeparator jSeparator1;
    private JMenu jMenuGeneral;

    private DBTable tabla;

    private JTextField jTFUser;
    private JPasswordField jPPassword;
    private JLabel jLuser, jLpassword, jLConectar;
    private JButton jBingresar, jBConectar;
    private JButton jBNewButton;
    
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

        this.ventParquimetro = new VentanaParquimetro(this, tabla);
        this.ventParquimetro.setVisible(false);
        this.jDesktopPane1.add(this.ventParquimetro);
    }

    // metodos

    private void initGUI() {
        try {
        	
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            this.setTitle("Parquimetros");
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setSize(WIDTH, HEIGTH);
            
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

            pack();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void armarPanelLogin(){

        jPanelLogin = new JPanel();
        jPanelLogin.setBackground(Color.LIGHT_GRAY);
        jPanelLogin.setBounds(0, -13, 800, 613);
        jDesktopPane1.add(jPanelLogin);
        jPanelLogin.setLayout(null);
       
        jLuser = new JLabel("Usuario: ");
        jLuser.setBounds(245, 33, 45, 14);
        jPanelLogin.add(jLuser);

        jTFUser = new JTextField();
        jTFUser.setBounds(300, 30, 186, 20);
        jTFUser.setColumns(20);
        jPanelLogin.add(jTFUser);

        jLpassword = new JLabel("Password: ");
        jLpassword.setBounds(235, 64, 55, 14);
        jPanelLogin.add(jLpassword);

        jPPassword = new JPasswordField();
        jPPassword.setBounds(300, 61, 186, 20);
        jPPassword.setColumns(20);
        jPanelLogin.add(jPPassword);
        
        jBingresar = new JButton("Ingresar");
        jBingresar.setBounds(348, 92, 89, 23);
        jPanelLogin.add(jBingresar);
        jBingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String user = jTFUser.getText();
                char[] array_clave = jPPassword.getPassword();
                String clave = new String(array_clave);
                conectarBD(user,clave);
            }
        });

        jLConectar = new JLabel("Conectar tarjeta a parquimetro");
        jLConectar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jLConectar.setBounds(266, 273, 250, 20);
        jPanelLogin.add(jLConectar);
        
        jBConectar = new JButton("Conectar");
        jBConectar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jBConectar.setBounds(332, 303, 120, 29);
        jPanelLogin.add(jBConectar);
        jBConectar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                conectarTarjeta();
            }
        });

        jBNewButton = new JButton("Dark Mode");
        jBNewButton.setBounds(10, 19, 89, 23);
        jPanelLogin.add(jBNewButton);
        jBNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (jPanelLogin.getBackground().equals(Color.black))
        			notDarkMode();
        		else
            		darkMode();
        	}
        });
        
        //crea la tabla pero aún no la agrega a ningún frame
        tabla = new DBTable();
        tabla.setEditable(false);    
    }
    
    private void darkMode(){
    	jPanelLogin.setBackground(Color.BLACK);
    	jLuser.setForeground(Color.WHITE);
    	jLpassword.setForeground(Color.WHITE);
    	ventAdmin.darkMode();
    	ventInspector.darkMode();
    }
    
    private void notDarkMode(){
    	jPanelLogin.setBackground(Color.LIGHT_GRAY);
    	jLuser.setForeground(Color.BLACK);
    	jLpassword.setForeground(Color.BLACK);
    	ventAdmin.notDarkMode();	
    	ventInspector.notDarkMode();
    }

    private void conectarTarjeta(){
        try{
            String servidor = "localhost:3306";
            String baseDatos = "parquimetros"; 
            String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
            String driver = "com.mysql.cj.jdbc.Driver";
            String usuario = "parquimetro";
            String password = "parq";
            tabla.connectDatabase(driver, uriConexion, usuario, password);

            jTFUser.setText("");
            jPPassword.setText("");

            ventParquimetro.setMaximum(true);
            ventParquimetro.setVisible(true);
            jPanelLogin.setVisible(false);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error al conectarse a la base de datos. \n " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(PropertyVetoException e){
            e.printStackTrace();
        }
    }

    private void conectarBD(String usuario, String password){
        try{
            if(!usuario.equals("admin")){ // debería ser un inspector
        	    String servidor = "localhost:3306";
        	    String baseDatos = "parquimetros"; 
                String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
                Connection conexionDB = DriverManager.getConnection(uriConexion, "admin", "admin");
                Statement stmt = conexionDB.createStatement();
                String consulta = "SELECT legajo, password FROM Inspectores WHERE legajo = '" + usuario + "' AND password = md5('" + password + "') ";
                ResultSet rs = stmt.executeQuery(consulta);

                if (rs.next()){ // ya que es una sola fila, si hubo match es un sólo inspector valido, sino rs esta vacío y da false.
                    ventInspector.setLegajo(usuario);
                    usuario = "inspector";
                    password = "inspector";
                }

                rs.close();
                stmt.close();
                conexionDB.close();
                conexionDB = null;
            }
            
            cambiarVentana(usuario, password);
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


    private void cambiarVentana(String usuario, String password) throws SQLException, ClassNotFoundException{
        jTFUser.setText("");
        jPPassword.setText("");
        
        String servidor = "localhost:3306";
        String baseDatos = "parquimetros"; 
        String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
        String driver = "com.mysql.cj.jdbc.Driver";
        tabla.connectDatabase(driver, uriConexion, usuario, password);

        try{
            if(usuario.equals("admin")) {
                ventAdmin.setMaximum(true);
                ventAdmin.setVisible(true);
            } 
            if(usuario.equals("inspector")){
                ventInspector.setVisible(true);
                ventInspector.setMaximum(true);
            }
            jPanelLogin.setVisible(false);
            
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