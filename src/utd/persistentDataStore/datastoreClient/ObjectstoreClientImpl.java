package utd.persistentDataStore.datastoreClient;

import java.net.InetAddress;


public class ObjectstoreClientImpl extends DatastoreClientImpl implements ObjectstoreClient
{

	public ObjectstoreClientImpl(InetAddress address, int port)
	{
		super(address, port);
	}

    /* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.ObjectstoreClient#writeObject(java.lang.String, java.lang.Object)
	 */
    @Override
    public void writeObject(String name, Object object) throws ClientException
    {
    }
    
    /* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.ObjectstoreClient#readObject(java.lang.String)
	 */
    @Override
    public Object readObject(String name) throws ClientException
    {
		return null;
    }

}
