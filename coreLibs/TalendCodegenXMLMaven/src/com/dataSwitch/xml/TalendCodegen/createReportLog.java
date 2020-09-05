
package com.dataSwitch.xml.TalendCodegen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.*;


public class createReportLog {
	
	public static void addToLog(String s,String path){
		
		
		try { 
            BufferedWriter out = new BufferedWriter(new FileWriter(path+"_log2.txt", true)); 
            out.write(s); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        }
		
	}
	
	public static void mergeFiles(String Path){
		
		
		String data = ""; 
	    try {
			data = new String(Files.readAllBytes(Paths.get(Path+"_log.txt")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try { 
            BufferedWriter out = new BufferedWriter(new FileWriter(Path+"Report_Log.txt", true)); 
            out.write(data); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        }
	    
	    
	    
	    String data2 = ""; 
	    try {
			data2 = new String(Files.readAllBytes(Paths.get(Path+"_log2.txt")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try { 
            BufferedWriter out = new BufferedWriter(new FileWriter(Path+"Report_Log.txt", true)); 
            out.write(data2); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        }
	    
	    File file = new File(Path+"_log2.txt");
	    file.delete();
	    File file2 = new File(Path+"_log.txt");
	    file2.delete();
	}
	
}