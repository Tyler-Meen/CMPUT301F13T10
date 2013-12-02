package cmput301f13t10.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Logger;
import cmput301f13t10.presenter.Media;

/**
 * Command to insert an adventure into the database
 * @author Brendan Cowan
 *
 */
public class ESInsertCommand extends AsyncTask<Void, Void, Void>
{

	/**
	 * the httpClient to be used in the request
	 */
	private HttpClient mHttpClient = new DefaultHttpClient();

	/**
	 * The Gson constructor that this class will use
	 */
	private Gson mGson = new GsonBuilder().registerTypeAdapter( Media.class, new MediaSerializer<Media>() ).create();

	/**
	 * The adventure to insert into the database
	 */
	private AdventureModel mAdventure;

	/**
	 * The callback to call once the command has been executed
	 */
	private Callback mCallback;

	/**
	 * Constructor
	 * @param adventure The adventure to add
	 * @param callback The function to call once the command has completed
	 */
	public ESInsertCommand( AdventureModel adventure, Callback callback )
	{
		mAdventure = adventure;
		mCallback = callback;
	}

	@Override
	protected Void doInBackground( Void... params )
	{

		// first one for the adventure itself
		HttpPost httpPost = new HttpPost( AppConstants.ES_URL + AppConstants.ES_ADVENTURE + mAdventure.getRemoteId() );
		StringEntity stringEntity = null;
		try
		{
			stringEntity = new StringEntity( mGson.toJson( mAdventure ) );
		}
		catch( UnsupportedEncodingException e )
		{
			Logger.log( "", e );
		}
		httpPost.setHeader( "Accept", "application/json" );

		httpPost.setEntity( stringEntity );
		HttpResponse response = null;

		try
		{
			response = mHttpClient.execute( httpPost );
		}
		catch( ClientProtocolException e )
		{
			Logger.log( "", e );
		}
		catch( IOException e )
		{
			Logger.log( "", e );
		}

		// second one for the id
		HttpPost updateRequest = new HttpPost( AppConstants.ES_URL + AppConstants.ES_IDS + "/_update" );
		String query = "{\"script\" : \"ctx._source.mIds += tag\", \"params\": { \"tag\":\"" + mAdventure.getRemoteId() + "\" } }";
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
