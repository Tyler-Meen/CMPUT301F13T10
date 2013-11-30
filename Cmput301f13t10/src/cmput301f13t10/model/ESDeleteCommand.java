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

/**
 * A command to delete an adventure from the database
 * @author Brendan Cowan
 *
 */
public class ESDeleteCommand extends AsyncTask<Void, Void, Void>
{
	/**
	 * The HttpClient that this command will use
	 */
	private HttpClient mHttpClient = new DefaultHttpClient();

	/**
	 * The Gson constructor that this class will use
	 */
	private Gson mGson = new GsonBuilder().registerTypeAdapter( Media.class, new MediaSerializer<Media>() ).create();

	/**
	 * The remote adventure Id that the command will delete
	 */
	private int mId;
	
	/**
	 * Callback to be called after the command has been completed
	 */
	private Callback mCallback;

	/**
	 * Constructor
	 * @param id The remote id of the adventure to delete
	 * @param callback The callback to call after the command has been executed
	 */
	public ESDeleteCommand( int id, Callback callback )
	{
		mId = id;
		mCallback = callback;
	}

	@Override
	protected Void doInBackground( Void... params )
	{

		// Delete the adventure
		HttpDelete httpDelete = new HttpDelete( AppConstants.ES_URL + AppConstants.ES_ADVENTURE + mId );
		httpDelete.addHeader( "Accept", "application/json" );

		HttpResponse response = null;
		try
		{
			response = mHttpClient.execute( httpDelete );
		}
		catch( ClientProtocolException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		HttpEntity entity = response.getEntity();
		BufferedReader br = null;
		try
		{
			br = new BufferedReader( new InputStreamReader( entity.getContent() ) );
		}
		catch( IllegalStateException e2 )
		{
			e2.printStackTrace();
		}
		catch( IOException e2 )
		{
			e2.printStackTrace();
		}
		String output;

		try
		{
			while( ( output = br.readLine() ) != null ); // just keep reading until we're done
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		// then delete the id
		HttpPost updateRequest = new HttpPost( AppConstants.ES_URL + AppConstants.ES_IDS + "/_update" );
		String query = "{\"script\" : \"ctx._source.mIds.remove(" + mId + ")\"}";
		StringEntity stringentity = null;
		try
		{
			stringentity = new StringEntity( query );
		}
		catch( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		updateRequest.setHeader( "Accept", "application/json" );

		updateRequest.setEntity( stringentity );
		try
		{
			response = mHttpClient.execute( updateRequest );
		}
		catch( ClientProtocolException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute( Void result )
	{
		if( mCallback != null )
			mCallback.callBack( null );
	}

}
