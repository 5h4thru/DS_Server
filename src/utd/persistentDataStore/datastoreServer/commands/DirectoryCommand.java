package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;
import java.util.List;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DirectoryCommand extends ServerCommand {

	public void run() {
		try
		{
			List<String> directory = FileUtil.directory();
			StreamUtil.writeLine("ok\n", getOutputStream());
			StreamUtil.writeLine("" + directory.size() + "\n", getOutputStream());
			for(String name : directory)
			{
				StreamUtil.writeLine(name + "\n", getOutputStream());
			}
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
