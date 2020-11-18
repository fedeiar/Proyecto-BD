package parquimetros;

import quick.dbtable.DBTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import quick.dbtable.DBTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VentanaParquimetro extends VentanaUsuario{
    
    //atributos
    JPanel jPanelparquimetro;
    JLabel jLUbicaciones, jLParquimetros;

    DBTable tabla_ubicaciones, tabla_parquimetros;

    //constructor
    public VentanaParquimetro(VentanaPrincipal vp, DBTable t){
        super(vp, t);

    }

    protected void initGUI(){
        super.initGUI();
        this.setTitle("Ventana Parquimetro");

        armarPanelParquimetro();
    }

    

    private void armarPanelParquimetro(){
        jPanelparquimetro = new JPanel();
        jPanelparquimetro.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(jPanelparquimetro);
        jPanelparquimetro.setLayout(null);

        jLUbicaciones = new JLabel("Ubicaciones:");
        jLUbicaciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLUbicaciones.setBounds(20, 31, 85, 17);
        jPanelparquimetro.add(jLUbicaciones);

        tabla_ubicaciones = new DBTable();
        tabla_ubicaciones.setBounds(20, 53, 380, 182);
        tabla_ubicaciones.setSortEnabled(true);
        tabla_ubicaciones.setControlPanelVisible(false);
        tabla_ubicaciones.setEditable(false);
        jPanelparquimetro.add(tabla_ubicaciones);
        
        jLParquimetros = new JLabel("Parquimetros:");
        jLParquimetros.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLParquimetros.setBounds(20, 308, 95, 17);
        jPanelparquimetro.add(jLParquimetros);

        tabla_parquimetros = new DBTable();
        tabla_parquimetros.setBounds(20, 330, 380, 182);
        tabla_parquimetros.setSortEnabled(true);
        tabla_parquimetros.setControlPanelVisible(false);
        tabla_parquimetros.setEditable(false);
        jPanelparquimetro.add(tabla_parquimetros);
    }

    protected void thisComponentShown(ComponentEvent evt){
        super.conectarDBTable(tabla_ubicaciones);
        super.refrescarTabla(tabla_ubicaciones, "SELECT * FROM Ubicaciones");
        tabla_ubicaciones.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                mostrarParquimetros();
            }
        });

        super.conectarDBTable(tabla_parquimetros);
    }

    protected void thisComponentHidden(ComponentEvent evt){
        super.thisComponentHidden(evt);
        try {
            tabla_ubicaciones.close();
            tabla_parquimetros.close();
        } 
        catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
    }

    private void mostrarParquimetros(){
        int row = tabla_ubicaciones.getSelectedRow();
        int col_calle = tabla_ubicaciones.getColumnByHeaderName("calle").getModelIndex() - 1;
        int col_altura = tabla_ubicaciones.getColumnByHeaderName("altura").getModelIndex() - 1;
        String calle = tabla_ubicaciones.getValueAt(row, col_calle).toString();
        String altura = tabla_ubicaciones.getValueAt(row, col_altura).toString();

        super.refrescarTabla(tabla_parquimetros, "SELECT * FROM Parquimetros WHERE calle = '"+calle+"' AND altura = "+altura+"");
    }
}
