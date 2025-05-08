/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.Controller;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import expensesnow.Views.LogInView;
import expensesnow.Controller.ControllerLogin;
import expensesnow.Views.SignUpView;

/**
 *
 * @author User
 */
public class ControllerApp {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardlayout;

    public ControllerApp() {
        frame = new JFrame("frameApp");
        cardlayout = new CardLayout();
        mainPanel = new JPanel(cardlayout);
        
        LogInView logInView = new LogInView();
        ControllerLogin loginWindow = new ControllerLogin(logInView, this);
        mainPanel.add(logInView, "login");
        mainPanel.add(new SignUpView(), "signup");
        
        frame.setContentPane(mainPanel);
        frame.setSize(850, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        showLogin();
        
    }
    
    public final void showLogin(){
        cardlayout.show(mainPanel, "login");
    }
    
    public final void showSignUp(){
        cardlayout.show(mainPanel, "signup");
    }
    
}
