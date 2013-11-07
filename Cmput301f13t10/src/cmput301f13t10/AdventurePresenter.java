package cmput301f13t10;

import java.util.ArrayList;
import java.util.List;

/**
 * A Presenter that relays data between the adventure model and the adventure
 * view. This class creates new adventure models or loads them from the cache.
 * 
 * @author Braeden Soetaert
 * 
 */
public class AdventurePresenter
{
	/**
	 * The view that created this presenter.
	 */
	private AdventureEditView mView;

	/**
	 * The adventure model that this presenter retrieves data from.
	 */
	private AdventureModel mModel;

	/**
	 * The cache of all adventures to get the adventure model from.
	 */
	private AdventureCache mCache;

	/**
	 * Constructor
	 * 
	 * @param view
	 *            The view that creates this presenter.
	 */
	public AdventurePresenter( AdventureEditView view )
	{
		mView = view;
		mCache = AdventureCache.getAdventureCache();
		mModel = new AdventureModel();
		mCache.addAdventure( mModel );
	}

	/**
	 * Constructor
	 * 
	 * @param view
	 *            The view that creates this presenter.
	 * @param id
	 *            The id of the adventure model to load from the cache.
	 */
	public AdventurePresenter( AdventureEditView view, Integer id )
	{
		mView = view;
		mCache = AdventureCache.getAdventureCache();
		mModel = mCache.getAdventureById( id );
	}

	/**
	 * Returns the adventure id of the currently loaded adventure model.
	 * 
	 * @return The id of the currently loaded adventure model.
	 */
	public Integer getAdventureId()
	{
		return mModel.getId();
	}

	/**
	 * Returns the title of the currently loaded adventure model.
	 * 
	 * @return The title of the currently loaded adventure model.
	 */
	public String getAdventureTitle()
	{
		return mModel.getTitle();
	}

	/**
	 * Sets the title for the currently loaded adventure title.
	 * 
	 * @param title
	 *            The title to set for the adventure.
	 */
	public void setAdventureTitle( String title )
	{
		mModel.setTitle( title );
		// TODO Should save adventure when title changes.
	}

	/**
	 * Delete the section with the corresponding section id from the adventure
	 * model.
	 * 
	 * @param sectionId
	 *            The id of the section to delete.
	 */
	public void deleteSection( Integer sectionId )
	{
		mModel.deleteSection( sectionId );
		// TODO Should save adventure when section is deleted.
	}

	/**
	 * Gets the section title, id and whether or not it is the start section for
	 * every section in the current adventure.
	 * 
	 * @return A list of objects containing the section titles and ids.
	 */
	public List<SectionTitle> getSectionTitles()
	{
		SectionModel aSection;
		Integer startSectionId = mModel.getStartSection().getId();
		ArrayList<SectionModel> sections = mModel.getSections();
		List<SectionTitle> sectionTitles = new ArrayList<SectionTitle>();

		for( int i = 0; i < sections.size(); i++ )
		{
			aSection = sections.get( i );
			if( aSection.getId() == startSectionId )
			{
				sectionTitles.add( new SectionTitle( aSection.getName(), aSection.getId(), true ) );
			}
			else
			{
				sectionTitles.add( new SectionTitle( aSection.getName(), aSection.getId() ) );
			}

		}
		return sectionTitles;
	}
}
