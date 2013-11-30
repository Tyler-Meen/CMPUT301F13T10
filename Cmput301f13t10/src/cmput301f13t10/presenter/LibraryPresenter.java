package cmput301f13t10.presenter;

import java.io.FileOutputStream;
import java.util.ArrayList;

import android.util.Log;
import cmput301f13t10.model.AdventureCache;
import cmput301f13t10.model.AdventureModel;
import cmput301f13t10.model.Callback;
import cmput301f13t10.model.DatabaseInteractor;
import cmput301f13t10.model.FileInteractor;
import cmput301f13t10.model.InvalidSearchTypeException;
import cmput301f13t10.view.UpdatableView;

public class LibraryPresenter
{
	/**
	 * The cache of adventures from which to pull adventures.
	 */
	private AdventureCache mCache;
	private ArrayList<AdventureModel> mAdventureList;
	private UpdatableView mView;
	
	public LibraryPresenter( UpdatableView view )
	{
		mAdventureList = new ArrayList<AdventureModel>();
		mCache = AdventureCache.getAdventureCache();
		mView = view;
	}

	public void loadData()
	{
		mAdventureList = mCache.getAllAdventuresSynchrounous();
	}

	public void saveData(FileOutputStream fileOutputStream)
	{
		FileInteractor.saveAdventures( AdventureCache.getAdventureCache().getAllAdventuresSynchrounous(), fileOutputStream );
	}

	public void updateAdventures()
	{
		// we should always see local adventures
		for( AdventureModel adv : AdventureCache.getAdventureCache().getAllAdventuresSynchrounous() )
		{
			if( !libContains( adv ) )
				mAdventureList.add( adv );
		}
	}

	public void populateList()
	{
		Callback getAdventureCallback = new Callback()
		{
			@Override
			public void callBack( Object adventureList )
			{
				try
				{
					mAdventureList = (ArrayList<AdventureModel>) adventureList;
					mView.updateView();
				}
				catch( ClassCastException e )
				{
					Logger.log( "bad!", e );
				}
			}
		};
		mAdventureList.clear();
		DatabaseInteractor.getDatabaseInteractor().getAllAdventures( getAdventureCallback );
		
	}

	public ArrayList<AdventureModel> getAdventures()
	{
		return mAdventureList;
	}
	
	private boolean libContains( AdventureModel adv )
	{
		for( AdventureModel thisAdv : mAdventureList )
		{
			if( thisAdv.getLocalId() == adv.getLocalId() )
				return true;
		}
		return false;
	}

	public void sortLibraryUsing( String searchText )
	{
		try
		{
			mAdventureList = Searcher.searchBy( mAdventureList, searchText, Searcher.sTITLE );
		}
		catch( InvalidSearchTypeException e )
		{
			Log.v( "Library Search Error", Searcher.sTITLE + " not a valid search type" );
			mAdventureList = mCache.getAllAdventuresSynchrounous();
		}
	}

	public void deleteAdventure( int localId )
	{		
		for( int i = 0; i < mAdventureList.size(); i++ )
		{	
			if( mAdventureList.get( i ).getLocalId() == localId )
			{
				mAdventureList.get( i ).setSave( false );
				AdventureCache.getAdventureCache().deleteAdventure( mAdventureList.get( i ) );
				DatabaseInteractor.getDatabaseInteractor().deleteAdventure( mAdventureList.get( i ) );
				mAdventureList.remove( i );
				break;
			}
		}
	}

	
	
}
