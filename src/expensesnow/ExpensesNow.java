
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
        //Creating an instance of Controller App, which is the class that controls the App panel transition logic from page to page
        SwingUtilities.invokeLater(() -> new ControllerApp());
    }
    
}
