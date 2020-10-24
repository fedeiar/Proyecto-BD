package parquimetros;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import quick.dbtable.DBTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

import java.awt.Font;

@SuppressWarnings("serial")
public class VentanaAdministrador extends javax.swing.JInternalFrame{

    //atributos
    private DBTable tabla;
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
        scrConsulta.setBounds(10, 11, 566, 193);
        jPanelConsulta.add(scrConsulta);

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

        jTAconsulta = new JTextArea();
        jTAconsulta.setText("SELECT * FROM Inspectores");
        jTAconsulta.setTabSize(3);
        jTAconsulta.setRows(10);
        jTAconsulta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        jTAconsulta.setColumns(80);
        jTAconsulta.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
        scrConsulta.setViewportView(jTAconsulta);

        jPanelConsulta.add(tabla);
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
        System.out.println("entre a component show?");
        tabla.setBounds(0, 249, 790, 368);
    }

    private void desconectarBD(){
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
