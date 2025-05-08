
package expensesnow;

import javax.swing.SwingUtilities;
import expensesnow.Controller.ControllerApp;

/**
 *
 * @author User
 */
public class ExpensesNow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> new ControllerApp());
        System.out.println("Hola");
    }
    
}
