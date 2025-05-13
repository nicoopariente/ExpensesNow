/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import expensesnow.utils.Helpers;
/**
 *
 * ModelcsvFile class contains methods to manipulate the csv file storing the user and expenses information
 */
public class ModelcsvFile {
    
    private final String directoryPath = "src/expensesnow/db";
    private final String csvFileName = "users_record.csv";
    private final String csvExpensesFileName = "expenses_record.csv";
    private FileWriter file;
    private final Helpers helper = new Helpers();
    
    public ModelcsvFile(){
        
    }
    
    //Method to check if a File with an specific name already exists
    public final boolean checkFile(String fileName){
        
        File fileCheck = new File(this.directoryPath, fileName);
        
        if(fileCheck.exists() && fileCheck.isFile()){
            return true;
        }else{
            return false;
        }
        
    }
    
    //Method that will create a new User record. Parameters are name, password and newTargetAmount.
    public final Object[] createRecord(String name, String password, String newTargetAmount ){
        //checks if the file was already created.
        if(checkFile(this.csvFileName)){
            //checks if a user with the same name already exists
            if(checkRecord(name)){
                //checks if the chosen Target Amount can be converted to an int type
                if(helper.checkIfNumber(newTargetAmount)){
                    //checks if name or password are empty
                    if(!name.equals("") && !password.equals("")){
                        String line = name + "," + password + "," + newTargetAmount;
                    try{
                        //Writes to the file the user information
                        this.file = new FileWriter(this.directoryPath + "/" + this.csvFileName, true);
                        file.append(line + "\n");
                        file.close();
                    }catch(IOException e){
                        System.err.print(e.getMessage());
                    }
                    return new Object[] {true, ""};
                    }else{
                        return new Object[] {false, "Name and Password cannot be empty"};
                    }
                        
                }else{
                    return new Object[] {false, "Only numbers are accepted for the Target Amount"}; 
                }
            }else{
            return new Object[] {false, "The chosen name already exists. Please try with a different one, thanks"}; 
            }
        //if the file does not exist, createFile method will be called, and if successfull, the createRecord function will call itself again.    
        }else if(createFile(this.csvFileName)){
                return createRecord(name, password, newTargetAmount );
        }else{
            return new Object[] {false, "failed to save record"}; 
        }
        
    }
    
    //method to create a csv file in the db folder
    public final boolean createFile(String fileName){
        try{
            this.file = new FileWriter(this.directoryPath + "/" + fileName);
        }catch(IOException e){
            System.err.print(e.getMessage());
        }
        
        if(checkFile(this.csvFileName)){
            return true;
        }else{
            return false;
        }
    }
    
    //method to check if a user with the same name already exists in the users file
    public final boolean checkRecord(String name){
        List<List<String>> fileLine = new ArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.directoryPath + "/" + this.csvFileName));
            String oneLine;
            while(( oneLine = reader.readLine()) != null){
            String[] values = oneLine.split(",");
            fileLine.add(Arrays.asList(values));
        }
        }catch(IOException e){
            System.err.print(e.getMessage());
        }
        boolean found = true;
        for(List<String> eachLine : fileLine){
            String nameLine = eachLine.get(0);
            if(nameLine.equals(name)){
                found = false;
            }
        }
        return found;
    }  
    
    //Method to check if the name and password are correct
    public final boolean checkUserData(String name, String password){
    List<List<String>> fileLine = new ArrayList();
    try{
        BufferedReader reader = new BufferedReader(new FileReader(this.directoryPath + "/" + this.csvFileName));
        String oneLine;
        while(( oneLine = reader.readLine()) != null){
        String[] values = oneLine.split(",");
        fileLine.add(Arrays.asList(values));
    }
    }catch(IOException e){
        System.err.print(e.getMessage());
    }
    boolean found = false;
    for(List<String> eachLine : fileLine){
        String nameLine = eachLine.get(0);
        if(nameLine.equals(name)){
            if(eachLine.get(1).equals(password)){
                found = true;
            }
        }
    }
    return found;
    }
    
    //Method to create an expense record in the expenses csv file  
    public final Object[] createExpense(String name, String store, String amount, String date){
        //Checks if the file was already created
        if(checkFile(this.csvExpensesFileName)){
            //Checks if the chosen amount can be converted to an int type
            if(helper.checkIfNumber(amount)){
                
                String line = name + "," + store + "," + amount + "," + date;
                try{
                    //creates the expenses record line
                    this.file = new FileWriter(this.directoryPath + "/" + this.csvExpensesFileName, true);
                    file.append(line + "\n");
                    file.close();
                }catch(IOException e){
                    System.err.print(e.getMessage());
                }
                
                return new Object[] {true, "Expense Added"};
            }else{
                return new Object[] {false, "Only numbers are accepted for the Target Amount"};
            }
            //If file does not exist, the createFile method will be called, and if successfull, the createExpense method will call itself to retry
        }else if(createFile(this.csvExpensesFileName)){
                return createExpense(name, store, amount, date);
        }else{
            System.out.println("failed to save record");
            return new Object[] {false, "failed to save record"}; 
        }
        
    }
    
    //method to gather all the expenses records that belong to the logged user
    public final List<List<String>> displayExpenses(String name){
        List<List<String>> fileLine = new ArrayList();
        List<List<String>> userExpenses = new ArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.directoryPath + "/" + this.csvExpensesFileName));
            String oneLine;
            while(( oneLine = reader.readLine()) != null){
            String[] values = oneLine.split(",");
            fileLine.add(Arrays.asList(values));
        }
        }catch(IOException e){
            System.err.print(e.getMessage());
        }
        for(List<String> eachLine : fileLine){
            String nameLine = eachLine.get(0);
            if(nameLine.equals(name)){
                userExpenses.add(eachLine);
            }
        }
        return userExpenses;
        
    }
    
    
    //Method to retrive the user Target Amount
    public final int checkUserTargetAmount(String name){
        List<List<String>> fileLine = new ArrayList();
        int amount = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.directoryPath + "/" + this.csvFileName));
            String oneLine;
            while(( oneLine = reader.readLine()) != null){
            String[] values = oneLine.split(",");
            fileLine.add(Arrays.asList(values));
        }
        }catch(IOException e){
            System.err.print(e.getMessage());
        }
        for(List<String> eachLine : fileLine){
            String nameLine = eachLine.get(0);
            if(nameLine.equals(name)){
                amount = Integer.parseInt(eachLine.get(2));
            }
        }
        return amount;
    }
    
}
