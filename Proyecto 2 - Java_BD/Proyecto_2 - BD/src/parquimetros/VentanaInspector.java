package parquimetros;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import quick.dbtable.DBTable;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class VentanaInspector extends VentanaUsuario {
	
 	

    //atributos

	public static final int WIDTH = 800;
    public static final int HEIGTH = 600;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuSalir;
    private JSeparator jSeparator1;
    private JMenu jMenuGeneral;
    private JPanel jPanelInspector;
    private JTextField jTxPantente;
    private JTextField textField;
    private JLabel jLPatente, jLUbicacion;
	    

	
	public VentanaInspector(VentanaPrincipal vp, DBTable t) {
		super(vp,t);
	}
    
    
    // metodos

	protected void initGUI(){
        try{
        	super.initGUI();
            this.setTitle("Consultas Admin");

            //arma los paneles
            ArmarPanelInspector();	
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void ArmarPanelInspector() {

    	jPanelInspector = new JPanel();
        jPanelInspector.setBackground(Color.LIGHT_GRAY);
        jPanelInspector.setBounds(0, 0, 786, 571);
        getContentPane().add(jPanelInspector);
        jPanelInspector.setLayout(null);
        
		jTxPantente = new JTextField();
		jTxPantente.setBounds(107, 13, 96, 20);
		jPanelInspector.add(jTxPantente);
		jTxPantente.setColumns(10);
		
		jLPatente = new JLabel("PATENTE");
		jLPatente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLPatente.setBounds(41, 13, 56, 20);
		jPanelInspector.add(jLPatente);
		
		JButton btnNewButton = new JButton("CARGAR PANTENTES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(42, 100, 161, 23);
		jPanelInspector.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(125, 38, 78, 23);
		jPanelInspector.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar patente");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(42, 66, 161, 23);
		jPanelInspector.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("INGRESAR");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(678, 38, 89, 23);
		jPanelInspector.add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(598, 13, 169, 20);
		jPanelInspector.add(textField);
		textField.setColumns(10);
		
		jLUbicacion = new JLabel("UBICACI\u00D3N");
		jLUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jLUbicacion.setBounds(525, 15, 63, 15);
		jPanelInspector.add(jLUbicacion);
		
		DBTable tabla_ubicaciones = new DBTable();
		tabla_ubicaciones.setBounds(10, 237, 766, 93);
		jPanelInspector.add(tabla_ubicaciones);
		tabla_ubicaciones.setSortEnabled(true);
		tabla_ubicaciones.setEditable(false);
		tabla_ubicaciones.setControlPanelVisible(false);
		
		DBTable tabla_patentes = new DBTable();
		tabla_patentes.setBounds(213, 13, 302, 178);
		jPanelInspector.add(tabla_patentes);
		tabla_patentes.setSortEnabled(true);
		tabla_patentes.setEditable(false);
		tabla_patentes.setControlPanelVisible(false);
		
		DBTable tabla_ubicaciones_1 = new DBTable();
		tabla_ubicaciones_1.setSortEnabled(true);
		tabla_ubicaciones_1.setEditable(false);
		tabla_ubicaciones_1.setControlPanelVisible(false);
		tabla_ubicaciones_1.setBounds(10, 373, 766, 104);
		jPanelInspector.add(tabla_ubicaciones_1);
    }

    protected void thisComponentHidden(ComponentEvent evt){
        super.thisComponentHidden(evt);
    }
    
    protected void thisComponentShown(ComponentEvent evt){

    }
    
    public void darkMode(){
    	jPanelInspector.setBackground(getBackground().black);
    	jLPatente.setForeground(getForeground().white);
    	jLUbicacion.setForeground(getForeground().white);
    }

    public void notDarkMode(){
    	jPanelInspector.setBackground(getBackground().LIGHT_GRAY);
    	jLPatente.setForeground(getForeground().black);
    	jLUbicacion.setForeground(getForeground().black);
    }
}