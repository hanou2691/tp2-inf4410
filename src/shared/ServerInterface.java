package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	int pell(int a) throws RemoteException;
	int prime(int a) throws RemoteException;
}
