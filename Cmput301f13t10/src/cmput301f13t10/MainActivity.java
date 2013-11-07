package cmput301f13t10;

import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * The main menu of the app. Navigates to the library views, the help view and
 * the settings view.
 * 
 * @author Braeden Soetaert
 * 
 */
public class MainActivity extends Activity
{
	/**
	 * Starts up the library for editing adventures on edit button click.
	 * 
	 * @param view the view that was clicked
	 */
	public void editAdventures( View view )
	{
		Intent intent = new Intent( this, AdventureEditView.class );
		startActivity( intent );
	}

	/**
	 * Starts up the help view on help button click.
	 * 
	 * @param view the view that was clicked
	 */
	public void help( View view )
	{
		// Intent intent = new Intent(this, HelpView.class);
		// startActivity(intent);
	}

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}

	/**
	 * Starts up the library for reading adventures on read button click.
	 * 
	 * @param view the view that was clicked
	 */
	public void readAdventures( View view )
	{
		// Intent intent = new Intent(this, ReadLibraryView.class);
		// startActivity(intent);
	}

	/**
	 * Starts up the settings view on settings button click.
	 * 
	 * @param view the view that was clicked
	 */
	public void settings( View view )
	{
		// Intent intent = new Intent(this, SettingsView.class);
		// startActivity(intent);
	}

}
