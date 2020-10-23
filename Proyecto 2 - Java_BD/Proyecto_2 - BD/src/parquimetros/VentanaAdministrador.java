package parquimetros;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
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
    
    private JTextField jUser;
    private JTextField jPassword;
    
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
            BorderLayout thisLayout = new BorderLayout();
            this.setTitle("Consultas Admin");
            this.getContentPane().setLayout(thisLayout);
            this.setClosable(true);
            this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            this.setMaximizable(true);

            //crea la tabla y la agrega al frame con Jscrollpane y todo
            tabla = new DBTable();
            this.getContentPane().add(tabla, BorderLayout.CENTER);
            tabla.setEditable(false);

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
<<<<<<< HEAD

=======
>>>>>>> parent of d458fa7... USER:NACHO;PAS:123
        try{
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
<<<<<<< HEAD

=======
>>>>>>> parent of d458fa7... USER:NACHO;PAS:123
        }
    }
}
