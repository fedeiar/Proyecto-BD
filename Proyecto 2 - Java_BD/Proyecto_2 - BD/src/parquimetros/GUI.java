package parquimetros;

import javax.swing.SwingUtilities;

public class GUI {
    public static void main(String[] args) 
   {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            VentanaPrincipal inst = new VentanaPrincipal();
            inst.setLocationRelativeTo(null);
            inst.setVisible(true);
         }
      });
   }   
}
