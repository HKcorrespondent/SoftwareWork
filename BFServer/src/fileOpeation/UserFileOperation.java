package fileOpeation;

import java.io.File;

public class UserFileOperation {
	private UserFileOperation(){
		
	}
	private final static String dataAddress ="C:\\Users\\asus\\Desktop\\SoftwareWork\\BFServer\\data";
	
	
	public static boolean signup(){
		return false;
		
	}
	public static boolean login(){
		return true;
		
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
