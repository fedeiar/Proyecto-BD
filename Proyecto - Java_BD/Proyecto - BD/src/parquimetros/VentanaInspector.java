package parquimetros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import quick.dbtable.DBTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;


@SuppressWarnings("serial")
public class VentanaInspector extends VentanaUsuario {
	
    //atributos

	public static final int WIDTH = VentanaPrincipal.WIDTH;
    public static final int HEIGTH = VentanaPrincipal.HEIGTH;

    private JPanel jPanelInspector;
    private JTextField jTFPatente, jTFUsuarioActual;
    private JLabel jLPatente, jLSeleccionParquimetro, jLUsuarioActual, jLPatentesIngresadasInvalidas;
    private JTable jTtablaPatentes, jTtablaPatentesInvalidas;
    private JScrollPane jSPPatentes, jSPPatentesInvalidas;
    private JButton jBCargarPatentes, jBDeletePatente, jBAgregar;

    private DBTable tabla_parquimetros, tabla_multas;

    private String legajo;
    private String id_asociado_con;

    //constructor

	public VentanaInspector(VentanaPrincipal vp, DBTable t) {
        super(vp,t);
	}
        
    //metodos

	protected void initGUI(){
     
        super.initGUI();
        this.setTitle("Consultas Inspector");

        //arma los paneles
        ArmarPanelInspector();	
    }

    public void setLegajo(String l){
        legajo = l;
    }
    
