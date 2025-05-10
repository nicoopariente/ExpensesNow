/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.Controller;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import expensesnow.Views.LogInView;
import expensesnow.Views.SignUpView;
import expensesnow.Views.DashboardView;


/**
 *
 * @author User
 */
public class ControllerApp {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardlayout;

    public ControllerApp() {
        
        //Creating main Frame that will cotain all the pages (panels) that will transition
        frame = new JFrame("frameApp");
        //CardLayout class allows us to change from panel to panel
        cardlayout = new CardLayout();
        mainPanel = new JPanel(cardlayout);
        
        //Creating instance of the Log in View
        LogInView logInView = new LogInView();
        //Adding the logic that is separated in a Controller Class that implements MouseListener interface
        ControllerLogin loginWindow = new ControllerLogin(logInView, this);
        //We add the logInView object to our main Frame
        mainPanel.add(logInView, "login");
        
        //Creating instance of the Sign up View
        SignUpView signUpView = new SignUpView();
        //Adding the logic that is separated in a Controller Class that implements MouseListener interface
        ControllerSignup signupWindow = new ControllerSignup(signUpView, this);
        //We add the signupView object to our main Frame
        mainPanel.add(signUpView, "signup");
        
        mainPanel.add(new DashboardView(), "dashboard");
        
        //Setting some properties to our main frame
        frame.setContentPane(mainPanel);
        frame.setSize(850, 520);
        //Closing the Frame should stop the app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    
    //Method to show the dashboard view
    public final void showDashboard(){
        cardlayout.show(mainPanel, "dashboard");
    }
    
}
