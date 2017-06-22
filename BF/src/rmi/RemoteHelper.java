package rmi;

import java.rmi.Remote;

import service.ConfigurationService;
import service.IOService;
import service.RunService;
import service.UserService;

public class RemoteHelper {
	private Remote remote;
	private static RemoteHelper remoteHelper = new RemoteHelper();
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private RemoteHelper() {
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public IOService getIOService(){
		return (IOService)remote;
	}
	
	public UserService getUserService(){
		return (UserService)remote;
	}
	
	public RunService getRunservice(){
		return (RunService)remote;
	}
	public ConfigurationService  getConfiguratioSservice(){
		return (ConfigurationService)remote;
	}
}
