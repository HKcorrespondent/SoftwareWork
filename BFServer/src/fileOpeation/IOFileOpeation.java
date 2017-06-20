package fileOpeation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javafx.beans.value.WritableBooleanValue;

public class IOFileOpeation {
	private static String dataAddress = UserFileOperation.dataAddress;
	public static boolean  writeFile(String file, String userId, String fileName){
		File file1 = new  File(dataAddress+"\\"+userId+"\\"+fileName);
		try {
			FileWriter fw = new FileWriter(file1, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
	}
	public static String readFile(String userId, String fileName) {
		File file = new  File(dataAddress+"\\"+userId+"\\"+fileName);
		StringBuffer sb=null;
		BufferedReader  br=null;
		try {
			
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file))); //这里可以控制编码  
		sb = new StringBuffer();
		String line = null;
		
		while((line = br.readLine()) != null) {  
			sb.append(line);  
		}
		

		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not exsist");
			
		}
		return new String(sb);
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		
		Scanner s=new Scanner(System.in);
		String t=s.nextLine();
		t=t+String.format("\n1233\n213");
		System.out.println(t);
		IOFileOpeation.writeFile(t, "726752766", "123.bf");
		System.out.println(IOFileOpeation.readFile("726752766", "123.bf"));
	}
	
	
	
}
