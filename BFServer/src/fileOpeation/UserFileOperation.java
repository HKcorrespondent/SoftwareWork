package fileOpeation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserFileOperation {
	public static ArrayList<String> loginUser = new ArrayList<>();
	private UserFileOperation(){
		
	}
	public final static String dataAddress ="C:\\Users\\asus\\Desktop\\SoftwareWork\\BFServer\\data";
	
	
	public static boolean signup(String username, String password){
		File file = new File(dataAddress+"\\"+username);
		if(file.exists()){
			return false;
		}else{
			file.mkdir();
			File file1 = new  File(dataAddress+"\\"+username+"\\"+"_"+username);
			try {
				FileWriter fw = new FileWriter(file1, false);
				fw.write(username+" "+password);
				fw.flush();
				fw.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}	
		}
		
		
		
		
	
	}
	public static boolean login(String username, String password){
		File file = new  File(dataAddress+"\\"+username+"\\"+"_"+username);
		if(!file.exists()){
			return false;
		}
		StringBuffer sb=null;
		BufferedReader  br=null;
		try {
			
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file))); //这里可以控制编码  
		sb = new StringBuffer();
		String line = null;
		
		while((line = br.readLine()) != null) {  
			sb.append(line);  
		}
		br.close();

		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
//			System.out.println("file not exsist");
			return false;
		}
//		String match= new String(sb);
		String[] match =(new String(sb)).split(" ");
		if(match[0].equals(username)&&match[1].equals(password)&&(!loginUser.contains(username))){
			loginUser.add(username);
			return true;
		}else{
			return false;
		}
		
		

		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(UserFileOperation.class.getClass().getClass());
		
		File file = new File(dataAddress+"\\"+"726752766");
		System.out.println(file.exists());
		if(!file.exists()){
			file.mkdir();
		}
	}

}