    private void ArmarPanelInspector() {
    	jPanelInspector = new JPanel();
        jPanelInspector.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(jPanelInspector);
        jPanelInspector.setLayout(null);
        
        jTFUsuarioActual = new JTextField();
		jTFUsuarioActual.setEnabled(false);
		jTFUsuarioActual.setEditable(false);
		jTFUsuarioActual.setBounds(649, 18, 37, 19);
        jTFUsuarioActual.setColumns(10);
        jPanelInspector.add(jTFUsuarioActual);
		
		jLUsuarioActual = new JLabel("Usuario actual:");
		jLUsuarioActual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLUsuarioActual.setBounds(551, 14, 111, 27);
		jPanelInspector.add(jLUsuarioActual);
        
		jTFPatente = new JTextField();
		jTFPatente.setBounds(82, 41, 97, 28);
		jPanelInspector.add(jTFPatente);
		jTFPatente.setColumns(10);
		
		jLPatente = new JLabel("Ingrese patente");
		jLPatente.setBounds(83, 14, 102, 27);
		jLPatente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jPanelInspector.add(jLPatente);
		
		jBCargarPatentes = new JButton("CARGAR MULTAS");
        jBCargarPatentes.setBounds(83, 105, 188, 27);
        jBCargarPatentes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jPanelInspector.add(jBCargarPatentes);
		jBCargarPatentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
                boolean valido = validarInspector();
                if(valido){
                    ((DefaultTableModel) jTtablaPatentesInvalidas.getModel()).setRowCount(0); //vaciamos las patentes invalidas cuando cargamos de nuevo
                    generarMultas();
                }
			}
		});
		
		jBDeletePatente = new JButton("Eliminar ultima patente");
        jBDeletePatente.setBounds(83, 73, 188, 27);
        jBDeletePatente.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jPanelInspector.add(jBDeletePatente);
		jBDeletePatente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numRow = jTtablaPatentes.getModel().getRowCount();
				if (numRow>0)
				((DefaultTableModel) jTtablaPatentes.getModel()).removeRow(numRow-1);	
			}
		});
        
        jLSeleccionParquimetro = new JLabel("Seleccione un parquimetro");
		jLSeleccionParquimetro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLSeleccionParquimetro.setBounds(350, 154, 158, 27);
        jPanelInspector.add(jLSeleccionParquimetro);
        
		tabla_parquimetros = new DBTable();
		tabla_parquimetros.setBounds(350, 183, 379, 129);
		tabla_parquimetros.setSortEnabled(true);
        tabla_parquimetros.setControlPanelVisible(false);
        tabla_parquimetros.setEditable(false);
        jPanelInspector.add(tabla_parquimetros);

		tabla_multas = new DBTable();
		tabla_multas.setBounds(10, 331, 769, 198);
        tabla_multas.setSortEnabled(true);
        tabla_multas.setControlPanelVisible(false);
		tabla_multas.setEditable(false);
		jPanelInspector.add(tabla_multas);
        
		jBAgregar = new JButton("Agregar");
		jBAgregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jBAgregar.setBounds(185, 41, 86, 27);
		jPanelInspector.add(jBAgregar);
		jBAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                String patente = jTFPatente.getText();
                boolean duplicado = controlarDuplicados(jTtablaPatentes, patente);
                if(!duplicado){
                    String []fila = {patente};
                    ((DefaultTableModel) jTtablaPatentes.getModel()).addRow(fila);
                }

			}
        });
        
		jSPPatentes = new JScrollPane();
		jSPPatentes.setEnabled(false);
		jSPPatentes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jSPPatentes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jSPPatentes.setBounds(281, 14, 220, 129);
		jPanelInspector.add(jSPPatentes);

		jTtablaPatentes = new JTable();
		jSPPatentes.setViewportView(jTtablaPatentes);
		jTtablaPatentes.setModel(new DefaultTableModel(
			new String[][] { },
			new String[] { "PATENTES" }
        ));
		
		jLPatentesIngresadasInvalidas = new JLabel("Patentes Ingresadas Invalidas");
		jLPatentesIngresadasInvalidas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLPatentesIngresadasInvalidas.setBounds(72, 154, 206, 27);
        jPanelInspector.add(jLPatentesIngresadasInvalidas);
        
        jSPPatentesInvalidas = new JScrollPane();
		jSPPatentesInvalidas.setEnabled(false);
		jSPPatentesInvalidas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jSPPatentesInvalidas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jSPPatentesInvalidas.setBounds(72, 185, 220, 129);
		jPanelInspector.add(jSPPatentesInvalidas);

        jTtablaPatentesInvalidas = new JTable();
		jSPPatentesInvalidas.setViewportView(jTtablaPatentesInvalidas);
		jTtablaPatentesInvalidas.setModel(new DefaultTableModel(
			new String[][] { },
			new String[] { "PATENTES INVALIDAS" }
        ));
    }

    private boolean controlarDuplicados(JTable table, String patente){
        int filas = jTtablaPatentes.getModel().getRowCount();
        String patente_en_tabla;
        boolean duplicado = false;
        for(int i = 0; i < filas && !duplicado; i++){
            patente_en_tabla = table.getModel().getValueAt(i, 0).toString();
            duplicado = patente.equals(patente_en_tabla);
        }
        return duplicado;
    }

    private void generarMultas(){
        String patente_anotada;

        String calle_parquimetro = this.tabla_parquimetros.getValueAt(this.tabla_parquimetros.getSelectedRow(), tabla_parquimetros.getColumnByHeaderName("calle").getModelIndex() - 1).toString();
        calle_parquimetro = calle_parquimetro.toUpperCase().trim();

        String altura_parquimetro = this.tabla_parquimetros.getValueAt(this.tabla_parquimetros.getSelectedRow(), tabla_parquimetros.getColumnByHeaderName("altura").getModelIndex() - 1).toString();
        altura_parquimetro = altura_parquimetro.toUpperCase().trim();

        boolean pago_parquimetro = false;

        try{
            Statement stmt_estacionados = tabla_multas.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt_estacionados.execute("CREATE TEMPORARY TABLE Temp(fecha DATE, hora TIME, patente VARCHAR(6), id_asociado_con INT UNSIGNED)");
            ResultSet rs_estacionados = stmt_estacionados.executeQuery("SELECT * FROM Estacionados");

            Statement stmt_patentes = tabla_multas.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs_patentes = stmt_patentes.executeQuery("SELECT patente FROM automoviles");

            boolean automovil_valido;
            int filas = jTtablaPatentes.getModel().getRowCount();
            for(int i = 0; i < filas; i++){
                patente_anotada = jTtablaPatentes.getModel().getValueAt(i, 0).toString().toUpperCase().trim();
                automovil_valido = verificarPatente(rs_patentes, patente_anotada);
                if(automovil_valido){
                    pago_parquimetro = verificarSiPagoParquimetro(rs_estacionados, patente_anotada, calle_parquimetro, altura_parquimetro);
                    
                    if(!pago_parquimetro){
                        registrarMulta(patente_anotada);
                    }
                    pago_parquimetro = false;
                }
                else{
                    String []s = {patente_anotada};
                    ((DefaultTableModel) jTtablaPatentesInvalidas.getModel()).addRow(s);
                }
            }
            mostrarMultas();
            ((DefaultTableModel) jTtablaPatentes.getModel()).setRowCount(0); //vaciamos la tabla de patentes

            stmt_estacionados.close();
            rs_estacionados.close();
            rs_patentes.close();
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private boolean verificarSiPagoParquimetro(ResultSet rs, String patente_anotada, String calle_parq, String altura_parq) throws SQLException{
        String patente_estacionada;
        String calle_estacionada;
        String altura_estacionada;
        boolean pago = false;

        while(rs.next() && !pago){
            patente_estacionada = rs.getString("patente").toUpperCase().trim();
            calle_estacionada = rs.getString("calle").toUpperCase().trim();
            altura_estacionada = rs.getString("altura").toUpperCase().trim();
            if(patente_anotada.equals(patente_estacionada) && calle_estacionada.equals(calle_parq) && altura_estacionada.equals(altura_parq)){
                pago = true;
            }
        }
        rs.beforeFirst();
        return pago;
    }

    private boolean verificarPatente(ResultSet rs, String patente) throws SQLException{
        boolean es_valida = false;
        rs.beforeFirst();
        while(rs.next() && !es_valida){
            String patente_en_BD = rs.getString("patente");
            es_valida = patente.equals(patente_en_BD);
        }
        return es_valida;
    }

    private void registrarMulta(String patente) throws SQLException{
        Statement stmt = tabla_multas.getConnection().createStatement();
        java.sql.Date fecha = Fecha.getFechaActualSQL();
        java.sql.Time hora = Fecha.getHoraActualSQL();
        stmt.execute("INSERT INTO Multa(fecha, hora, patente, id_asociado_con) VALUES('"+fecha+"', '"+hora+"', '" +patente+ "', '" +id_asociado_con+ "')");
        stmt.execute("INSERT INTO Temp(fecha, hora, patente, id_asociado_con) VALUES('"+fecha+"', '"+hora+"', '" +patente+ "', '" +id_asociado_con+ "')" );
        stmt.close();
    }

    private void mostrarMultas() throws SQLException{
        String consulta = "SELECT numero, fecha, hora, calle, altura, patente, legajo " +
                          "FROM (Temp NATURAL JOIN Multa) NATURAL JOIN asociado_con";
        this.refrescarTabla(tabla_multas, consulta);
        Statement stmt = tabla_multas.getConnection().createStatement();
        stmt.execute("DROP TABLE Temp");
        stmt.close();
    }
    
    private boolean validarInspector(){
        boolean valido = false;
        try{
            String calle = this.tabla_parquimetros.getValueAt(this.tabla_parquimetros.getSelectedRow(), tabla_parquimetros.getColumnByHeaderName("calle").getModelIndex() - 1).toString();
            String altura = this.tabla_parquimetros.getValueAt(this.tabla_parquimetros.getSelectedRow(), tabla_parquimetros.getColumnByHeaderName("altura").getModelIndex() - 1).toString();
            String dia = Fecha.getDiaActual();
            char turno = Fecha.getTurno();
            Statement stmt = tabla.getConnection().createStatement();
            String consulta = "SELECT * FROM Asociado_con " + 
                              "WHERE legajo = "+ this.legajo +" AND calle = '"+ calle +"' AND altura = "+ altura +" AND " +
                              "dia = '"+ dia +"' AND turno = '"+ turno +"'";
            ResultSet rs = stmt.executeQuery(consulta);
            if(rs.next()){ //si el inspector tiene asociada la ubicación, tendríamos una sola tupla. Sino 0.
                id_asociado_con = rs.getString("id_asociado_con").toString();
                valido = true;
                registrarAccesoInspector(stmt);
            }
            else{
                JOptionPane.showMessageDialog(this,"Error: No está autorizado a labrar multas en la ubicación", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            stmt.close();
            rs.close();
        }
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return valido;
    }

    private void registrarAccesoInspector(Statement stmt) throws SQLException{
        String id_parq = this.tabla_parquimetros.getValueAt(this.tabla_parquimetros.getSelectedRow(), tabla_parquimetros.getColumnByHeaderName("id_parq").getModelIndex() - 1).toString();
        java.sql.Date fecha = Fecha.getFechaActualSQL();
        java.sql.Time hora = Fecha.getHoraActualSQL();
        String consulta = "INSERT INTO Accede(legajo, id_parq, fecha, hora) " + 
                          "VALUES("+legajo+", "+id_parq+", '"+fecha+"', '"+hora+"')" ;
        stmt.execute(consulta);
    }

    protected void thisComponentHidden(ComponentEvent evt){
        super.thisComponentHidden(evt);
        try {
            tabla_parquimetros.close();
            tabla_multas.close();
        } 
        catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
    }
    
    protected void thisComponentShown(ComponentEvent evt){
        this.conectarDBTable(tabla_parquimetros);
        this.refrescarTabla(tabla_parquimetros, "SELECT * from Parquimetros");
        jTFUsuarioActual.setText(legajo);

        this.conectarDBTable(tabla_multas);
    }

    public void darkMode(){
    	jPanelInspector.setBackground(Color.BLACK);
    	jLPatente.setForeground(Color.WHITE);
    	jLSeleccionParquimetro.setForeground(Color.white);
    	jLUsuarioActual.setForeground(Color.white);
    	jLPatentesIngresadasInvalidas.setForeground(Color.white);
    }

    public void notDarkMode(){
    	jPanelInspector.setBackground(Color.LIGHT_GRAY);
    	jLPatente.setForeground(Color.BLACK);
    	jLSeleccionParquimetro.setForeground(Color.black);
    	jLUsuarioActual.setForeground(Color.black);
    	jLPatentesIngresadasInvalidas.setForeground(Color.black);
    }
}