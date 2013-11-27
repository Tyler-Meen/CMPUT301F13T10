package cmput301f13t10.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import cmput301f13t10.presenter.AppConstants;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class ESUpdateCommand extends AsyncTask<Void, Void, Void>
{

	private HttpClient mHttpClient = new DefaultHttpClient();

	private static Gson mGson = new Gson();

	private int mId;
	private String mUpdate;
	private Callback mCallback;

	// public void updateAdventure(String str) throws ClientProtocolException,
	// IOException {

	// }

	public ESUpdateCommand( int id, String update, Callback callback )
	{
		mId = id;
		mUpdate = update;
		mCallback = callback;
	}

	//@Override
	//public Object execute() throws ClientProtocolException, IOException
	//{



	//}

	@Override
	protected Void doInBackground( Void... params )
	{
		HttpPost updateRequest = new HttpPost( AppConstants.ES_URL + AppConstants.ES_ADVENTURE + mId + "/_update" );
		String query = "{\"script\" : \"ctx._source." + mUpdate + "}";
		StringEntity stringentity = null;
		try
		{
			stringentity = new StringEntity( query );
		}
		catch( UnsupportedEncodingException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateRequest.setHeader( "Accept", "application/json" );
		updateRequest.setEntity( stringentity );
		
		HttpResponse response = null;
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
		String status = response.getStatusLine().toString();

		// String json = getEntityContent(response);
		// updateRequest.releaseConnection();

		return null;
	}

	/*public static String AdventureDiffToJsonString( AdventureModel oldAdventure, AdventureModel newAdventure )
	{
		JsonElement oldAdventureElement = mGson.toJsonTree(oldAdventure);
		JsonElement newAdventureElement = mGson.toJsonTree(oldAdventure);
		
		Set<Map.Entry<String, JsonElement>> oldSet = oldAdventureElement.getAsJsonObject().entrySet();
		Set<Map.Entry<String, JsonElement>> newSet = newAdventureElement.getAsJsonObject().entrySet();
	}*/
	
	@Override
	protected void onPostExecute( Void result ) {
		if(mCallback != null)
			mCallback.callBack( null );
	}

}


