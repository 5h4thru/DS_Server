package utd.persistentDataStore.datastoreServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import utd.persistentDataStore.datastoreServer.commands.DeleteCommand;
import utd.persistentDataStore.datastoreServer.commands.DirectoryCommand;
import utd.persistentDataStore.datastoreServer.commands.ReadCommand;
import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.datastoreServer.commands.WriteCommand;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DatastoreServer {

	private ServerSocket serverSocket;
	static public final int port = 10023;

	public void startup() throws IOException 
	{
		System.out.println("Starting service at port " + port);
		serverSocket = new ServerSocket(port);
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		while(true) {	
			try {
				Socket incomingRequest = serverSocket.accept();
				
				inputStream = incomingRequest.getInputStream();
				outputStream = incomingRequest.getOutputStream();
				
				ServerCommand command = dispatchCommand(inputStream);
				command.setInputStream(inputStream);
				command.setOutputStream(outputStream);
				command.run();
				StreamUtil.closeSocket(inputStream);
			
			} 
			catch(ServerException se) {
				System.out.println(se.getMessage());
				StreamUtil.sendError(se.getMessage(), outputStream);
				StreamUtil.closeSocket(inputStream);
			} 
			catch(Exception e) {
				System.out.println("Exception encountered while executing the request "+ e.getMessage());
				e.printStackTrace();
				StreamUtil.closeSocket(inputStream);
			}
		}	
	}
	
	private ServerCommand dispatchCommand(InputStream inputStream) throws ServerException 
	{
		try
		{
			String commandString = StreamUtil.readLine(inputStream);
			if ("write".equalsIgnoreCase(commandString)) {
				WriteCommand writeCommand = new WriteCommand(); 			   
			    return writeCommand;
			}
			else if ("read".equalsIgnoreCase(commandString)) {
				ReadCommand readCommand = new ReadCommand(); 			   
			    return readCommand;
			}
			else if ("delete".equalsIgnoreCase(commandString)) {
				DeleteCommand deleteCommand = new DeleteCommand();
				return deleteCommand; 
			}
			else if ("directory".equalsIgnoreCase(commandString)) {
				DirectoryCommand directoryCommand = new DirectoryCommand();
				return directoryCommand; 
			}
			else {
			    // Handle unknown request error
				throw new ServerException("Unknown Command " + commandString);
			} 
		}
		catch(IOException e)
		{
			// Handle IOException from StreamUtil.readLine
		}
		
		return null;
	}
	
	public static void main(String[] args) 
	{
		DatastoreServer server = new DatastoreServer();
		try {
			server.startup();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
