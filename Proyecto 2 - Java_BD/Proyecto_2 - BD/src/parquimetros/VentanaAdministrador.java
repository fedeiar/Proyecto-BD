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
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import java.awt.Color;

@SuppressWarnings("serial")
public class VentanaAdministrador extends VentanaUsuario{

    //atributos
    private DBTable tabla_tablasPresentes;
    private JTextPane jLSeleccion;
    private JPanel jPanelConsulta;
    private JTextArea jTAconsulta;
    private JButton jBejecutar, jBborrar;
    private JScrollPane scrConsulta;
    

    //constructor
    public VentanaAdministrador(VentanaPrincipal vp, DBTable t){
        super(vp,t);
    }

    protected void initGUI(){
        try{
            super.initGUI();
            this.setTitle("Consultas Admin");

            //arma los paneles
            ArmaPanelConsulta();
						
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void ArmaPanelConsulta(){
        getContentPane().setLayout(null);
        
        jPanelConsulta = new JPanel();
        jPanelConsulta.setBackground(Color.LIGHT_GRAY);
        jPanelConsulta.setBounds(0, 0, 790, 571);
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
    
        tabla.setBounds(10, 252, 770, 287);
			
        jPanelConsulta.add(tabla);
        
        //crea la tabla que contendrá los nombres de todas las tablas.
        tabla_tablasPresentes = new DBTable();
        tabla_tablasPresentes.setBounds(519, 22, 221, 182);
        tabla_tablasPresentes.setSortEnabled(true);
        tabla_tablasPresentes.setControlPanelVisible(false);
        tabla_tablasPresentes.setEditable(false);
        jPanelConsulta.add(tabla_tablasPresentes);

        jLSeleccion = new JTextPane();
        jLSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        jLSeleccion.setBackground(Color.LIGHT_GRAY);
        jLSeleccion.setEditable(false);
        jLSeleccion.setText("Seleccione una clase");
        jLSeleccion.setBounds(519, 0, 237, 34);
        jPanelConsulta.add(jLSeleccion);
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

    protected void thisComponentHidden(ComponentEvent evt){
        super.thisComponentHidden(evt);
        try {
            tabla_tablasPresentes.close();
            jPanelConsulta.remove(tabla);
        } 
        catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
    }

    protected void thisComponentShown(ComponentEvent evt){
        jPanelConsulta.add(tabla);
        tabla.setBounds(10, 252, 770, 287);
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
    	    	tabla.getColumn(i).setMinWidth(100);
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
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), ex.getMessage() + "\n", "Error al ejecutar la consulta.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void darkMode(){
    	getBackground();
        jPanelConsulta.setBackground(Color.black);
    	jLSeleccion.setBackground(Color.black);
    	jLSeleccion.setForeground(Color.white);
    }

    public void notDarkMode(){
    	jPanelConsulta.setBackground(Color.LIGHT_GRAY);
    	jLSeleccion.setForeground(Color.black);
    	jLSeleccion.setBackground(Color.LIGHT_GRAY);
    }

}
