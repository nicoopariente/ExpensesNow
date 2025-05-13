/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import expensesnow.Views.SignUpView;
import expensesnow.Model.ModelcsvFile;
 
/**
 *
 * Adds logic to the Sign Up page
 */
public class ControllerSignup implements MouseListener {
    
    private SignUpView signup;
    private final ControllerApp controllerApp;
    private ModelcsvFile modelcsvFile = new ModelcsvFile();
    private String name;
    
    //Declaring the constructor
    public ControllerSignup(SignUpView signup, ControllerApp controller) {
        this.signup = signup;
        this.controllerApp = controller;
        //Adding Mouse Listener to the Sign Up button
        signup.getCreateAccountButton().addMouseListener(this);
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
        //Checking if the Mouse Event is related to the Sign Up Button
        if(e.getSource() == this.signup.getCreateAccountButton()){
            //Storing the information of the User. Name, Password and Target Amount for each Month
            String name = signup.getNewUserName().getText();
            String password = signup.getNewUserPassword().getText();
            String targetAmount = signup.getNewTargetAmount().getText();
            //createRecord method will return an object with two values, a boolean and a string. 
            //The boolean will return true if user created successfully. The string will return an error message in case the creation failed
            if((Boolean)modelcsvFile.createRecord(name, password, targetAmount)[0]){
                this.name = name;
                //We use the Controller App method to display the Dashboard
                controllerApp.showDashboardFromSignup();
            }else{
                //If creation failed, we display a message with information on the reason
                signup.getMessageLabel().setText((String)modelcsvFile.createRecord(name, password, targetAmount)[1]);
            }
            
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
