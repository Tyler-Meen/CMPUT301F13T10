package cmput301f13t10.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Observable;
import java.util.Observer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ESClient implements Observer
{

	private HttpClient mHttpClient = new DefaultHttpClient();

	private Gson mGson = new Gson();

	@Override
	public void update( Observable arg0, Object arg1 )
	{
		try {
			Command command = (Command) arg1;
			command.execute();
		} catch (Exception e) {
			Logger.log( "not a command parameter", e );
		}
		
	}

}
