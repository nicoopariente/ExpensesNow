/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expensesnow.utils;

/**
 *
 * @author User
 */
public class Helpers {
    
    public final boolean checkIfNumber(String targetAmount){
        try{
            Integer.parseInt(targetAmount); 
            return true;
        }catch(NumberFormatException e){
            return false;
        }
        
    }
    
}
