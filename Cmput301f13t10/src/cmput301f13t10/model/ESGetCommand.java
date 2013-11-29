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

public class ESGetCommand extends AsyncTask<Void, Void, Void>
{

	private HttpClient mHttpClient = new DefaultHttpClient();

	private Gson mGson = new GsonBuilder().registerTypeAdapter( Media.class, new MediaSerializer<Media>() ).create();

	// public AdventureModel getAdventure() {

	// }
	private int mId;
	private Callback mCallback;
	private AdventureModel mAdventure;

	public ESGetCommand( int id, Callback callback )
	{
		mId = id;
		mCallback = callback;
	}

	// @Override
	// public Object execute()
	// {

	// }

	String getEntityContent( HttpResponse response ) throws IOException
	{
		BufferedReader br = new BufferedReader( new InputStreamReader( ( response.getEntity().getContent() ) ) );
		String output;
		// System.err.println("Output from Server -> ");
		String json = "";
		while( ( output = br.readLine() ) != null )
		{
			// System.err.println(output);
			json += output;
		}
		// System.err.println("JSON:"+json);
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

			String status = response.getStatusLine().toString();

			String json = getEntityContent( response );

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<AdventureModel>>()
			{
			}.getType();
			// Now we expect to get an Adventure response
			ElasticSearchResponse<AdventureModel> esResponse = mGson.fromJson( json, elasticSearchResponseType );
			// We get the adventure from it!
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
		// return adventure;
	}

	@Override
	protected void onPostExecute( Void result )
	{
		if( mCallback != null )
			mCallback.callBack( mAdventure );
	}

}
