package cmput301f13t10.model;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import cmput301f13t10.presenter.AppConstants;

/**
 * Singleton class that interacts with the online database. Can upload, download, and update adventures online.
 * @author Brendan Cowan
 *
 */
public class DatabaseInteractor
{

	/**
	 * Static instance of the database interactor
	 */
	private static DatabaseInteractor mDatabaseInteractor = null;

	/**
	 * Constructor
	 */
	private DatabaseInteractor()
	{
	}

	/**
	 * Singleton getter of the constructor
	 * @return the databaseInteractor
	 */
	public static DatabaseInteractor getDatabaseInteractor()
	{
		if( mDatabaseInteractor == null )
			mDatabaseInteractor = new DatabaseInteractor();
		return mDatabaseInteractor;
	}

	/**
	 * Add a new adventure to the database. If the adventure is already on the database (as indicated by the remoteId), it will be overwritten.
	 * @param adventure The adventure to upload
	 */
	public void addAdventure( AdventureModel adventure )
	{
		// Assign the adventure a remote id if it doesn't have one
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

	/**
	 * Delete an adventure from the database (if it exists), then reinsert it (i.e., update it).
	 * @param adventure
	 */
	private void deleteThenInsert( AdventureModel adventure )
	{
		ESInsertCommand insert = new ESInsertCommand( adventure, null );
		ESDeleteCommand delete = new ESDeleteCommand( adventure.getRemoteId(), null );
		delete.execute();
		insert.execute();
	}

	/**
	 * Return all adventures from the database
	 * @param callback The callback to call when the function completes
	 */
	public void getAllAdventures( Callback callback )
	{

		Callback getIdsCallback = new Callback( callback )
		{
			@Override
			public void callBack( Object arg )
			{
				ArrayList<Integer> ids = (ArrayList<Integer>) arg;

				GetCallback getCallback = new GetCallback( ids.size(), (Callback)mCallbackArg )
				{

					ArrayList<AdventureModel> mAdventures = new ArrayList<AdventureModel>();
					int mCount = 0;

					@Override
					public void callBack( Object arg )
					{
						AdventureModel adventure = (AdventureModel) arg;
						mCount++;
						if( adventure != null && !AdventureCache.getAdventureCache().containsRemote( adventure ) )
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

				// For each of the ids that were found, get the adventures
				for( int i : ids )
				{
					ESGetCommand getCommand = new ESGetCommand( i, getCallback );
					getCommand.execute( null, null );
				}
			}
		};

		// Get all the ids first, so we know what adventures are actually in the database
		ESGetIdsCommand getIdsCommand = new ESGetIdsCommand( getIdsCallback );
		getIdsCommand.execute( null, null );
	}

	/**
	 * Remove an adventure from the database
	 * @param adventure The adventure to delete
	 */
	public void deleteAdventure( AdventureModel adventure )
	{
		ESDeleteCommand deleteCommand = new ESDeleteCommand( adventure.getRemoteId(), null );
		deleteCommand.execute( null, null );
	}

	/**
	 * A callback that takes two arguments
	 * @author user
	 *
	 */
	private abstract class GetCallback extends Callback
	{

		/**
		 * the callback to call when this callback is done
		 */
		protected Callback mCallback;
		/**
		 * The number of ids to get adventures for
		 */
		protected int mNumber;

		/**
		 * Constructor
		 * @param number The number of ids to get adventures for
		 * @param callback the callback to call when this callback is done
		 */
		public GetCallback( int number, Callback callback )
		{
			mCallback = callback;
			mNumber = number;
		}

	}

}
