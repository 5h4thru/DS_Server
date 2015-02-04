package utd.persistentDataStore.datastoreServer.commands;

import java.io.IOException;

import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.StreamUtil;

public class WriteCommand extends ServerCommand {

	public void run() {
		try
		{
			String name = StreamUtil.readLine(getInputStream());	
			int length = Integer.parseInt(StreamUtil.readLine(getInputStream()));
			byte[] data = StreamUtil.readData(length, getInputStream());
			FileUtil.writeData(name, data);		// Needs folder to exist, otherwise error
			StreamUtil.writeLine("ok\n", getOutputStream());
		}
		catch(IOException e)
		{
			StreamUtil.sendError(e.getMessage(), getOutputStream());
		}

	}

}
