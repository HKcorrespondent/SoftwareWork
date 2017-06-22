package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConfigurationService extends Remote{
	public String[] getExecutableFileType() throws RemoteException;
		
	
}
