package parquimetros;

import quick.dbtable.DBTable;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.sql.Types;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public abstract class VentanaUsuario extends javax.swing.JInternalFrame{
    
    //atributos
    protected VentanaPrincipal ventPrincipal;
    protected DBTable tabla;

    public VentanaUsuario(VentanaPrincipal vp, DBTable t){
        super();
        ventPrincipal = vp;
        tabla = t;
        initGUI();
    }

    protected void initGUI(){
        this.setPreferredSize(new Dimension(VentanaPrincipal.WIDTH, VentanaPrincipal.HEIGTH));
        this.setBounds(0, 0, VentanaPrincipal.WIDTH, VentanaPrincipal.HEIGTH);
        this.setVisible(true);
        this.setClosable(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setMaximizable(true);
        this.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
        this.addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent evt) {
               thisComponentHidden(evt);
            }

            public void componentShown(ComponentEvent evt){
                thisComponentShown(evt);
            }
            
        });
    }

    protected abstract void thisComponentShown(ComponentEvent evt);

    protected void thisComponentHidden(ComponentEvent evt){
        try{
            this.setVisible(false);
            tabla.close();
            ventPrincipal.restaurarVentanaPrincipal();
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    protected void refrescarTabla(DBTable table, String consulta){
        try {
    	    table.setSelectSql(consulta);
            table.createColumnModelFromQuery();

    	    for (int i = 0; i < table.getColumnCount(); i++){	
                if (table.getColumn(i).getType()==Types.TIME){
                    table.getColumn(i).setType(Types.CHAR);  
                }

                if (table.getColumn(i).getType()==Types.DATE) {
                    table.getColumn(i).setDateFormat("dd/MM/YYYY");
                }
                System.out.println(i);
            }
            table.refresh();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), ex.getMessage() + "\n", "Error al ejecutar la consulta.", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void conectarDBTable(DBTable table){
        try{
            table.connectDatabase(tabla.getDatabaseDriver(), tabla.getJdbcUrl(), tabla.getUser(), tabla.getPassword());
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
