package fileOpeation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Scanner;

import javafx.beans.value.WritableBooleanValue;




public class IOFileOpeation {
	private static String dataAddress = UserFileOperation.dataAddress;
	public final static String splitFileSymbol = "_";
	public final static String splitFileInformation = "@";
	private static int HistoryVersionNumber = 3;
	public static boolean  writeFile(String codeGetIn, String userId, String fileName){
		File file1 = new  File(dataAddress+"\\"+userId+"\\"+fileName);
		String finalString ="";
		if(!file1.exists()){
			finalString=firstSave();
		}else{
			String readString =readFile(userId, fileName);
			String[] fileArgs=readString.split(splitFileSymbol);
//			System.out.println(fileArgs.length);
			if(fileArgs.length>=HistoryVersionNumber){
//				System.out.println(fileArgs[fileArgs.length-1].split(splitFileInformation)[0]);
				int saveVision=Integer.parseInt(fileArgs[fileArgs.length-1].split(splitFileInformation)[0]);
				
//				System.out.println(fileArgs[fileArgs.length-1].split(splitFileInformation)[0]);
//				System.out.println(saveVision+"!!");
				
				String newfileStrng=newAddFile(codeGetIn,saveVision+1);
				for(int i=1;i<fileArgs.length;i++){
					finalString=finalString+fileArgs[i]+splitFileSymbol;
				}
				finalString=finalString+newfileStrng;
				
				
			}else{
				int saveVision=Integer.parseInt(fileArgs[fileArgs.length-1].split(splitFileInformation)[0]);
				
//				System.out.println(fileArgs[fileArgs.length-1]);
//				System.out.println(saveVision+"!!");
				
				String newfileStrng=newAddFile(codeGetIn,saveVision+1);
				for(int i=0;i<fileArgs.length;i++){
					finalString=finalString+fileArgs[i]+splitFileSymbol;
				}
				finalString=finalString+newfileStrng;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		try {
			FileWriter fw = new FileWriter(file1, false);
			fw.write(finalString);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	private static String newAddFile(String fileString,int saveVision){
		
		
		return saveVision+splitFileInformation+Calendar.getInstance().getTime()+splitFileInformation+fileString+splitFileSymbol;
		
		
	}
	private static String firstSave(){
		Calendar.getInstance().getTime();
		return "0"+splitFileInformation+Calendar.getInstance().getTime()+splitFileInformation+"code"+splitFileSymbol;
		
	}
	
	
	
	private static String getTheNewlyFile(){
		
		return dataAddress;
		
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
			sb.append("\n");
//			System.out.println(sb);

		}
		if(sb.length()>1){
		sb.deleteCharAt(sb.length()-1);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not exsist");
			
		}
		
		
		
//		BufferedReader bre = null;
//
//		
//		
//		try {
//			bre = new BufferedReader(new FileReader(file));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}//此时获取到的bre就是整个文件的缓存流
//		sb = new StringBuffer();
		return new String(sb);
		
		
//		return new String(sb);
		
	}
	
	public static String readFileList(String userId) {
		// TODO Auto-generated method stub
		File file = new  File(dataAddress+"\\"+userId);
		System.out.println(dataAddress+"\\"+userId);
		File[] files= file.listFiles();
		String retString = "";
		for(int i =0;i<files.length;i++){
			
			if(files[i].getName().charAt(0)!='_'){
				retString=files[i].getName()+" "+retString;
			}
		}
		

	
		
		System.out.println(retString);
		return retString;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		

		File f= new File("D:/file.txt");
		if(!f.exists()){
			f.createNewFile();
		}
		
	}
	
	
	
}
