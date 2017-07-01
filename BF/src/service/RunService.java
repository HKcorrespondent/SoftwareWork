package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RunService extends Remote{
	public String run(String code, String param , String codeName) throws RemoteException;
	public String oneStepRunClear( String codeName,String username) throws RemoteException;
	public String oneStepRun(String code, String param, String codeName,String username) throws RemoteException;
}
