package cmput301f13t10.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Media;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ESGetIdsCommand extends AsyncTask<Void, Void, Void>
{
	private HttpClient mHttpClient = new DefaultHttpClient();

	private Gson mGson = new GsonBuilder().registerTypeAdapter( Media.class, new MediaSerializer<Media>() ).create();

	private Callback mCallback;
	private ArrayList<Integer> mIds = new ArrayList<Integer>();

	// public AdventureModel getAdventure() {

	// }

	public ESGetIdsCommand( Callback callback )
	{
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
			HttpGet getRequest = new HttpGet( AppConstants.ES_URL + AppConstants.ES_IDS + "?pretty=1" );

			getRequest.addHeader( "Accept", "application/json" );
			HttpResponse response = mHttpClient.execute( getRequest );

			String status = response.getStatusLine().toString();

			String json = getEntityContent( response );

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<IdList>>()
			{
			}.getType();
			// Now we expect to get an Ids response
			ElasticSearchResponse<IdList> esResponse = null;
			try
			{
				esResponse = mGson.fromJson( json, elasticSearchResponseType );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			// We get the ids from it!
			for( int i : esResponse.getSource().getIds() )
				mIds.add( i );// new ArrayList<Integer>(esResponse.getSource());

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
		// return ids;
	}

	@Override
	protected void onPostExecute( Void result )
	{
		if( mCallback != null )
			mCallback.callBack( mIds );
	}
}
