package parquimetros;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import quick.dbtable.DBTable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
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

import com.mysql.cj.xdevapi.Statement;



import java.awt.Font;

@SuppressWarnings("serial")
public class VentanaAdministrador extends javax.swing.JInternalFrame{

    //atributos
    private DBTable tabla;
    private DBTable tabla_tablasPresentes;
    private VentanaPrincipal ventPrincipal;

    private JPanel jPanelConsulta;
    private JTextArea jTAconsulta;
    private JButton jBejecutar, jBborrar;
    private JScrollPane scrConsulta;

    //constructor
    public VentanaAdministrador(VentanaPrincipal vp, DBTable t){
        super();
        ventPrincipal = vp;
        tabla = t;
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
            ArmaPanelConsulta();

            this.addComponentListener(new ComponentAdapter() {
                public void componentHidden(ComponentEvent evt) {
                   thisComponentHidden(evt);
                }

                public void componentShown(ComponentEvent evt){
                    thisComponentShown(evt);
                }
                
            });

            //crea la tabla que contendrá los nombres de todas las tablas.
            tabla_tablasPresentes = new DBTable();
            jPanelConsulta.add(tabla_tablasPresentes);
            tabla_tablasPresentes.setEditable(false);
            tabla_tablasPresentes.setBounds(472, 11, 308, 193);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void ArmaPanelConsulta(){
        
        jPanelConsulta = new JPanel();
        jPanelConsulta.setBounds(0, 0, 790, 560);
        getContentPane().add(jPanelConsulta);
        jPanelConsulta.setLayout(null);

        scrConsulta = new JScrollPane();
        scrConsulta.setBounds(10, 11, 461, 193);
        jPanelConsulta.add(scrConsulta);
        
        jTAconsulta = new JTextArea();
        scrConsulta.setViewportView(jTAconsulta);
        jTAconsulta.setText("SELECT * FROM Inspectores");
        jTAconsulta.setTabSize(3);
        jTAconsulta.setRows(10);
        jTAconsulta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        jTAconsulta.setColumns(80);
        jTAconsulta.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));

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
        jPanelConsulta.add(tabla);
        tabla.setBounds(0, 249, 790, 368);

        this.conectarTablaPresentes();
    }

    private void conectarTablaPresentes(){
        try{
            
            tabla_tablasPresentes.connectDatabase(tabla.getDatabaseDriver(), tabla.getJdbcUrl(), tabla.getUser(), tabla.getPassword());
           
            tabla_tablasPresentes.setSelectSql("SHOW TABLES");

            tabla_tablasPresentes.createColumnModelFromQuery();
        
            tabla_tablasPresentes.refresh();

            tabla_tablasPresentes.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent ev){
                    describe_table(ev);
                }
            });

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

    private void describe_table(MouseEvent ev){
        String nombre_tabla = this.tabla_tablasPresentes.getValueAt(tabla_tablasPresentes.getSelectedRow(), 0).toString();

        try {
            tabla.setSelectSql("SELECT * FROM " + nombre_tabla);
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

    private void desconectarBD(){
        try{
            this.setVisible(false);
            jPanelConsulta.remove(tabla);
            tabla.close();
            ventPrincipal.restaurarVentanaPrincipal();
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
