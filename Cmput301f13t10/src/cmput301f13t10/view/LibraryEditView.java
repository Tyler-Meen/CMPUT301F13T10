/*
 Copyright (c) 2013, Brendan Cowan, Tyler Meen, Steven Gerdes, Braeden Soetaert, Aly-khan Jamal
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 The views and conclusions contained in the software and documentation are those
 of the authors and should not be interpreted as representing official policies, 
 either expressed or implied, of the FreeBSD Project.
 */
package cmput301f13t10.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import cmput301f13t10.model.AdventureCache;
import cmput301f13t10.model.AdventureInteractor;
import cmput301f13t10.model.AdventureModel;
import cmput301f13t10.model.Callback;
import cmput301f13t10.model.DatabaseInteractor;
import cmput301f13t10.model.FileInteractor;
import cmput301f13t10.model.InvalidSearchTypeException;
import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Logger;
import cmput301f13t10.presenter.Searcher;
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
	ArrayList<AdventureModel> mAdventure = new ArrayList<AdventureModel>();

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

	private AdventureInteractor mAdventureInteractor;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		// AdventureModel fake = new AdventureModel("test");
		cache = AdventureCache.getAdventureCache();

		// cache.addAdventure( fake );
		mAdventure = new ArrayList<AdventureModel>();

		super.onCreate( savedInstanceState );

		setContentView( R.layout.library_edit );

		// adventure = cache.getAllAdventuresSynchrounous();

		populateList();
		addListenerOnButton();

		adventureListView.setOnItemClickListener( new OnItemClickListener()
		{

			public void onItemClick( AdapterView<?> parentAdapter, View view, int position, long id )
			{
				AdventureId = ( (AdventureModel) parentAdapter.getItemAtPosition( position ) ).getLocalId();
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
		mAdventure = cache.getAllAdventuresSynchrounous();
		populateList();
	}

	@Override
	public void onPause()
	{
		super.onPause();
		FileOutputStream fileOutputStream = null;
		try
		{
			fileOutputStream = openFileOutput( AppConstants.FILE_NAME, 0 );
		}
		catch( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileInteractor.saveAdventures( AdventureCache.getAdventureCache().getAllAdventuresSynchrounous(), fileOutputStream );
	}

	/**
	 * Populate the list of adventures with the adventures in adventure
	 */
	private void populateList()
	{
		Callback getAdventureCallback = new Callback()
		{
			@Override
			public void callBack( Object adventureList )
			{
				try
				{
					mAdventure = (ArrayList<AdventureModel>) adventureList;
					updateList();
				}
				catch( ClassCastException e )
				{
					Logger.log( "bad!", e );
				}
			}

		};
		mAdventure.clear();
		DatabaseInteractor.getDatabaseInteractor().getAllAdventures( getAdventureCallback );
		updateList();

	}

	private void updateList()
	{
		// we should always see local adventures
		for( AdventureModel adv : AdventureCache.getAdventureCache().getAllAdventuresSynchrounous() )
		{
			if( !libContains( adv ) )
				mAdventure.add( adv );
		}

		adventureListView = (ListView) findViewById( R.id.adventure_edit_list );
		ArrayAdapter<AdventureModel> adapter = new AdventureArrayAdapter( this, mAdventure );
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
			mAdventure = Searcher.searchBy( mAdventure, searchText, Searcher.sTITLE );
		}
		catch( InvalidSearchTypeException e )
		{
			Log.v( "Library Search Error", Searcher.sTITLE + " not a valid search type" );
			mAdventure = cache.getAllAdventuresSynchrounous();
		}
		populateList();
		return true;
	}

	@Override
	public boolean onQueryTextSubmit( String arg0 )
	{
		if( mSearchItem != null )
		{
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

	private boolean libContains( AdventureModel adv )
	{
		for( AdventureModel thisAdv : mAdventure )
		{
			if( thisAdv.getLocalId() == adv.getLocalId() )
				return true;
		}
		return false;
	}

	public void onUploadClick( View v )
	{

	}

}
