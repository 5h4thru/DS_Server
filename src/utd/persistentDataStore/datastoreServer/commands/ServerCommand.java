package utd.persistentDataStore.datastoreServer.commands;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class ServerCommand {
	
	private InputStream inputStream;
	private OutputStream outputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public OutputStream getOutputStream() {
		return outputStream;
	}
	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public abstract void run();
	
}
