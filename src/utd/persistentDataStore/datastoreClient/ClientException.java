package utd.persistentDataStore.datastoreClient;

public class ClientException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ClientException(String msg)
	{
		super(msg);
	}

	public ClientException(String msg, Throwable ex)
	{
		super(msg, ex);
	}

}
