package parquimetros;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import quick.dbtable.DBTable;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class VentanaAdministrador extends javax.swing.JInternalFrame{

    //atributos
    private DBTable tabla;
    
    private JPanel jPanelLogin;
    private JTextField jTFUser;
    private JPasswordField jPWPassword;
    private JLabel jLuser, jLpassword;
    //constructor
    public VentanaAdministrador(){
        super();
        initGUI();
    }

    private void initGUI(){
        try{
            this.setPreferredSize(new Dimension(VentanaPrincipal.WIDTH, VentanaPrincipal.HEIGHT));
            this.setBounds(0, 0, VentanaPrincipal.WIDTH, VentanaPrincipal.HEIGHT);
            this.setVisible(true);
            BorderLayout thisLayout = new BorderLayout();
            this.setTitle("Consultas Admin");
            this.getContentPane().setLayout(thisLayout);
            this.setClosable(true);
            this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            this.setMaximizable(true);

            jPanelLogin = new JPanel();
            this.getContentPane().add(jPanelLogin, BorderLayout.CENTER);

            jLuser = new JLabel("Usuario: ");
            //jLuser.setBounds(new Rectangle(100,200,jLuser.getWidth(),jLuser.getHeight()));
            jPanelLogin.add(jLuser);


            jTFUser = new JTextField();
            jTFUser.setColumns(20);
            jPanelLogin.add(jTFUser);

            jLpassword = new JLabel("Password: ");
            jPanelLogin.add(jLpassword);

            jPWPassword = new JPasswordField();
            jPWPassword.setColumns(20);
            jPanelLogin.add(jPWPassword);

           

            //crea la tabla
            tabla = new DBTable();
            
            this.addComponentListener(new ComponentAdapter() {
                public void componentHidden(ComponentEvent evt) {
                   thisComponentHidden(evt);
                }
                public void componentShown(ComponentEvent evt) {
                   thisComponentShown(evt);
                }
            });


        } 
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void thisComponentHidden(ComponentEvent evt){
        this.desconectarBD();
    }

    private void desconectarBD(){
        try{
            tabla.close();
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private void thisComponentShown(ComponentEvent evt){
        this.conectarBD();
    }

    private void conectarBD(){

        //try{

        
            //Agrega la tabla al frame
            this.getContentPane().add(tabla, BorderLayout.CENTER);
            tabla.setEditable(false);

            String driver ="com.mysql.cj.jdbc.Driver";
        	String servidor = "localhost:3306";
        	String baseDatos = "parquimetros"; 
        	String usuario = "admin";
        	
        	
            String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
                                 
            //tabla.connectDatabase(driver, uriConexion, usuario, clave);

        /*}
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error al conectarse a la base de datos. \n " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }*/
    
}
}
