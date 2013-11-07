package tests;

import cmput301f13t10.IdFactory;
import cmput301f13t10.Media;
import android.content.Context;
import android.view.View;

public class MockMedia implements Media
{

	private int mId;

	public MockMedia()
	{
		mId = IdFactory.getIdFactory().getNewId();
	}

	@Override
	public int getId()
	{
		return mId;
	}

	@Override
	public View toView( Context c )
	{
		return null;
	}

}
