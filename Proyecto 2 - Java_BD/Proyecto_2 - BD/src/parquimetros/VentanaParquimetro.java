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

import java.sql.ResultSetMetaData;

import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VentanaParquimetro extends VentanaUsuario{
    
    //atributos
    JPanel jPanelparquimetro;
    JLabel jLUbicaciones, jLParquimetros, jLTarjetas;
    JButton jBConectar;

    DBTable tabla_ubicaciones, tabla_parquimetros, tabla_tarjetas;

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
        tabla_ubicaciones.setBounds(20, 53, 320, 182);
        tabla_ubicaciones.setSortEnabled(true);
        tabla_ubicaciones.setControlPanelVisible(false);
        tabla_ubicaciones.setEditable(false);
        jPanelparquimetro.add(tabla_ubicaciones);
        
        jLParquimetros = new JLabel("Parquimetros:");
        jLParquimetros.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLParquimetros.setBounds(20, 279, 95, 17);
        jPanelparquimetro.add(jLParquimetros);

        tabla_parquimetros = new DBTable();
        tabla_parquimetros.setBounds(20, 306, 350, 182);
        tabla_parquimetros.setSortEnabled(true);
        tabla_parquimetros.setControlPanelVisible(false);
        tabla_parquimetros.setEditable(false);
        jPanelparquimetro.add(tabla_parquimetros);

        tabla_tarjetas = new DBTable();
        tabla_tarjetas.setBounds(410, 53, 350, 182);
        tabla_tarjetas.setSortEnabled(true);
        tabla_tarjetas.setControlPanelVisible(false);
        tabla_tarjetas.setEditable(false);
        jPanelparquimetro.add(tabla_tarjetas);

        jLTarjetas = new JLabel("Tarjetas:");
        jLTarjetas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jLTarjetas.setBounds(410, 31, 85, 17);
        jPanelparquimetro.add(jLTarjetas);
        
        jBConectar = new JButton("Conectar Tarjeta");
        jBConectar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jBConectar.setBounds(516, 272, 155, 31);
        jPanelparquimetro.add(jBConectar);
        jBConectar.setEnabled(false);
        jBConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                conectarTarjeta();
			}
        });
    }

    protected void thisComponentShown(ComponentEvent evt){
        super.conectarDBTable(tabla_ubicaciones);
        super.refrescarTabla(tabla_ubicaciones, "SELECT * FROM Ubicaciones");
        establecerAnchoColumnas(tabla_ubicaciones, 90);
        tabla_ubicaciones.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                mostrarParquimetros();
            }
        });

        super.conectarDBTable(tabla_tarjetas);
        super.refrescarTabla(tabla_tarjetas, "SELECT * FROM Tarjetas");
        establecerAnchoColumnas(tabla_tarjetas, 80);

        super.conectarDBTable(tabla_parquimetros);
    }

    protected void thisComponentHidden(ComponentEvent evt){
        super.thisComponentHidden(evt);
        try {
            tabla_ubicaciones.close();
            tabla_parquimetros.close();
            tabla_tarjetas.close();
        }
        catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
    }

    private void conectarTarjeta(){
        try{
            int row_tarjeta = tabla_tarjetas.getSelectedRow();
            int row_parq = tabla_parquimetros.getSelectedRow();
            int col_tarjeta = tabla_tarjetas.getColumnByHeaderName("id_tarjeta").getModelIndex() - 1;
            int col_parq = tabla_parquimetros.getColumnByHeaderName("id_parq").getModelIndex() - 1;
            String id_tarjeta = tabla_tarjetas.getValueAt(row_tarjeta, col_tarjeta).toString();
            String id_parq = tabla_parquimetros.getValueAt(row_parq, col_parq).toString();

            Statement stmt = tabla.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("CALL conectar("+id_tarjeta+","+id_parq+")");
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rs.next()){ // ya que contiene solo 1 fila
                String mensaje = "";
                int cantCol = rsmd.getColumnCount();
                String nombreCol;
                for(int i = 1; i <= cantCol; i++){ // la primer columna es la 1
                    nombreCol = rsmd.getColumnLabel(i); 
                    mensaje += nombreCol +": "+ rs.getString(i) +"\n";
                }
                JOptionPane.showMessageDialog(this, mensaje, "Informacion de la Operacion", JOptionPane.INFORMATION_MESSAGE);
                super.refrescarTabla(tabla_tarjetas, tabla_tarjetas.getSelectSql());
                establecerAnchoColumnas(tabla_tarjetas, 80);
            }
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
        establecerAnchoColumnas(tabla_parquimetros, 80);
        jBConectar.setEnabled(true);
    }

    private void establecerAnchoColumnas(DBTable table, int width){
        for(int i = 0; i < table.getColumnCount(); i++){
            table.getColumn(i).setPreferredWidth(width);
        }
    }
}
