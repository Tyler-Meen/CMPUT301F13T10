package cmput301f13t10.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Media;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ESDeleteCommand extends AsyncTask<Void, Void, Void>
{
	private HttpClient mHttpClient = new DefaultHttpClient();

	private Gson mGson = new GsonBuilder().registerTypeAdapter( Media.class, new MediaSerializer<Media>() ).create();
	/**
	 * delete an entry specified by the id
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	// public void deleteAdventure() throws IOException {

	// }
	private int mId;
	private Callback mCallback;

	public ESDeleteCommand( int id, Callback callback )
	{
		mId = id;
		mCallback = callback;
	}

	// @Override
	// public Object execute() throws ClientProtocolException, IOException
	// {

	// }

	@Override
	protected Void doInBackground( Void... params )
	{

		HttpDelete httpDelete = new HttpDelete( AppConstants.ES_URL + AppConstants.ES_ADVENTURE + mId );
		httpDelete.addHeader( "Accept", "application/json" );

		HttpResponse response = null;
		try
		{
			response = mHttpClient.execute( httpDelete );
		}
		catch( ClientProtocolException e2 )
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch( IOException e2 )
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String status = response.getStatusLine().toString();

		HttpEntity entity = response.getEntity();
		BufferedReader br = null;
		try
		{
			br = new BufferedReader( new InputStreamReader( entity.getContent() ) );
		}
		catch( IllegalStateException e2 )
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch( IOException e2 )
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String output;

		try
		{
			while( ( output = br.readLine() ) != null )
			{
				// System.err.println(output);
			}
		}
		catch( IOException e2 )
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// EntityUtils.consume(entity);

		// httpDelete.releaseConnection();

		// second one for the id
		HttpPost updateRequest = new HttpPost( AppConstants.ES_URL + AppConstants.ES_IDS + "/_update" );
		String query = "{\"script\" : \"ctx._source.mIds.remove(" + mId + ")\"}";
		StringEntity stringentity = null;
		try
		{
			stringentity = new StringEntity( query );
		}
		catch( UnsupportedEncodingException e1 )
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateRequest.setHeader( "Accept", "application/json" );

		updateRequest.setEntity( stringentity );
		try
		{
			response = mHttpClient.execute( updateRequest );
		}
		catch( ClientProtocolException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status = response.getStatusLine().toString();
		return null;
	}

	@Override
	protected void onPostExecute( Void result )
	{
		if( mCallback != null )
			mCallback.callBack( null );
	}

}
