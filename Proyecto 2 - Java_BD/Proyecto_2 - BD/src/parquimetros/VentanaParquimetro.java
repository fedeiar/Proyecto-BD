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

public class VentanaParquimetro extends VentanaUsuario{
    
    //atributos

    //constructor
    public VentanaParquimetro(VentanaPrincipal vp, DBTable t){
        super(vp, t);

    }

    protected void initGUI(){
        super.initGUI();
        this.setTitle("Ventana Parquimetro");
    }

    protected void thisComponentShown(ComponentEvent evt){
        
    }
}
