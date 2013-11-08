package cmput301f13t10;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;

/**
 * Presenter for the Adventure group of classes, as per the MVP pattern.
 * 
 * @author Brendan Cowan
 * @author Steven Gerdes
 * 
 */
public class SectionPresenter
{

	/**
	 * The view that created the presenter
	 */
	private SectionView mView;
	/**
	 * The current section that the reader is viewing
	 */
	private SectionModel mCurrentSection;
	/**
	 * The current adventure that the reader is viewing
	 */
	private AdventureModel mCurrentAdventure;

	/**
	 * Constructor
	 * 
	 * @param view
	 *            The view that created this presenter
	 */
	public SectionPresenter( SectionView view )
	{
		mView = view;
	}

	/**
	 * Set the current section that the user is viewing
	 * 
	 * @param section
	 *            The current section to set
	 */
	public void setCurrentSection( SectionModel section )
	{
		mCurrentSection = section;
		mCurrentAdventure.setSection( mCurrentSection );
		mView.updateSectionView();
	}

	/**
	 * Set the current section that the user is viewing by the sections id
	 * 
	 * @param sectionId
	 *            The id of the section to set
	 */
	public void setCurrentSectionById( Integer sectionId )
	{
		if( sectionId == null )
			setCurrentSection( new SectionModel( AppConstants.SECTION_TITLE ) );
		else
			setCurrentSection( mCurrentAdventure.getSection( sectionId ) );
	}

	/**
	 * Set the current Adventure that the user is viewing
	 * 
	 * @param adventure
	 *            The current adventure to set
	 */
	public void setCurrentAdventure( int adventure )
	{
		mCurrentAdventure = AdventureCache.getAdventureCache().getAdventureById( adventure );
		setCurrentSection( mCurrentAdventure.getCurrentSection() );
	}

	/**
	 * Get the possible choices for the current section
	 * 
	 * @return An ArrayList of strings indicating possible choices
	 */
	public ArrayList<String> getChoices()
	{
		ArrayList<String> stringChoices = null;
		try
		{
			ArrayList<SectionChoice> sectionChoices = mCurrentSection.getChoices();
			stringChoices = new ArrayList<String>();
			for( SectionChoice s : sectionChoices )
			{
				stringChoices.add( s.getUserChoice() );
			}
		}
		catch( NullPointerException e )
		{
			Logger.log( "No current section", e );
		}
		return stringChoices;
	}

	/**
	 * Get the title of the current section
	 * 
	 * @return The title of the current section
	 */
	public ArrayList<SectionTitle> getSectionTitles()
	{
		ArrayList<SectionModel> sectionChoices = mCurrentAdventure.getSections();
		ArrayList<SectionTitle> sectionTitles = new ArrayList<SectionTitle>();

		for( SectionModel s : sectionChoices )
			sectionTitles.add( new SectionTitle( s.getName(), s.getId() ) );

		return sectionTitles;
	}

	public String getSectionTitle()
	{
		return mCurrentSection.getName();
	}

	/**
	 * Set set the title of the current section, and update the view to conform
	 * with the change.
	 * 
	 * @param sectionName
	 *            The new name of the section
	 */
	public void UpdateSectionTitle( String sectionName )
	{
		mCurrentSection.setName( sectionName );
		mView.updateSectionView();
	}

	/**
	 * Get the id of the current adventure
	 * @return The id of the current adventure
	 */
	public int getAdventureId()
	{
		return mCurrentAdventure.getId();
	}

	/**
	 * Get the id of the current section
	 * @return The id of the current section
	 */
	public int getSectionId()
	{
		return mCurrentSection.getId();
	}

	/**
	 * Set the input view group to contain all media in the current section.
	 * 
	 * @param vg
	 *            The view group that is to contain the media.
	 */
	public void setCurrentSectionView( ViewGroup vg )
	{ // TODO: make the view do this instead of the presenter
		try
		{
			ArrayList<Media> medias = mCurrentSection.getMedia();
			vg.removeAllViews();
			for( Media m : medias )
			{
				vg.addView( m.toView( mView.getContext() ) );
			}
		}
		catch( NullPointerException e )
		{
			Logger.log( "No current section", e );
		}
	}

	/**
	 * Takes a bitmap (which is store in the intent) and adds it as a Media to the current section.
	 * @param editView The current SectionEditView
	 * @param data The data taken from the camera
	 */
	public void storeImage( SectionEditView editView, Intent data )
	{
		Bitmap tempBitmap = null;
		if( data.getData() != null )
		{
			try
			{
				//TODO Decouple the editView from the data.
				InputStream stream = editView.getContentResolver().openInputStream( data.getData() );
				tempBitmap = BitmapFactory.decodeStream( stream );
				stream.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		else
		{
			tempBitmap = (Bitmap) data.getExtras().get( "data" );
		}
		if( tempBitmap != null )
		{
			ImageMedia newImageMedia = new ImageMedia();
			newImageMedia.setImageBitmap( tempBitmap );
			mCurrentSection.add( newImageMedia );
		}
	}

	public boolean atLastSection()
	{
		return mCurrentSection.getChoices().isEmpty();
	}

	public void addSectionChoice( Integer id, String decisionText, String sectionTitle )
	{
		SectionModel section = mCurrentAdventure.getSection( id );
		if( section == null )
		{
			section = new SectionModel( sectionTitle );
			mCurrentAdventure.addSection( section );
		}

		mCurrentSection.addChoice( new SectionChoice( new SectionTitle( section.getName(), section.getId() ), decisionText ) );
		mView.updateSectionView();
	}

	public void removeSectionChoice( Integer id )
	{
		//mCurrentSection.removeChoice( choiceToRemove );
		mView.updateSectionView();
	}

	public ArrayList<SectionTitle> getChoiceSectionTitles()
	{
		ArrayList<SectionChoice> sectionChoices = mCurrentSection.getChoices();
		ArrayList<SectionTitle> stringChoices = new ArrayList<SectionTitle>();
		for( SectionChoice s : sectionChoices )
		{
			stringChoices.add( s.getSectionTitle() );
		}
		return stringChoices;
	}
	
}
