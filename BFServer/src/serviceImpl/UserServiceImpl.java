package serviceImpl;

import java.io.File;
import java.rmi.RemoteException;

import fileOpeation.UserFileOperation;
import service.UserService;

public class UserServiceImpl implements UserService{
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	System.out.println(UserServiceImpl.class.getClassLoader().getResource("User.txt"));
		File UserData= new File(""+UserServiceImpl.class.getClassLoader().getResource("User.txt"));
	}
	
	
	
	@Override
	public boolean login(String username, String password) throws RemoteException {
		return UserFileOperation.login();
		
		
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		
		
		return true;
	}
	
	@Override
	public String signup(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		
		
		
		if(UserFileOperation.signup()){
			return "注册成功!";
		}else{
			return "该用户名已存在!";
		}
		
		
		
		
		
		
	
	}

}
