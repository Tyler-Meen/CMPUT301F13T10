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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import cs.ualberta.cmput301f13t10.R;

/**
 * This is an activity that displays a list of adventures to the user. They can
 * select an adventure to view, which will launch the SectionReadView.
 * 
 * @author Aly-Khan Jamal
 * @author Braeden Soetaert
 */
public class LibraryView extends Activity implements Serializable, SearchView.OnQueryTextListener
{
	/**
	 * The adventures to display
	 */
	ArrayList<AdventureModel> adventure;

	/**
	 * The cache from which to grab the adventures
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
	private MenuItem mSearchItem;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{

		cache = AdventureCache.getAdventureCache();

		adventure = new ArrayList<AdventureModel>();

		super.onCreate( savedInstanceState );

		setContentView( R.layout.library_view );

		adventure = cache.getAllAdventures();
		populateList();

		adventureListView.setOnItemClickListener( new AdapterView.OnItemClickListener()
		{

			public void onItemClick( AdapterView<?> parentAdapter, View view, int position, long id )
			{
				AdventureId = ( (AdventureModel) parentAdapter.getItemAtPosition( position ) ).getId();
				startSectionReadView();
			}
		} );
	}

	/**
	 * Start the section read view with the start section of the start adventure
	 */
	private void startSectionReadView()
	{
		Intent intent = new Intent( this, SectionReadView.class );
		Bundle b = new Bundle();
		b.putSerializable( AppConstants.CURRENT_ADVENTURE, AdventureId );
		intent.putExtra( AppConstants.CURRENT_ADVENTURE, b );
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
	 * Start the section read view with the start section of the start adventure
	 */
	private void populateList()
	{
		adventureListView = (ListView) findViewById( R.id.adventure_read_list );

		ArrayAdapter<AdventureModel> adapter = new AdventureArrayAdapter( this, adventure );
		adventureListView.setAdapter( adapter );
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
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.library_view, menu);
	    mSearchItem = menu.findItem(R.id.action_search);
	    final SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchItem);
	    searchView.setOnQueryTextListener(this);
	    searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
	        @Override
	        public void onFocusChange(View view, boolean queryTextFocused) {
	            if(!queryTextFocused) {
	                mSearchItem.collapseActionView();
	                searchView.setQuery("", false);
	            }
	        }
	    } );
	    return super.onCreateOptionsMenu(menu);
	}

}