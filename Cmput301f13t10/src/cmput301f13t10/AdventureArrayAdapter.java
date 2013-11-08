package cmput301f13t10;

import java.util.ArrayList;
import java.util.List;

import cs.ualberta.cmput301f13t10.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapter for the library
 * 
 * @author Aly-Khan Jamal
 * 
 */
public class AdventureArrayAdapter extends ArrayAdapter<AdventureModel>
{
	/**
	 * The context of the adapter
	 */
	private final Context mContext;

	/**
	 * The adventures being displayed in the library
	 */
	private final ArrayList<AdventureModel> mAdventure;

	/**
	 * Constructor
	 * 
	 * @param context
	 *            The context of the ArrayAdapter
	 * @param adventure
	 *            The adventures to display in the library
	 */
	public AdventureArrayAdapter( Context context, ArrayList<AdventureModel> adventure )
	{
		super( context, R.layout.list_view_row, adventure );
		mContext = context;
		mAdventure = adventure;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View rowView = inflater.inflate( R.layout.list_view_row, parent, false );
		TextView textView = (TextView) rowView.findViewById( R.id.adventure_name );
		textView.setText( mAdventure.get( position ).getTitle() );
		return rowView;
	}

}
