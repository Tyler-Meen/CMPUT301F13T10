package cmput301f13t10.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Helper class used to assist with saving and loading from file
 * 
 * @author Brendan Cowan
 * 
 */
public class FileInteractor
{
	/**
	 * Save the given adventures to file. This method overwrites what is in the
	 * file with the new data.
	 * 
	 * @param adventures
	 *            The list of adventures to save to file
	 * @param fileOutputStream
	 *            The file output stream for the data
	 */
	public static void saveAdventures( ArrayList<AdventureModel> adventures, FileOutputStream fileOutputStream )
	{
		try
		{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream( fileOutputStream );

			for( AdventureModel adv : adventures )
			{
				if( adv.toSave() )
				{
					objectOutputStream.writeObject( adv );
					adv.setSave( false );
				}
			}
			fileOutputStream.close();
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		catch( NullPointerException e )
		{
			e.printStackTrace();
		}
	}

	/**
	 * Load the adventures from the given file
	 * 
	 * @param fileInputStream
	 *            The input stream to read from
	 * @return A list of all adventures in file
	 */
	public static ArrayList<AdventureModel> loadAdventures( FileInputStream fileInputStream )
	{
		ArrayList<AdventureModel> cache = new ArrayList<AdventureModel>();
		try
		{
			ObjectInputStream objectInputStream = new ObjectInputStream( fileInputStream );
			while( true )
			{
				AdventureModel adv = (AdventureModel) objectInputStream.readObject();
				cache.add( adv );
			}
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		catch( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		catch( NullPointerException e )
		{
			e.printStackTrace();
		}
		catch( ClassCastException e )
		{
			e.printStackTrace();
		}
		return cache;
	}
}
