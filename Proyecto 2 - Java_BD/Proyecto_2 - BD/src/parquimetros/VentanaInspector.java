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
    private JLabel jLPatente, jLSeleccioneUnParquimetro;
    private JTable table;
    private JScrollPane jSPScroll;
    private JButton jBCargarPatentes, jBDeletePatente, jBAgregar;
    
    private DBTable tabla_parquimetros, tabla_ubicaciones_1;

    private String legajo;
    private ArrayList <String> listaPatentes;
    
    //constructor
	public VentanaInspector(VentanaPrincipal vp, DBTable t) {
		super(vp,t);
	}
        
    //metodos

	protected void initGUI(){
        try{
        	super.initGUI();
            this.setTitle("Consultas Inspector");
            listaPatentes = new ArrayList<String>();
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
		
		jLPatente = new JLabel("Ingrese patente");
		jLPatente.setBounds(83, 14, 102, 27);
		jLPatente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jPanelInspector.add(jLPatente);
		
		jBCargarPatentes = new JButton("CARGAR PATENTES");
        jBCargarPatentes.setBounds(83, 105, 188, 27);
        jBCargarPatentes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jPanelInspector.add(jBCargarPatentes);
		jBCargarPatentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

			}
		});
		
		jBDeletePatente = new JButton("Eliminar ultima patente");
        jBDeletePatente.setBounds(83, 73, 188, 27);
        jBDeletePatente.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jPanelInspector.add(jBDeletePatente);
		jBDeletePatente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numRow = table.getModel().getRowCount();
				if (numRow>0)
				((DefaultTableModel) table.getModel()).removeRow(numRow-1);	
			}
		});
        
        jLSeleccioneUnParquimetro = new JLabel("Seleccione un parquimetro");
		jLSeleccioneUnParquimetro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLSeleccioneUnParquimetro.setBounds(10, 203, 188, 27);
        jPanelInspector.add(jLSeleccioneUnParquimetro);
        
		tabla_parquimetros = new DBTable();
		tabla_parquimetros.setBounds(10, 237, 769, 93);
		tabla_parquimetros.setSortEnabled(true);
        tabla_parquimetros.setControlPanelVisible(false);
        tabla_parquimetros.setEditable(false);
        jPanelInspector.add(tabla_parquimetros);

		tabla_ubicaciones_1 = new DBTable();
		tabla_ubicaciones_1.setBounds(10, 373, 769, 104);
		tabla_ubicaciones_1.setSortEnabled(true);
		tabla_ubicaciones_1.setEditable(false);
		tabla_ubicaciones_1.setControlPanelVisible(false);
		jPanelInspector.add(tabla_ubicaciones_1);
		
		jBAgregar = new JButton("Agregar");
		jBAgregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jBAgregar.setBounds(185, 41, 86, 27);
		jPanelInspector.add(jBAgregar);
		
		jSPScroll = new JScrollPane();
		jSPScroll.setEnabled(false);
		jSPScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jSPScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jSPScroll.setBounds(281, 14, 220, 118);
		jPanelInspector.add(jSPScroll);
		
		table = new JTable();
		jSPScroll.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new String[][] {
				
			},
			new String[] {
				"PATENTES"
			}
        ));
		
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
    
    private void conectarDBTable(DBTable table, String consulta){
        try{
            System.out.println(consulta);
            table.connectDatabase(super.tabla.getDatabaseDriver(), super.tabla.getJdbcUrl(), super.tabla.getUser(), super.tabla.getPassword());
            table.setSelectSql(consulta);
            table.createColumnModelFromQuery();
            table.refresh();
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

    protected void thisComponentHidden(ComponentEvent evt){
        super.thisComponentHidden(evt);
        try {
            tabla_parquimetros.close();
        } 
        catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
    }
    
    protected void thisComponentShown(ComponentEvent evt){
        this.conectarDBTable(tabla_parquimetros, "SELECT * from Parquimetros");
    }

    public void darkMode(){
    	jPanelInspector.setBackground(Color.BLACK);
    	jLPatente.setForeground(Color.WHITE);
    }

    public void notDarkMode(){
    	jPanelInspector.setBackground(Color.LIGHT_GRAY);
    	jLPatente.setForeground(Color.BLACK);
    }
}