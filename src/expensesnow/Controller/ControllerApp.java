
package expensesnow.Controller;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import expensesnow.Views.LogInView;
import expensesnow.Views.SignUpView;
import expensesnow.Views.DashboardView;


/**
 *
 * ControllerApp will control the page to be displayed and the interaction between pages.
 */
public class ControllerApp {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardlayout;
    private ControllerLogin loginWindow;
    private DashboardView dashboardView;
    private ControllerSignup signupWindow;

    public ControllerApp() {
        
        //Creating main Frame that will cotain all the pages (panels) that will transition
        frame = new JFrame("ExpensesNow");
        //CardLayout class allows to change from panel to panel
        cardlayout = new CardLayout();
        mainPanel = new JPanel(cardlayout);
        
        //Creating instance of the Log in View
        LogInView logInView = new LogInView();
        //Adding the logic that is separated in a Controller Class that implements MouseListener interface
        loginWindow = new ControllerLogin(logInView, this);
        //We add the logInView object to our main Frame
        mainPanel.add(logInView, "login");
        
        //Creating instance of the Sign up View
        SignUpView signUpView = new SignUpView();
        //Adding the logic that is separated in a Controller Class that implements MouseListener interface
        signupWindow = new ControllerSignup(signUpView, this);
        //We add the signupView object to our main Frame
        mainPanel.add(signUpView, "signup");
        

        
        //Setting some properties to our main frame
        frame.setContentPane(mainPanel);
        frame.setSize(850, 520);
        //Closing the Frame should stop the app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //The window will default to the center of the screen
        frame.setLocationRelativeTo(null);
        //Avoiding the capacity to resize the window to prevent UI issues
        frame.setResizable(false);
        //Setting the frame visible
        frame.setVisible(true);
 
        
        //The constructor initialize the first panel
        showLogin();
        
    }
    
    //Method to show the login view
    public final void showLogin(){
        cardlayout.show(mainPanel, "login");
    }
    
    //Method to show the signup view
    public final void showSignUp(){
        cardlayout.show(mainPanel, "signup");
    }
    
    //Method to show the dashboard view when user logs in
    public final void showDashboardFromLogin(){
        if(dashboardView == null){
            //Creating instance of the Dashboar view
            dashboardView = new DashboardView();
            //Adding the logic that is separated in a Controller Class that implements MouseListener interface. We pass the name of the user that is unique and will 
            //be used to display the correct information
            ControllerDashboard dashboardWindow = new ControllerDashboard(dashboardView, this, loginWindow.getName()); 
            //We add the dashboardView object to our main Frame
            mainPanel.add(dashboardView, "dashboard");
        }
        else{
            dashboardView.getDashboardWelcomeMessage().setText("You have successfully logged In " + loginWindow.getName());
        }
        cardlayout.show(mainPanel, "dashboard");
    }
    
    //Method to show the dashboard view when user signs up
    public final void showDashboardFromSignup(){
    if(dashboardView == null){
        //Creating instance of the Dashboar view
        dashboardView = new DashboardView();
        //Adding the logic that is separated in a Controller Class that implements MouseListener interface. We pass the name of the user that is unique and will 
        //be used to display the correct information
        ControllerDashboard dashboardWindow = new ControllerDashboard(dashboardView, this, signupWindow.getName()); 
        //We add the dashboardView object to our main Frame
        mainPanel.add(dashboardView, "dashboard");
    }
    else{
        dashboardView.getDashboardWelcomeMessage().setText("You have successfully logged In " + signupWindow.getName());
    }
    cardlayout.show(mainPanel, "dashboard");
}
    
}
