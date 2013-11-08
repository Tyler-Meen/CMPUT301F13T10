package cmput301f13t10;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cs.ualberta.cmput301f13t10.R;

/**
 * Displays section choices in a list view
 * @author Steven Gerdes
 *
 */
public class SectionChoiceArrayAdapter extends ArrayAdapter<SectionChoice>
{
	/**
	 * The context of the activity that created this adapter.
	 */
	private final Context mContext;

	/**
	 * The values that will populate this adapter.
	 */
	private final List<SectionChoice> mValues;

	/**
	 * Constructor
	 * 
	 * @param context
	 *            The context of the calling activity.
	 * @param values
	 *            The values to populate the adapter with.
	 */
	public SectionChoiceArrayAdapter( Context context, List<SectionChoice> values )
	{
		super( context, R.layout.section_adapter, values );
		mContext = context;
		mValues = values;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View rowView = inflater.inflate( R.layout.selected_section_list_item, parent, false );

		// Set the text view to have the section's title
		TextView sectionTitleText = (TextView) rowView.findViewById( R.id.section_title );
		sectionTitleText.setText( mValues.get( position ).getSectionTitle().getTitle() );

		// Set the text view to have the section's title
		TextView choiceDecisionText = (TextView) rowView.findViewById( R.id.choice_description );
		choiceDecisionText.setText( mValues.get( position ).getChoiceDescription() );
		
		return rowView;
	}
}