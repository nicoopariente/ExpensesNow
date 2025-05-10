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
 * @author User
 */
public class ControllerLogin implements MouseListener {
    
    private LogInView login;
    private final ControllerApp controllerApp;
    private ModelcsvFile modelcsvFile = new ModelcsvFile();

    public ControllerLogin(LogInView login, ControllerApp controller) {
        this.login = login;
        this.controllerApp = controller;
        login.getLoginButton().addMouseListener(this);
        login.getSignupButton().addMouseListener(this);
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == this.login.getLoginButton()){
            String name = this.login.getUsernameField().getText();
            String password = this.login.getPasswordField().getText();
            if(modelcsvFile.checkUserData(name, password)){
                controllerApp.showDashboard();
            }
            else{
                this.login.getMessageLogin().setText("Incorrect name or password");
            }
        }
        if(e.getSource() == this.login.getSignupButton()){
            controllerApp.showSignUp();
        }
        
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
