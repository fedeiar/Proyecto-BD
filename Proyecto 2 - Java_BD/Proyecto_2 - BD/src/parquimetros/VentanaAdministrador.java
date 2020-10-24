package parquimetros;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import quick.dbtable.DBTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import java.awt.TextField;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class VentanaAdministrador extends javax.swing.JInternalFrame{

    //atributos
    private DBTable tabla;
    
    private JPanel jPanelLogin;
    private JPanel jPanelConsulta;
    private JTextField jTFUser;
    private JPasswordField jPPassword;
    private JLabel jLuser, jLpassword;
    private JButton jBingresar;
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
            this.setClosable(true);
            this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            this.setMaximizable(true);
            this.getContentPane().setLayout(null);

            //arma los paneles
            armarPanelLogin();
            ArmaPanelConsulta();
            jPanelLogin.setVisible(true);
            jPanelConsulta.setVisible(false);

            this.addComponentListener(new ComponentAdapter() {
                public void componentHidden(ComponentEvent evt) {
                   thisComponentHidden(evt);
                }
                
            });
        } 
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void armarPanelLogin(){

        jPanelLogin = new JPanel();
        jPanelLogin.setBounds(0, 111, 790, 539);
        this.getContentPane().add(jPanelLogin);
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
        jBingresar.setBounds(390, 82, 89, 23);
        jPanelLogin.add(jBingresar);
            
        jBingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String user = jTFUser.getText();
                char[] array_clave = jPPassword.getPassword();
                String clave = new String(array_clave);
                conectarBD(user,clave);
            }
        });
    }

    private void ArmaPanelConsulta(){
        
        jPanelConsulta = new JPanel();
        jPanelConsulta.setBounds(0, 0, 790, 560);
        getContentPane().add(jPanelConsulta);
        jPanelConsulta.setLayout(null);
        jPanelConsulta.setVisible(false);

        JScrollPane scrConsulta = new JScrollPane();
        scrConsulta.setBounds(10, 11, 566, 193);
        jPanelConsulta.add(scrConsulta);

        JButton jBejecutar = new JButton("Ejecutar");
        jBejecutar.setBounds(10, 215, 89, 23);
        jPanelConsulta.add(jBejecutar);
        
        JButton jBborrar = new JButton("Borrar");
        jBborrar.setBounds(105, 215, 89, 23);
        jPanelConsulta.add(jBborrar);

        JTextArea txtConsulta = new JTextArea();
        txtConsulta.setText("SELECT t.fecha, t.nombre_batalla, b.nombre_barco, b.id, b.capitan, r.resultado \nFROM batallas t, resultados r, barcos b \nWHERE t.nombre_batalla = r.nombre_batalla \nAND r.nombre_barco = b.nombre_barco \nORDER BY t.fecha, t.nombre_batalla, b.nombre_barco");
        txtConsulta.setTabSize(3);
        txtConsulta.setRows(10);
        txtConsulta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtConsulta.setColumns(80);
        txtConsulta.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        scrConsulta.setViewportView(txtConsulta);

        //crea la tabla y la agrega al panel de consultas
        tabla = new DBTable();
        tabla.setEditable(false);
        tabla.setBounds(0, 249, 790, 368);
        jPanelConsulta.add(tabla);
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

    private void conectarBD(String usuario, String password){
        try{
            
            String driver = "com.mysql.cj.jdbc.Driver";
        	String servidor = "localhost:3306";
        	String baseDatos = "parquimetros"; 
            String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?serverTimezone=America/Argentina/Buenos_Aires";
                                 
            tabla.connectDatabase(driver, uriConexion, usuario, password);
            
            jPanelLogin.setVisible(false);
			jPanelConsulta.setVisible(true);
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
}
