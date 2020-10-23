package parquimetros;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JButton;

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
            this.setPreferredSize(new Dimension(VentanaPrincipal.WIDTH, VentanaPrincipal.HEIGTH));
            this.setBounds(0, 0, VentanaPrincipal.WIDTH, VentanaPrincipal.HEIGTH);
            this.setVisible(true);
            this.setTitle("Consultas Admin");
            this.getContentPane().setLayout(new BorderLayout());
            this.setClosable(true);
            this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            //this.setMaximizable(true);

            armarPanelLogin();
           

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

    private void armarPanelLogin(){
        
        jPanelLogin = new JPanel();
        this.getContentPane().add(jPanelLogin, BorderLayout.CENTER);
        jPanelLogin.setLayout(null);
       
        jLuser = new JLabel("Usuario: ");
        jLuser.setBounds(282, 267, 45, 14);
        jPanelLogin.add(jLuser);

        jTFUser = new JTextField();
        jTFUser.setBounds(352, 261, 186, 20);
        jTFUser.setColumns(20);
        jPanelLogin.add(jTFUser);

        jLpassword = new JLabel("Password: ");
        jLpassword.setBounds(272, 297, 55, 14);
        jPanelLogin.add(jLpassword);

        jPWPassword = new JPasswordField();
        jPWPassword.setBounds(352, 291, 186, 20);
        jPWPassword.setColumns(20);
        jPanelLogin.add(jPWPassword);
        
        JButton btnNewButton = new JButton("Ingresar");
        btnNewButton.setBounds(449, 322, 89, 23);
        jPanelLogin.add(btnNewButton);
        btnNewButton.setAction(a)

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
        // this.conectarBD();
    }

    private void conectarBD(){
        try{
            //Agrega la tabla al frame
            this.getContentPane().add(tabla, BorderLayout.CENTER);
            tabla.setEditable(false);

            String driver ="com.mysql.cj.jdbc.Driver";
        	String servidor = "localhost:3306";
        	String baseDatos = "parquimetros"; 
        	String usuario = "admin";

            String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
                                 
            tabla.connectDatabase(driver, uriConexion, usuario, clave);

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error al conectarse a la base de datos. \n " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
