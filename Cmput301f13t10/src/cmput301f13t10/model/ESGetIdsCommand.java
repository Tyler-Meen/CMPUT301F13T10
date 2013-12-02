package cmput301f13t10.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Media;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Command for getting all remote ids of adventures stored in the database
 * 
 * @author Brendan Cowan
 * 
 */
public class ESGetIdsCommand extends AsyncTask<Void, Void, Void>
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
	 * The callback to call once this command has been completed
	 */
	private Callback mCallback;

	/**
	 * The returned list of ids
	 */
	private ArrayList<Integer> mIds = new ArrayList<Integer>();

	/**
	 * Constructor
	 * 
	 * @param callback
	 *            The callback to call once this command has been executed
	 */
	public ESGetIdsCommand( Callback callback )
	{
		mCallback = callback;
	}

	/**
	 * Get the Json string from the HttpResponse
	 * 
	 * @param response
	 *            The response to decode
	 * @return The Json string
	 * @throws IOException
	 *             If reading the response fails
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
			HttpGet getRequest = new HttpGet( AppConstants.ES_URL + AppConstants.ES_IDS + "?pretty=1" );

			getRequest.addHeader( "Accept", "application/json" );
			HttpResponse response = mHttpClient.execute( getRequest );

			String json = getEntityContent( response );

			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<IdList>>()
			{
			}.getType();
			ElasticSearchResponse<IdList> esResponse = null;
			try
			{
				esResponse = mGson.fromJson( json, elasticSearchResponseType );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			for( int i : esResponse.getSource().getIds() )
			{
				if( mIds.contains( i ) )
				{
					deleteId( i ); // Workaround for when the database gets too
									// many of one id.
				}
				else
				{
					mIds.add( i );
				}
			}

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

	/**
	 * Delete an id from the database
	 * 
	 * @param id
	 *            the id to remove
	 */
	private void deleteId( int id )
	{
		HttpPost updateRequest = new HttpPost( AppConstants.ES_URL + AppConstants.ES_IDS + "/_update" );
		String query = "{\"script\" : \"ctx._source.mIds.remove(tag)\", \"params\":{\"tag\":\"" + id + "\"}";
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
			mHttpClient.execute( updateRequest );
		}
		catch( ClientProtocolException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void onPostExecute( Void result )
	{
		if( mCallback != null )
			mCallback.callBack( mIds );
	}
}
