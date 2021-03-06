package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.ConfigurationService;
import service.IOService;
import service.RunService;
import service.UserService;
import serviceImpl.ConfigurationServiceImpl;
import serviceImpl.IOServiceImpl;
import serviceImpl.RunServiceImpl;
import serviceImpl.UserServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService,RunService,ConfigurationService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	private RunService runService;
	private ConfigurationService configurationService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		runService = new RunServiceImpl();
		configurationService  =new ConfigurationServiceImpl();
	}
	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName);
	}

	@Override
	public String readFile(String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName);
	}

	@Override
	public String readFileList(String userId) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId);
	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.logout(username);
	}

	@Override
	public String signup(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		
		return userService.signup(username, password);
	}

	@Override
	public String run(String code, String param, String codeName) throws RemoteException {
		// TODO Auto-generated method stub
		return runService.run(code, param,  codeName);
	}
	@Override
	public String[] getExecutableFileType() throws RemoteException {
		// TODO Auto-generated method stub
		return configurationService.getExecutableFileType();
	}
	@Override
	public String oneStepRun(String code, String param, String codeName, String username) throws RemoteException {
		// TODO Auto-generated method stub
		return runService.oneStepRun(code, param, codeName, username);
	}
	@Override
	public String getSplitFileSymbol() throws RemoteException {
		// TODO Auto-generated method stub
		return configurationService.getSplitFileSymbol();
	}
	@Override
	public String getSplitFileInformation() throws RemoteException {
		// TODO Auto-generated method stub
		return configurationService.getSplitFileInformation();
	}
	@Override
	public String oneStepRunClear(String codeName, String username) throws RemoteException {
		// TODO Auto-generated method stub
		return runService.oneStepRunClear(codeName, username);
	}
	

}
