package cmput301f13t10.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileInteractor
{
	public static void saveAdventures( ArrayList<AdventureModel> adventures, FileOutputStream fileOutputStream ) {
		try {
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					fileOutputStream);
			
			
			//ArrayList<AdventureModel> adventuresToSave = new ArrayList<AdventureModel>();
			
			for( AdventureModel adv : adventures ) {
				if( adv.toSave() ) {
					objectOutputStream.writeObject( adv );
					adv.setSave( false );
				}
			}
			
			
			//objectOutputStream.writeObject(adventuresToSave);
			//objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			//
		} catch (IOException e) {
			e.printStackTrace();
			//
		} catch (NullPointerException e) {
			//
		}
	}
	
	/*public static void deleteAdventure( AdventureModel adventure, FileOutputStream fileOutputStream, FileInputStream fileInputStream ) {
		try {
			ArrayList<AdventureModel> allAdventures = loadAdventures(fileInputStream);
			allAdventures.remove( adventure );
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					fileOutputStream);
			
			for( AdventureModel adv : allAdventures )
				objectOutputStream.writeObject(adv);
			
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			//
		} catch (IOException e) {
			//
		} catch (NullPointerException e) {
			//
		}
	}*/
	
	public static ArrayList<AdventureModel> loadAdventures(FileInputStream fileInputStream) {
		ArrayList<AdventureModel> cache = new ArrayList<AdventureModel>();
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					fileInputStream);
			//ArrayList<AdventureModel> advList = (ArrayList<AdventureModel>) objectInputStream.readObject();
			while (true) {
			//for( AdventureModel adv : advList ) {
				AdventureModel adv = (AdventureModel) objectInputStream.readObject();
				cache.add(adv);
			}
			//objectInputStream.close();
			//}

		} catch (FileNotFoundException e) {
			//MessageLogger.log(e);
		} catch (IOException e) {
			e.printStackTrace();
			//MessageLogger.log(e);
		} catch (ClassNotFoundException e) {
			//MessageLogger.log(e);
		} catch (NullPointerException e) {
			//MessageLogger.log(e);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return cache;
	}
}
