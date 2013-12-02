package cmput301f13t10.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import cmput301f13t10.model.HelpItem;

/**
 * This class adapts an Array of Help items into a list for an expandable list
 * view
 * 
 * @author Steven Gerdes
 * 
 */
public class ExpandableHelpListAdapter extends BaseExpandableListAdapter
{
	Context mContext;
	ArrayList<HelpItem> mTopHelpList;

	public ExpandableHelpListAdapter( Context context, ArrayList<HelpItem> helpList )
	{
		mContext = context;
		mTopHelpList = helpList;
	}

	@Override
	public boolean hasStableIds()
	{
		return false;
	}

	@Override
	public Object getChild( int groupPosition, int childPosition )
	{
		return mTopHelpList.get( groupPosition ).getSubHelpItem( childPosition );
	}

	@Override
	public long getChildId( int groupPosition, int childPosition )
	{
		return childPosition;
	}

	@Override
	public View getChildView( int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent )
	{
		if( !mTopHelpList.get( groupPosition ).hasSubItems() )
			return null;
		TextView leafHelpText = new TextView( mContext );
		leafHelpText.setText( mTopHelpList.get( groupPosition ).getSubHelpItem( childPosition ).getHelpText() );
		leafHelpText.setPadding( 50, 5, 5, 5 );
		leafHelpText.setTextSize( 20 );
		leafHelpText.setTypeface( null, Typeface.NORMAL );
		return leafHelpText;
	}

	@Override
	public int getChildrenCount( int groupPosition )
	{
		return mTopHelpList.get( groupPosition ).getSubHelpItemCount();
	}

	@Override
	public Object getGroup( int groupPosition )
	{
		return groupPosition;
	}

	@Override
	public int getGroupCount()
	{
		return mTopHelpList.size();
	}

	@Override
	public long getGroupId( int groupPosition )
	{
		return groupPosition;
	}

	@Override
	public View getGroupView( int groupPosition, boolean isExpanded, View convertView, ViewGroup parent )
	{
		TextView groupTitle = new TextView( mContext );
		groupTitle.setText( mTopHelpList.get( groupPosition ).getHelpText() );
		groupTitle.setPadding( 15, 5, 5, 5 );
		groupTitle.setTextSize( 30 );
		groupTitle.setTypeface( null, Typeface.BOLD );
		return groupTitle;
	}

	@Override
	public boolean isChildSelectable( int groupPosition, int childPosition )
	{
		return true;
	}
}
