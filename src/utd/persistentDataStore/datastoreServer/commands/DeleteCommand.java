package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DeleteCommand extends ServerCommand {

	public void run() {
		try 
		{
			String name = StreamUtil.readLine(getInputStream());	
			FileUtil.deleteData(name);
			StreamUtil.writeLine("ok\n", getOutputStream());
		}
		catch(ServerException e)
		{
			StreamUtil.sendError(e.getMessage(), getOutputStream());
		}
		catch(IOException e)
		{
			StreamUtil.sendError(e.getMessage(), getOutputStream());
		}
		
	}

}
