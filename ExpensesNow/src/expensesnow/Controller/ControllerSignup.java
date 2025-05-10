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
 * @author User
 */
public class ControllerSignup implements MouseListener {
    
    private SignUpView signup;
    private final ControllerApp controllerApp;
    private ModelcsvFile modelcsvFile = new ModelcsvFile();

    public ControllerSignup(SignUpView signup, ControllerApp controller) {
        this.signup = signup;
        this.controllerApp = controller;
        signup.getCreateAccountButton().addMouseListener(this);
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
        if(e.getSource() == this.signup.getCreateAccountButton()){
            String name = signup.getNewUserName().getText();
            String password = signup.getNewUserPassword().getText();
            String targetAmount = signup.getNewTargetAmount().getText();
            if((Boolean)modelcsvFile.createRecord(name, password, targetAmount)[0]){
                controllerApp.showDashboard();
            }else{
                signup.getMessageLabel().setText((String)modelcsvFile.createRecord(name, password, targetAmount)[1]);
            }
            
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
