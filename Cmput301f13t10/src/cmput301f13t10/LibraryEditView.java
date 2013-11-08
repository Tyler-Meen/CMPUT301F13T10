package cmput301f13t10;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import cs.ualberta.cmput301f13t10.R;

/**
 * This is an activity that displays a list of adventures to the user. They can
 * select an adventure to edit, which will launch the adventureEditView.
 * 
 * @author Aly-Khan Jamal
 * @author Braeden Soetaert
 */
public class LibraryEditView extends Activity implements Serializable, SearchView.OnQueryTextListener
{

	/**
	 * A list of adventures to display
	 */
	ArrayList<AdventureModel> adventure;

	/**
	 * The cache of adventures from which to pull adventures.
	 */
	AdventureCache cache;

	/**
	 * The list view that will display all of the adventures
	 */
	private ListView adventureListView;

	/**
	 * The adventure that was selected by the user.
	 */
	int AdventureId;

	/**
	 * The create adventure button.
	 */
	private Button btnCreateAdventure;
	private MenuItem mSearchItem;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
//		AdventureModel fake = new AdventureModel("test");
		cache = AdventureCache.getAdventureCache();

//		cache.addAdventure( fake );
		adventure = new ArrayList<AdventureModel>();

		super.onCreate( savedInstanceState );

		setContentView( R.layout.library_edit );

		adventure = cache.getAllAdventures();
		populateList();
		addListenerOnButton();
		
		adventureListView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick( AdapterView<?> parentAdapter, View view, int position, long id )
			{
				AdventureId = ( (AdventureModel) parentAdapter.getItemAtPosition( position ) ).getId();
				startAdventureEditViewId();
			}
		} );

	}

	/**
	 * Starts the adventureEditView, using the adventureId previously declaired.
	 */
	private void startAdventureEditViewId()
	{
		Intent intent = new Intent( this, AdventureEditView.class );
		intent.putExtra( AppConstants.ADVENTURE_ID, AdventureId );
		startActivity( intent );
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		adventure = cache.getAllAdventures();
		populateList();
	}

	/**
	 * Populate the list of adventures with the adventures in adventure
	 */
	private void populateList()
	{
		adventureListView = (ListView) findViewById( R.id.adventure_edit_list );
		ArrayAdapter<AdventureModel> adapter = new AdventureArrayAdapter( this, adventure );
		adventureListView.setAdapter( adapter );
	}

	/**
	 * Add the onCreateAdventure button
	 */
	public void addListenerOnButton()
	{

		btnCreateAdventure = (Button) findViewById( R.id.create_adventure_button );

		btnCreateAdventure.setOnClickListener( new OnClickListener()
		{

			@Override
			public void onClick( View v )
			{

				startAdventureEditView();
			}
		} );
	}

	/**
	 * Start the AdventureEditView with a blank new adventure.
	 */
	private void startAdventureEditView()
	{
		Intent intent = new Intent( this, AdventureEditView.class );
		startActivity( intent );
	}
	

	@Override
	public boolean onQueryTextChange( String searchText )
	{
		try
		{
			adventure = Searcher.searchBy( adventure, searchText, Searcher.sTITLE );
		}
		catch(InvalidSearchTypeException e)
		{
			Log.v("Library Search Error", Searcher.sTITLE + " not a valid search type");
			adventure = cache.getAllAdventures();
		}
		populateList();
		return true;
	}

	@Override
	public boolean onQueryTextSubmit( String arg0 )
	{
        if (mSearchItem != null) {
            mSearchItem.collapseActionView();
        }
		return false;
	}
	

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater().inflate( R.menu.library_view, menu );
		mSearchItem = menu.findItem( R.id.action_search );
		final SearchView searchView = (SearchView) MenuItemCompat.getActionView( mSearchItem );
		searchView.setOnQueryTextListener( this );
		searchView.setOnQueryTextFocusChangeListener( new View.OnFocusChangeListener()
		{
			@Override
			public void onFocusChange( View view, boolean queryTextFocused )
			{
				if( !queryTextFocused )
				{
					mSearchItem.collapseActionView();
					searchView.setQuery( "", false );
				}
			}
		} );
		return super.onCreateOptionsMenu( menu );
	}

}
