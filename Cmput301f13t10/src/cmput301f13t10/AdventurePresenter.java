package cmput301f13t10;

import java.util.ArrayList;
import java.util.List;

public class AdventurePresenter
{
	private AdventureEditView mView;
	private AdventureModel mModel;
	private AdventureCache mCache;

	public AdventurePresenter( AdventureEditView view )
	{
		mView = view;
		mCache = AdventureCache.getAdventureCache();
		mModel = new AdventureModel();
		mCache.addAdventure( mModel );
	}

	public AdventurePresenter( AdventureEditView view, Integer id )
	{
		mView = view;
		mCache = AdventureCache.getAdventureCache();
		mModel = mCache.getAdventureById( id );
	}

	public Integer getAdventureId()
	{
		return mModel.getId();
	}

	public String getAdventureTitle()
	{
		return mModel.getTitle();
	}

	public void setAdventureTitle( String title )
	{
		mModel.setTitle( title );
		// TODO Should save adventure when title changes.
	}

	public void deleteSection( Integer sectionId )
	{
		mModel.deleteSection( sectionId );
		// TODO Should save adventure when section is deleted.
	}

	public List<SectionTitle> getSectionTitles()
	{
		SectionModel aSection;
		ArrayList<SectionModel> sections = mModel.getSections();
		List<SectionTitle> sectionTitles = new ArrayList<SectionTitle>();
		for( int i = 0; i < sections.size(); i++ )
		{
			aSection = sections.get( i );
			sectionTitles.add( new SectionTitle( aSection.getName(), aSection.getId() ) );
		}
		return sectionTitles;
	}
}
