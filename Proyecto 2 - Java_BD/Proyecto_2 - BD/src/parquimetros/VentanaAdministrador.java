package parquimetros;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import quick.dbtable.DBTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.sql.Types;

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
    
    private JPanel jPanelLogin, jPanelConsulta;
    private JTextField jTFUser;
    private JTextArea jTAconsulta;
    private JPasswordField jPPassword;
    private JLabel jLuser, jLpassword;
    private JButton jBingresar, jBejecutar, jBborrar;
    private JScrollPane scrConsulta;

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

            this.addComponentListener(new ComponentAdapter() {
                public void componentHidden(ComponentEvent evt) {
                   thisComponentHidden(evt);
                }

                public void componentShown(ComponentEvent evt){
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
        jPanelLogin.setBounds(0, 105, 790, 545);
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

        scrConsulta = new JScrollPane();
        scrConsulta.setBounds(10, 11, 566, 193);
        jPanelConsulta.add(scrConsulta);

        jBejecutar = new JButton("Ejecutar");
        jBejecutar.setBounds(10, 215, 89, 23);
        jPanelConsulta.add(jBejecutar);
        jBejecutar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                refrescarTabla();
            }
        });
        
        jBborrar = new JButton("Borrar");
        jBborrar.setBounds(105, 215, 89, 23);
        jPanelConsulta.add(jBborrar);
        jBborrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                jTAconsulta.setText("");
            }
        });

        jTAconsulta = new JTextArea();
        jTAconsulta.setText("SELECT * FROM Inspectores");
        jTAconsulta.setTabSize(3);
        jTAconsulta.setRows(10);
        jTAconsulta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        jTAconsulta.setColumns(80);
        jTAconsulta.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        scrConsulta.setViewportView(jTAconsulta);

        //crea la tabla y la agrega al panel de consultas
        tabla = new DBTable();
        tabla.setEditable(false);
        tabla.setBounds(0, 249, 790, 368);
        jPanelConsulta.add(tabla);
    }

    private void refrescarTabla(){
        try {

    	    tabla.setSelectSql(this.jTAconsulta.getText().trim());
            tabla.createColumnModelFromQuery();

    	    for (int i = 0; i < tabla.getColumnCount(); i++){

                if (tabla.getColumn(i).getType()==Types.TIME){
                    tabla.getColumn(i).setType(Types.CHAR);  
                }

                if (tabla.getColumn(i).getType()==Types.DATE) {
                    tabla.getColumn(i).setDateFormat("dd/MM/YYYY");
                }
            }  
    	    // actualizamos el contenido de la tabla.
            tabla.refresh();
        }
        catch (SQLException ex) {
            // en caso de error, se muestra la causa en la consola
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), ex.getMessage() + "\n", "Error al ejecutar la consulta.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void thisComponentHidden(ComponentEvent evt){
        this.desconectarBD();
    }

    private void thisComponentShown(ComponentEvent evt){
        //setea visibilidad de los paneles
        jPanelLogin.setVisible(true);
        jPanelConsulta.setVisible(false);
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
