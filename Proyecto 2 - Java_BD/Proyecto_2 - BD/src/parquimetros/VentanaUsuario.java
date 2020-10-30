package parquimetros;

import quick.dbtable.DBTable;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;


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
        this.getContentPane().setLayout(null);
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
}
