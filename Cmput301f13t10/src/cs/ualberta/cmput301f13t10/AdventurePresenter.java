package cs.ualberta.cmput301f13t10;

import java.util.Collection;
import java.util.Observable;

import android.app.Activity;

public class AdventurePresenter implements Presenter
{

	MVPView mView;
	SectionModel mCurrentSection;

	public AdventurePresenter( MVPView view )
	{
		mView = view;
	}

	public void setCurrentSection( SectionModel section )
	{
		mCurrentSection = section;
	}

	@Override
	public void update( Observable arg0, Object arg1 )
	{

	}

}
