package serviceImpl;

import java.rmi.RemoteException;

import fileOpeation.IOFileOpeation;
import service.ConfigurationService;

public class ConfigurationServiceImpl implements ConfigurationService{
	private String[] fileTypeArgs = {".bf",".ook"};
	private String splitFileSymbol =IOFileOpeation.splitFileSymbol;
	private String splitFileInformation = IOFileOpeation.splitFileInformation;
	
	@Override
	public String[] getExecutableFileType() throws RemoteException {
		// TODO Auto-generated method stub
		return fileTypeArgs;
	}

	@Override
	public String getSplitFileSymbol() throws RemoteException {
		// TODO Auto-generated method stub
		return splitFileSymbol;
	}

	@Override
	public String getSplitFileInformation() throws RemoteException {
		// TODO Auto-generated method stub
		return splitFileInformation;
	}
	
}
