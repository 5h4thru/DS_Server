package utd.persistentDataStore.utils;

public class ServerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ServerException(String msg) {
		super(msg);
	}
	
	public ServerException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
