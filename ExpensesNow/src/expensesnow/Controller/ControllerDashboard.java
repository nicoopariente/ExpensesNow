/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import expensesnow.Views.DashboardView;
import expensesnow.Model.ModelcsvFile;
import java.time.LocalDateTime;
/**
 *
 * @author User
 */
public class ControllerDashboard implements MouseListener {
    
    private DashboardView dashboard;
    private final ControllerApp controllerApp;
    private ModelcsvFile modelcsvFile = new ModelcsvFile();
    private String name;

    public ControllerDashboard(DashboardView dashboard, ControllerApp controller, String name) {
        this.dashboard = dashboard;
        this.controllerApp = controller;
        dashboard.getDashboardWelcomeMessage().setText("You have successfully logged In " + name);
        dashboard.getAddExpensesRecordButton().addMouseListener(this);
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        
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
