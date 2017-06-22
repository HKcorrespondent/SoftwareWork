package serviceImpl;

import java.rmi.RemoteException;

import service.ConfigurationService;

public class ConfigurationServiceImpl implements ConfigurationService{
	private String[] fileTypeArgs = {".bf",".ook"};
	@Override
	public String[] getExecutableFileType() throws RemoteException {
		// TODO Auto-generated method stub
		return fileTypeArgs;
	}
	
}
