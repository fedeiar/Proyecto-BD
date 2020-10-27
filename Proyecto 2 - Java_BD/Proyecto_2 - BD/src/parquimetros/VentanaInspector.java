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
import java.util.ArrayList;

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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Button;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class VentanaInspector extends VentanaUsuario {
	
    //atributos

	public static final int WIDTH = VentanaPrincipal.WIDTH;
    public static final int HEIGTH = VentanaPrincipal.HEIGTH;

    private JPanel jPanelInspector;
    private JTextField jTxPatente;
    private JTextField textField;
    private JLabel jLPatente, jLUbicacion;
    
    private String legajo;
    private ArrayList <String> listaPantentes;
    private JTable table;
    private JTable table_1;
    private JScrollPane scrollPane;
    //constructor
	public VentanaInspector(VentanaPrincipal vp, DBTable t) {
		super(vp,t);
	}
        
    //metodos

	protected void initGUI(){
        try{
        	super.initGUI();
            this.setTitle("Consultas Admin");
            listaPantentes = new ArrayList<String>();
            //arma los paneles
            ArmarPanelInspector();	
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void setLegajo(String l){
        legajo = l;
    }
    
    private void ArmarPanelInspector() {
    	getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

    	jPanelInspector = new JPanel();
        jPanelInspector.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(jPanelInspector);
		jPanelInspector.setLayout(null);
        
		jTxPatente = new JTextField();
		jTxPatente.setBounds(82, 41, 97, 28);
		jPanelInspector.add(jTxPatente);
		jTxPatente.setColumns(10);
		
		jLPatente = new JLabel("Ingrese pantente");
		jLPatente.setBounds(83, 14, 102, 27);
		jLPatente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jPanelInspector.add(jLPatente);
		
		JButton btnNewButton = new JButton("CARGAR PANTENTES");
		btnNewButton.setBounds(83, 105, 188, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jPanelInspector.add(btnNewButton);
		
		JButton jBDeletePatente = new JButton("Eliminar ultima patente");
		jBDeletePatente.setBounds(83, 73, 188, 27);
		jBDeletePatente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int numRow = table.getModel().getRowCount();
				if (numRow>0)
				((DefaultTableModel) table.getModel()).removeRow(numRow-1);
				
				
			}
		});
		jBDeletePatente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jPanelInspector.add(jBDeletePatente);
		
		JButton btnNewButton_3 = new JButton("INGRESAR");
		btnNewButton_3.setBounds(620, 57, 89, 23);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jPanelInspector.add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(540, 33, 169, 20);
		jPanelInspector.add(textField);
		textField.setColumns(10);
		
		jLUbicacion = new JLabel("Ingrese ubicacion");
		jLUbicacion.setBounds(540, 17, 168, 15);
		jLUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jPanelInspector.add(jLUbicacion);
		
		DBTable tabla_ubicaciones = new DBTable();
		tabla_ubicaciones.setBounds(10, 237, 769, 93);
		jPanelInspector.add(tabla_ubicaciones);
		tabla_ubicaciones.setSortEnabled(true);
		tabla_ubicaciones.setEditable(false);
		tabla_ubicaciones.setControlPanelVisible(false);
		
		DBTable tabla_ubicaciones_1 = new DBTable();
		tabla_ubicaciones_1.setBounds(10, 373, 769, 104);
		tabla_ubicaciones_1.setSortEnabled(true);
		tabla_ubicaciones_1.setEditable(false);
		tabla_ubicaciones_1.setControlPanelVisible(false);
		jPanelInspector.add(tabla_ubicaciones_1);
		
		
		
		JButton jBAgregar = new JButton("Agregar");
		jBAgregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jBAgregar.setBounds(185, 41, 86, 27);
		jPanelInspector.add(jBAgregar);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(281, 14, 220, 118);
		jPanelInspector.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new String[][] {
				
				
			},
			new String[] {
				"PANTENTES"
			}
		));
		
		JLabel lblSeleccioneUnParquimetro = new JLabel("Seleccione un parquimetro");
		lblSeleccioneUnParquimetro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSeleccioneUnParquimetro.setBounds(10, 203, 188, 27);
		jPanelInspector.add(lblSeleccioneUnParquimetro);
		jBAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String patente = jTxPatente.getText();
				int numCols = table.getModel().getColumnCount();
				String [] fila = new String [numCols];
				fila[0]=patente;
				((DefaultTableModel) table.getModel()).addRow(fila);
				

				 
			}
		});
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