package client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.lang.Math;

import shared.ServerInterface;

public class Client {
	public static void main(String[] args) {
		String distantHostname = null;

		Client client = new Client();
		client.parseArgs(args);
		client.loadServersStubs();
		client.run();
	}

	private int nbServers;
	private String[] serversIPs = null;
	private ServerInterface[] serverStubs = null;
	

	private void parseArgs(String[] args) {
		// Get nb servers
		nbServers = Integer.valueOf(args[0]); 
	}	

	private void loadServersStubs() {
		int i = 0;
		for (i = 0 ; i < serversIPs.length; i++) {
			if (serversIPs[i] != null) {
				serverStubs[i] = loadServerStub(serversIPs[i]);
			}	
		} 		
	}
	
	public Client() {
		super();

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		
	}

	private void run() {

		if (distantServerStub != null) {
			appelRMIDistant();
		}
	}

	private ServerInterface loadServerStub(String hostname) {
		ServerInterface stub = null;

		try {
			Registry registry = LocateRegistry.getRegistry(hostname);
			stub = (ServerInterface) registry.lookup("server");
		} catch (NotBoundException e) {
			System.out.println("Erreur: Le nom '" + e.getMessage()
					+ "' n'est pas défini dans le registre.");
		} catch (AccessException e) {
			System.out.println("Erreur: " + e.getMessage());
		} catch (RemoteException e) {
			System.out.println("Erreur: " + e.getMessage());
		}

		return stub;
	}


	private void appelRMIDistant() {
		try {
			long start = System.nanoTime();			
			//distantServerStub.execute();
			long end = System.nanoTime();

			System.out.println("Temps écoulé appel RMI distant: "
					+ (end - start) + " ns");
		} catch (RemoteException e) {
			System.out.println("Erreur: " + e.getMessage());
		}
	}
}
