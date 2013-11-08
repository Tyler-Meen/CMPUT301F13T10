package cmput301f13t10;

import java.util.ArrayList;

/**
 * Factory class that generates Id numbers to be used as identifiers for objects
 * 
 * @author Brendan Cowan
 * 
 */
public class IdFactory
{
	/**
	 * The singleton IdFactory instance
	 */
	private static IdFactory mIdFactory = null;

	/**
	 * manager used to generate ids for sections
	 */
	private static IdManager mSectionIdManager = null;

	/**
	 * manager used to generate ids for sections
	 */
	private static IdManager mAdventureIdManager = null;

	/**
	 * manager used to generate ids for sections
	 */
	private static IdManager mChoiceIdManager = null;

	/**
	 * manager used to generate ids for medias
	 */
	private static IdManager mMediaIdManager = null;

	/**
	 * Singleton constructor.
	 */
	protected IdFactory()
	{
		mSectionIdManager = new IdManager();
		mAdventureIdManager = new IdManager();
		mChoiceIdManager = new IdManager();
		mMediaIdManager = new IdManager();
	}

	/**
	 * Singleton getter.
	 * 
	 * @return The static instance of the IdFactory.
	 */
	public static IdFactory getIdFactory()
	{
		if( mIdFactory == null )
			mIdFactory = new IdFactory();
		return mIdFactory;
	}

	public static IdManager getIdManager( String type )
	{
		getIdFactory();
		if( type == AppConstants.GENERATE_ADVENTURE_ID )
		{
			return mAdventureIdManager;
		}
		else if( type == AppConstants.GENERATE_CHOICE_ID )
		{
			return mChoiceIdManager;
		}
		else if( type == AppConstants.GENERATE_SECTION_ID )
		{
			return mSectionIdManager;
		}
		else if( type == AppConstants.GENERATE_MEDIA_ID )
		{
			return mMediaIdManager;
		}

		return null;

	}
}
