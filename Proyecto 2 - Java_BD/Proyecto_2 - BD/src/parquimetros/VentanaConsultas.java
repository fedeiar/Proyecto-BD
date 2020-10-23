package parquimetros;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;



//import java.sql.Types;
//import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import quick.dbtable.*;

@SuppressWarnings("serial")
public class VentanaConsultas extends javax.swing.JInternalFrame {
	private JPanel pnlConsulta;
	private JTextArea txtConsulta;
	private JButton botonBorrar;
	private JButton btnEjecutar;
	private DBTable tabla;    
	private JScrollPane scrConsulta;
	
	public VentanaConsultas() {
		super();
	    initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(800, 600));
	        this.setBounds(0, 0, 800, 600);
	        setVisible(true);
	        BorderLayout thisLayout = new BorderLayout();
	        this.setTitle("Consultas (Utilizando DBTable)");
	        getContentPane().setLayout(thisLayout);
	        this.setClosable(true);
	        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	        this.setMaximizable(true);
	        this.addComponentListener(new ComponentAdapter() {
	        
	        	public void componentHidden(ComponentEvent evt) {
	        		thisComponentHidden(evt);
	        	}
	        	public void componentShown(ComponentEvent evt) {
	        		thisComponentShown(evt);
	        	}
	        });
	         
	        pnlConsulta = new JPanel();
	        getContentPane().add(pnlConsulta, BorderLayout.NORTH);
	        
	        
	        scrConsulta = new JScrollPane();
	        pnlConsulta.add(scrConsulta);
	       
	        txtConsulta = new JTextArea();
	        scrConsulta.setViewportView(txtConsulta);
	        txtConsulta.setTabSize(3);
	        txtConsulta.setColumns(80);
	        txtConsulta.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
	        txtConsulta.setText("ACA VA LA CONSULTA");
	        txtConsulta.setFont(new java.awt.Font("Monospaced",0,12));
	        txtConsulta.setRows(10);
	        
	        btnEjecutar = new JButton();
	        pnlConsulta.add(btnEjecutar);
	        btnEjecutar.setText("Ejecutar");
	        btnEjecutar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent evt) {
	        		btnEjecutarActionPerformed(evt);
	            }
	        });
	            
	        botonBorrar = new JButton();
	        pnlConsulta.add(botonBorrar);
	        botonBorrar.setText("Borrar");            
	        botonBorrar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		txtConsulta.setText("");            			
	            }
	        });
	            
	        tabla = new DBTable();
	        getContentPane().add(tabla, BorderLayout.CENTER);           
	        tabla.setEditable(false);       
	          
		} catch (Exception e) {
			e.printStackTrace();
	      }
	}
	private void thisComponentShown(ComponentEvent evt) {
		//this.conectBD();
	}
	   
	private void thisComponentHidden(ComponentEvent evt) {
		//this.desconectBD();
	}

	private void btnEjecutarActionPerformed(ActionEvent evt) {
		this.refreshTable();      
	}
	
	private void refreshTable() {
		try {
			//ACA VAN LOS DATOS DE NUESTRA BASE DE DATOS
			tabla.refresh();
		} catch (SQLException ex){
	       
			System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
	                                       ex.getMessage() + "\n", 
	                                       "Error al ejecutar la consulta.",
	                                       JOptionPane.ERROR_MESSAGE);
	         
	      }
		
		
	}
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
