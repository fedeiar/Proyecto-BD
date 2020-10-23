package parquimetros;

import java.awt.EventQueue;

<<<<<<< HEAD
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextField;
=======
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Button;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
>>>>>>> parent of 73b3533... FEDE DEJA DE ROBAR

public class Prueba {

	private JFrame frame;

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
<<<<<<< HEAD
=======
		
		 

	      

	      
	      
	     

>>>>>>> parent of 73b3533... FEDE DEJA DE ROBAR
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
<<<<<<< HEAD
=======
		
		
			 
			
>>>>>>> parent of 73b3533... FEDE DEJA DE ROBAR
		frame = new JFrame();
		frame.setBounds(100, 100, 641, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		TextField textField = new TextField();
		panel.add(textField);
	}

}
