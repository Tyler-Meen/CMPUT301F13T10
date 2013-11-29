package cmput301f13t10.model;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import cmput301f13t10.presenter.AppConstants;

public class DatabaseInteractor implements AdventureInteractor
{

	private static DatabaseInteractor mDatabaseInteractor = null;

	private DatabaseInteractor()
	{
	}

	public static DatabaseInteractor getDatabaseInteractor()
	{
		if( mDatabaseInteractor == null )
			mDatabaseInteractor = new DatabaseInteractor();
		return mDatabaseInteractor;
	}

	@Override
	public void addAdventure( AdventureModel adventure )
	{
		// Make sure the remote id is up to date.
		if( adventure.getRemoteId() == -1 )
		{
			Callback callback = new Callback( adventure )
			{

				@Override
				public void callBack( Object arg )
				{
					ArrayList<Integer> usedIds = (ArrayList<Integer>) arg;
					AdventureModel adventure = (AdventureModel) mCallbackArg;
					for( int i = 0;; i++ )
					{
						if( !usedIds.contains( i ) )
						{
							adventure.setRemoteId( i );
							break;
						}
					}

					deleteThenInsert( adventure );

				}

			};
			ESGetIdsCommand getIds = new ESGetIdsCommand( callback );
			getIds.execute( null, null );
		}
		else
		{
			deleteThenInsert( adventure );
		}

	}

	private void deleteThenInsert( AdventureModel adventure )
	{
		ESInsertCommand insert = new ESInsertCommand( adventure, null );
		ESDeleteCommand delete = new ESDeleteCommand( adventure.getLocalId(), null );
		delete.execute();
		insert.execute();
	}

	@Override
	public void getAllAdventures( Callback callback )
	{
		ArrayList<AdventureModel> adventures = new ArrayList<AdventureModel>();

		GetIdsCallback getIdsCallback = new GetIdsCallback( callback )
		{
			@Override
			public void callBack( Object arg )
			{
				ArrayList<Integer> ids = (ArrayList<Integer>) arg;

				GetCallback getCallback = new GetCallback( ids.size(), mCallback )
				{

					ArrayList<AdventureModel> mAdventures = new ArrayList<AdventureModel>();
					int mCount = 0;

					@Override
					public void callBack( Object arg )
					{
						AdventureModel adventure = (AdventureModel) arg;
						mCount++;
						if( !AdventureCache.getAdventureCache().containsRemote( adventure ) )
						{
							IdFactory.getIdFactory().assignLocalId( adventure );
							AdventureCache.getAdventureCache().addAdventure( adventure );
							mAdventures.add( adventure );
						}
						if( mCount >= mNumber )
						{
							mCallback.callBack( mAdventures );
						}
					}

				};

				for( int i : ids )
				{
					ESGetCommand getCommand = new ESGetCommand( i, getCallback );
					getCommand.execute( null, null );
				}
			}
		};

		ESGetIdsCommand getIdsCommand = new ESGetIdsCommand( getIdsCallback );
		getIdsCommand.execute( null, null );
	}

	@Override
	public void deleteAdventure( AdventureModel adventure )
	{
		ESDeleteCommand deleteCommand = new ESDeleteCommand( adventure.getLocalId(), null );
		deleteCommand.execute( null, null );
	}

	@Override
	public ArrayList<AdventureModel> getAllAdventuresSynchrounous()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAdventureById( int id, Callback callback )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public AdventureModel getAdventureByIdSynchrounous( int id )
	{
		// TODO Auto-generated method stub
		return null;
	}

	private abstract class GetIdsCallback extends Callback
	{

		protected Callback mCallback;

		public GetIdsCallback( Callback callback )
		{
			mCallback = callback;
		}

	}

	private abstract class GetCallback extends Callback
	{

		protected Callback mCallback;
		protected int mNumber;

		public GetCallback( int number, Callback callback )
		{
			mCallback = callback;
			mNumber = number;
		}

	}

}
