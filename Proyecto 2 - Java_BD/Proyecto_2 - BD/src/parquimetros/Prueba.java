package parquimetros;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;


import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Button;
import java.awt.Font;
import javax.swing.JPasswordField;


public class Prueba {

	private JFrame frame;

	
   public static TextField textField_1;
   public static TextField textField;
   private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba window = new Prueba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Prueba() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 641, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 627, 69);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		panel.setVisible(true);
		
		Button button = new Button("INGRESAR");
		button.setBounds(185, 229, 291, 37);
		frame.getContentPane().add(button);
		button.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		Label label_1 = new Label("PASWORD");
		label_1.setBounds(0, 184, 269, 23);
		frame.getContentPane().add(label_1);
		label_1.setAlignment(Label.CENTER);
		
		
		TextField user = new TextField();
		user.setBounds(282, 136, 306, 23);
		frame.getContentPane().add(user);
		user.setText("nacho");
		
		
		
		Label label = new Label("USUARIO");
		label.setBounds(0, 136, 269, 23);
		frame.getContentPane().add(label);
		label.setAlignment(Label.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(282, 187, 306, 20);
	
		frame.getContentPane().add(passwordField);
		
		button.addActionListener(new ActionListener() {
		
			
            public void actionPerformed(ActionEvent evt) {
            	
            	char [] clave= passwordField.getPassword();
            	String claveFinal = new String(clave);
            	
            	if (user.getText().equals("nacho") && claveFinal.equals("123")) {
            		frame.dispose();
            		
            		JOptionPane.showMessageDialog(null, "Ingresaste al sistema");
            		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                    ventanaPrincipal.setVisible(true);
            	} else {
            		JOptionPane.showMessageDialog(null, "El usuario o la contraseña es invalido","ERROR", JOptionPane.ERROR_MESSAGE);
            	}
            	
            	
                
             }
          });
		
	      
		
		
	}
}
