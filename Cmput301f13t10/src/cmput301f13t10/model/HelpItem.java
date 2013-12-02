package cmput301f13t10.model;

import java.util.ArrayList;

public class HelpItem
{
	private String mHelpText;
	private ArrayList<HelpItem> mSubHelpItems;

	public HelpItem( String text, ArrayList<HelpItem> subItems )
	{
		mHelpText = text;
		mSubHelpItems = subItems;
	}

	public String getHelpText()
	{
		return mHelpText;
	}

	public ArrayList<HelpItem> getSubHelpItems()
	{
		return mSubHelpItems;
	}

	public boolean hasSubItems()
	{
		if( mSubHelpItems == null )
			return false;
		return !mSubHelpItems.isEmpty();
	}

	public int getSubHelpItemCount()
	{
		if( mSubHelpItems == null )
			return 0;
		return mSubHelpItems.size();
	}

	public HelpItem getSubHelpItem( int childPosition )
	{
		if( mSubHelpItems == null )
			return null;
		return mSubHelpItems.get( childPosition );
	}
}
