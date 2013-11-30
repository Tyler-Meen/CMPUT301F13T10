package cmput301f13t10.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Logger;
import cmput301f13t10.presenter.Media;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Command for retrieving a given adventure from the database
 * @author Brendan Cowan
 *
 */
public class ESGetCommand extends AsyncTask<Void, Void, Void>
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
	 * The remote id of the adventure to get
	 */
	private int mId;
	
	/**
	 * The callback to call after the command has been executed
	 */
	private Callback mCallback;
	
	/**
	 * The adventure that has been retrieved
	 */
	private AdventureModel mAdventure;

	/**
	 * Constructor
	 * @param id The remote id of the adventure to get
	 * @param callback The callback to call after the command has been executed
	 */
	public ESGetCommand( int id, Callback callback )
	{
		mId = id;
		mCallback = callback;
	}

	/**
	 * Get the Json string from the HttpResponse
	 * @param response The response to decode
	 * @return The Json string
	 * @throws IOException If reading the response fails
	 */
	private String getEntityContent( HttpResponse response ) throws IOException
	{
		BufferedReader br = new BufferedReader( new InputStreamReader( ( response.getEntity().getContent() ) ) );
		String output;
		String json = "";
		while( ( output = br.readLine() ) != null )
		{
			json += output;
		}
		return json;
	}

	@Override
	protected Void doInBackground( Void... params )
	{

		try
		{
			HttpGet getRequest = new HttpGet( AppConstants.ES_URL + AppConstants.ES_ADVENTURE + mId + "?pretty=1" );

			getRequest.addHeader( "Accept", "application/json" );

			HttpResponse response = mHttpClient.execute( getRequest );

			String json = getEntityContent( response );

			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<AdventureModel>>()
			{
			}.getType();
			ElasticSearchResponse<AdventureModel> esResponse = mGson.fromJson( json, elasticSearchResponseType );
			mAdventure = esResponse.getSource();

		}
		catch( ClientProtocolException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute( Void result )
	{
		if( mCallback != null )
			mCallback.callBack( mAdventure );
	}

}
