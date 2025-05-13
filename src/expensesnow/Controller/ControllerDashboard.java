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
import java.util.List;
import expensesnow.utils.Helpers;
import java.time.Duration;
import java.time.YearMonth;
/**
 *
 * Adds logic to the Dashboard page
 */
public class ControllerDashboard implements MouseListener {
    
    private DashboardView dashboard;
    private final ControllerApp controllerApp;
    private ModelcsvFile modelcsvFile = new ModelcsvFile();
    private String name;
    private final Helpers helper = new Helpers();

    //Declaring the constructor
    public ControllerDashboard(DashboardView dashboard, ControllerApp controller, String name) {
        this.dashboard = dashboard;
        this.controllerApp = controller;
        //name will be the unique ID to display the correct information
        this.name = name;
        //Set the welcome message with the user name
        dashboard.getDashboardWelcomeMessage().setText("You have successfully logged In " + name);
        //Adding Mouse Listener to the Add Expense button
        dashboard.getAddExpensesRecordButton().addMouseListener(this);
        
        //displayExpenses method will gather all the expenses records the user already created and return a List
        List<List<String>> expensesList = modelcsvFile.displayExpenses(name);
        
        //modifyExpensesToText will convert the expensesList into a string
        String expenseResultInText = helper.modifyExpensesToText(expensesList);
        
        //Adding the expenses list string into the panel component
        dashboard.getExpensesTrack().setText(expenseResultInText);
        
        //sumFromAList method will sum the amounts of each expense for the current month
        int sumResult = helper.sumFromAList(expensesList);
        
        //adding the result sum to the text component
        dashboard.getExpensesTotal().setText(String.valueOf(sumResult));
        
        //Calculating the days between the current day and the end of the month
        LocalDateTime lastDay = YearMonth.now().atEndOfMonth().atTime(23, 59, 59);
        int duration = (int) Duration.between(LocalDateTime.now(), lastDay).toDays();
        
        //checkUserTargetAmount will return the int amount the user chose as monthly target when creating the account
        int targetAmount = modelcsvFile.checkUserTargetAmount(name);
        //calculating the money left to spend before getting to the target amount
        int moneyLeft = targetAmount - sumResult;
        int moneyPerDay = 0;
        if(moneyLeft > 0){
            //calculation the average of amount of money the user should expend per day in order to get to the target monthly amount
            moneyPerDay = moneyLeft / duration;
        }
        //calculating percentage of the target amount already spent
        int percentageCalculation = sumResult * 100 / targetAmount;
        
        //Adding the calculation amounts to the component as text
        dashboard.getExpensesCalculations().setText("You spent " + String.valueOf(percentageCalculation) + "% of your target expenses amount ($" + String.valueOf(targetAmount)+"). We recommend spending an average of $" + String.valueOf(moneyPerDay) +" per day until the end of the month.");
        
            
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        //After click inside the Add Expense, we rerender the calculations
        if(e.getSource() == this.dashboard.getAddExpensesRecordButton()){
            //Storing the user name, the store name, the amount spent, and the date
            String name = this.name;
            String store = dashboard.getStoreName().getText();
            String amount = dashboard.getRecordAmount().getText();
            String date = LocalDateTime.now().toString();
            //createExpense method will create a new expense record and return true if successfull in position 0. If failed, position 1 will contain an error message to be displayed.
            if((Boolean)modelcsvFile.createExpense(name, store, amount, date)[0]){
               //displayExpenses method will gather all the expenses records the user already created and return a List
                List<List<String>> expensesList = modelcsvFile.displayExpenses(name);

                //modifyExpensesToText will convert the expensesList into a string
                String expenseResultInText = helper.modifyExpensesToText(expensesList);

                //Adding the expenses list string into the panel component
                dashboard.getExpensesTrack().setText(expenseResultInText);

                //sumFromAList method will sum the amounts of each expense for the current month
                int sumResult = helper.sumFromAList(expensesList);

                //adding the result sum to the text component
                dashboard.getExpensesTotal().setText(String.valueOf(sumResult));

                //Calculating the days between the current day and the end of the month
                LocalDateTime lastDay = YearMonth.now().atEndOfMonth().atTime(23, 59, 59);
                int duration = (int) Duration.between(LocalDateTime.now(), lastDay).toDays();

                //checkUserTargetAmount will return the int amount the user chose as monthly target when creating the account
                int targetAmount = modelcsvFile.checkUserTargetAmount(name);
                //calculating the money left to spend before getting to the target amount
                int moneyLeft = targetAmount - sumResult;
                int moneyPerDay = 0;
                if(moneyLeft > 0){
                    //calculation the average of amount of money the user should expend per day in order to get to the target monthly amount
                    moneyPerDay = moneyLeft / duration;
                }
                //calculating percentage of the target amount already spent
                int percentageCalculation = sumResult * 100 / targetAmount;

                //Adding the calculation amounts to the component as text
                dashboard.getExpensesCalculations().setText("You spent " + String.valueOf(percentageCalculation) + "% of your target expenses amount ($" + String.valueOf(targetAmount)+"). We recommend spending an average of $" + String.valueOf(moneyPerDay) +" per day until the end of the month.");   
                //After adding the new record, we set the input fields to empty
                dashboard.getStoreName().setText("");
                dashboard.getRecordAmount().setText("");
                dashboard.getMessageDashboard().setText("");
                
            }else{
                //In case of failing to create new record, an error message will be displayed with information.
                dashboard.getMessageDashboard().setText((String)modelcsvFile.createExpense(name, store, amount, date)[1]);
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
