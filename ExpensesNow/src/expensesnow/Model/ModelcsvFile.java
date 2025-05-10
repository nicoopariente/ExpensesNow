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
 * @author User
 */
public class ModelcsvFile {
    
    private final String directoryPath = "src/expensesnow/db";
    private final String csvFileName = "users_record.csv";
    private FileWriter file;
    private final Helpers helper = new Helpers();
    
    public ModelcsvFile(){
        
    }
    
    public final boolean checkFile(){
        
        File fileCheck = new File(this.directoryPath, this.csvFileName);
        
        if(fileCheck.exists() && fileCheck.isFile()){
            System.out.println("The file exists");
            return true;
        }else{
            System.out.println("The file is not present");
            return false;
        }
        
    }
    
    public final Object[] createRecord(String name, String password, String newTargetAmount ){
        if(checkFile()){
            if(checkRecord(name)){
                if(helper.checkIfNumber(newTargetAmount)){
                    String line = name +","+password+","+newTargetAmount;
                    try{
                        this.file = new FileWriter(this.directoryPath + "/" + this.csvFileName, true);
                        file.append(line + "\n");
                        file.close();
                        System.out.println("done");
                    }catch(IOException e){
                        System.err.print(e.getMessage());
                    }
                    return new Object[] {true, ""};    
                }else{
                    return new Object[] {false, "Only numbers are accepted for the Target Amount"}; 
                }
            }else{
                System.out.println("The chosen name already exists. Please try with a new one, thanks");
            return new Object[] {false, "The chosen name already exists. Please try with a different one, thanks"}; 
            }
            
        }else if(createFile()){
                return createRecord(name, password, newTargetAmount );
        }else{
            System.out.println("failed to save record");
            return new Object[] {false, "failed to save record"}; 
        }
        
    }
    
    public final boolean createFile(){
        try{
            this.file = new FileWriter(this.directoryPath + "/" + this.csvFileName);
        }catch(IOException e){
            System.err.print(e.getMessage());
        }
        
        if(checkFile()){
            System.out.println("File Created");
            return true;
        }else{
            System.out.println("Failed to create file");
            return false;
        }
    }
    
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
            System.out.println(eachLine);
            String nameLine = eachLine.get(0);
            System.out.println("nameLine");
            System.out.println("name");
            if(nameLine.equals(name)){
                found = false;
            }
        }
        return found;
    }  
    
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
            System.out.println(eachLine);
            String nameLine = eachLine.get(0);
            System.out.println("nameLine");
            System.out.println("name");
            if(nameLine.equals(name)){
                if(eachLine.get(1).equals(password)){
                    found = true;
                }
            }
        }
        return found;
    }
    
}
