/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import expensesnow.Views.LogInView;
import expensesnow.Model.ModelcsvFile;
 
/**
 *
 * Adds logic to the Log in page
 */
public class ControllerLogin implements MouseListener {
    
    private LogInView login;
    private final ControllerApp controllerApp;
    private ModelcsvFile modelcsvFile = new ModelcsvFile();
    private String name;

    //Declaring the constructor
    public ControllerLogin(LogInView login, ControllerApp controller) {
        this.login = login;
        this.controllerApp = controller;
        //Adding Mouse Listener to the Log In button
        login.getLoginButton().addMouseListener(this);
        //Adding Mouse Listener to the Sign Up button
        login.getSignupButton().addMouseListener(this);
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
        //Checking if the Mouse Event is related to the Log In Button
        if(e.getSource() == this.login.getLoginButton()){
            //Storing the information of the User. Name and Password
            String name = this.login.getUsernameField().getText();
            String password = this.login.getPasswordField().getText();
            //chekUserData method will validate if the password is correct for the name. If matches, it will return true.
            if(modelcsvFile.checkUserData(name, password)){
                this.name = name;
                //We use the Controller App method to display the Dashboard
                controllerApp.showDashboardFromLogin();
            }
            else{
                this.login.getMessageLogin().setText("Incorrect name or password");
            }
        }
        //Checking if the Mouse Event is related to the Sign Up Button
        if(e.getSource() == this.login.getSignupButton()){
            //We use the Controller App method to display the Dashboard
            controllerApp.showSignUp();
        }
        
    }

    public String getName() {
        return name;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
