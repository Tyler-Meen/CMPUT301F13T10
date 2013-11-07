package tests;

import cmput301f13t10.IdFactory;
import cmput301f13t10.Media;
import android.content.Context;
import android.view.View;

/**
 * A fake media for use in tests. Has no view, and no way to edit.
 * @author Brendan Cowan
 *
 */
public class MockMedia implements Media
{

	/**
	 * {@value mId}
	 */
	private int mId;

	/**
	 * Constructor.
	 */
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
