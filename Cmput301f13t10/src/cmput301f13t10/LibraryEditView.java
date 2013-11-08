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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import cs.ualberta.cmput301f13t10.R;

/**
 * 
 * 
 * @author Aly-khan Jamal
 * 
 * @author Braeden Soetaert
 *
 */
public class LibraryEditView extends Activity implements Serializable, SearchView.OnQueryTextListener
{

	ArrayList<AdventureModel> adventure;
	AdventureCache cache;
	private ListView adventureListView;
	int AdventureId;
	private Button btnCreateAdventure;
	private MenuItem mSearchItem;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		cache = AdventureCache.getAdventureCache();

		adventure = new ArrayList<AdventureModel>();

		super.onCreate( savedInstanceState );

		setContentView( R.layout.library_edit );

		adventure = cache.getAllAdventures();
		populateList();
		addListenerOnButton();

		adventureListView.setOnItemClickListener( new AdapterView.OnItemClickListener()
		{

			public void onItemClick( AdapterView<?> parentAdapter, View view, int position, long id )
			{
				AdventureId = ( (AdventureModel) parentAdapter.getItemAtPosition( position ) ).getId();
				startAdventureEditViewId();
			}
		} );

	}

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

	private void populateList()
	{
		adventureListView = (ListView) findViewById( R.id.adventure_edit_list );
		ArrayAdapter<AdventureModel> adapter = new AdventureArrayAdapter( this, adventure );
		adventureListView.setAdapter( adapter );
	}

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
