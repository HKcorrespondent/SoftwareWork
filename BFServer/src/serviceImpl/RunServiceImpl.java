package serviceImpl;

import java.rmi.RemoteException;

import service.RunService;

public class RunServiceImpl implements RunService{

	@Override
	public String run(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		return "成功运行了! code:"+code;
	}

}
