/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.utils;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

/**
 *
 * Class that contains helping methods
 */
public class Helpers {
    
    //method to check if a string can be converted to an int
    public final boolean checkIfNumber(String targetAmount){
        try{
            Integer.parseInt(targetAmount); 
            return true;
        }catch(NumberFormatException e){
            return false;
        }
        
    }
    
    //method to return the sum of expenses amounts for the current month
    public final int sumFromAList(List<List<String>> list){
        int result = 0; 
        for(List<String> eachLine : list){
            LocalDateTime date = LocalDateTime.parse(eachLine.get(3));
            LocalDateTime dateNow = LocalDateTime.now();
            if(date.getYear() == dateNow.getYear() && date.getMonth() == dateNow.getMonth()){
                int amount = Integer.parseInt(eachLine.get(2));
                result+= amount;
            }
            
        }
        return result;
    }
    
    //method to convert a List into a string
    public final String modifyExpensesToText(List<List<String>> expenses){
        String expenseResult = ""; 
        for(List<String> eachLine : expenses){
         expenseResult += "STORE: " + eachLine.get(1) + "   ||   AMOUNT: " + eachLine.get(2) + "   ||   DATE: " + eachLine.get(3) + "\n";
         }
        return expenseResult;
    }
    
}
