package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RunService extends Remote{
	public String run(String code, String param) throws RemoteException;
		
	
}
