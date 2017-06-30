package serviceImpl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import service.RunService;
import serviceImpl.ExecuteServiceImpl.ExecutableFileClass;

public class RunServiceImpl implements RunService{
	
	
	private final String stackPtrAddReplace = "Ook.Ook?";
	private final String stackPtrSubReplace = "Ook?Ook.";
	private final String stackAddReplace = "Ook.Ook.";
	private final String stackSubReplace = "Ook!Ook!";
	private final String outputReplace = "Ook!Ook.";
	private final String inputReplace = "Ook.Ook!";
	private final String loopFrontReplace = "Ook!Ook?";
	private final String loopbackReplace = "Ook?Ook!";
	String[] replacement = {stackPtrAddReplace,stackPtrSubReplace,stackAddReplace
			,stackSubReplace,outputReplace,inputReplace,loopFrontReplace,loopbackReplace};
	
	
	private final String stackPtrAdd = ">";
	private final String stackPtrSub = "<";
	private final String stackAdd = "+";
	private final String stackSub = "-";
	private final String output = ".";
	private final String input = ",";
	private final String loopFront = "[";
	private final String loopback = "]";
	String[] bf = {stackPtrAdd,stackPtrSub,stackAdd,stackSub,output,input,loopFront,loopback
			
	};
	
	
	private static final String RunError = "运行错误!";
	@Override
	public String oneStepRun(String code, String param, String codeName, String username) throws RemoteException {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newSingleThreadExecutor();  
		FutureTask<String> future =  new FutureTask<>(new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					
					
					
					return "江来你是要负泽任的!";
					}
		});  
		executor.execute(future);  
		try {  
			    String result = future.get(5000, TimeUnit.MILLISECONDS); //设置超时执行时间为5秒
			    return result;
			} catch (InterruptedException e) {  
	
				executor.shutdown();  
			} catch (ExecutionException e) {  

				executor.shutdown();  
			} catch (TimeoutException e) {  
				executor.shutdown();  

				
				
			} finally {  
				
			    executor.shutdown();  
				
			}
		return RunError;

		
		
		
		
	}
	@Override
	public String run(String code, String param, String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newSingleThreadExecutor();  
		FutureTask<String> future =  new FutureTask<>(new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					ExecutableFileClass run = creatNewExecutableFileClass(code, param, fileName);
					if(run!=null){
						return run.run();
					}else{
						return RunError;
					}
					
					
					
					
					}
		});  
		executor.execute(future);  
		try {  
		    String result = future.get(5000, TimeUnit.MILLISECONDS); //设置超时执行时间为5秒
		    return result;
		} catch (InterruptedException e) {  

			executor.shutdown();  
		} catch (ExecutionException e) {  

			executor.shutdown();  
		} catch (TimeoutException e) {  
			executor.shutdown();  

			
			
		} finally {  
			
		    executor.shutdown();  
			
		}
	return RunError;
		
		
		
	}
	private ExecutableFileClass creatNewExecutableFileClass(String code, String param, String fileName){
		String name[] = fileName.split("\\.");
		if(name.length!=2){
			return null;
		}
		if(name[1].equals("bf")){
			return  bfRun(code, param, fileName);
		}
		if(name[1].equals("ook")){
			return  ookRun(code, param, fileName);
		}
		return null;
	}
	private ExecutableFileClass bfRun(String code, String param, String fileName){

		ExecutableFileClass os=new ExecutableFileClass(code, param);

		return os;
	}
	private ExecutableFileClass ookRun(String code, String param, String fileName){
		String realCode ="";
		code=code.replaceAll(" ", "");
		
		
		for(int i=0;i<code.length();i+=8){
			String c = code.substring(i, i+8);
			for(int j=0;j<replacement.length;j++){
				if(c.equals(replacement[j])){
					realCode=realCode+bf[j];
				}
			}
		}
		ExecutableFileClass os=new ExecutableFileClass(realCode, param);
		
		return os;
	}
	public static void main(String[] args){
		
		String name[] = "asdzx.bf".split("\\.");
		 System.out.println(name[1]);
		}
	private static HashMap< String, ExecutableFileClass> user2oneStepRun = new HashMap<>();
	
	private ExecutableFileClass getExecutableFileClassRunFromUsername(String code, String param,String fileName,String username){
		if(user2oneStepRun.containsKey(username)){
			return user2oneStepRun.get(username);
		}else{
			
			
			
			
			user2oneStepRun.put(username,creatNewExecutableFileClass(code, param, fileName));
			return user2oneStepRun.get(username);
		}
	
	}
	
	
}
