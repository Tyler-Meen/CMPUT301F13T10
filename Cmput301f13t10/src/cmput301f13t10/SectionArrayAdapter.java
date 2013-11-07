package cmput301f13t10;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import cs.ualberta.cmput301f13t10.R;

/**
 * An adapter for the adventure edit view that makes list items with a text view
 * and a delete button.
 * 
 * @author Braeden Soetaert
 * 
 */
public class SectionArrayAdapter extends ArrayAdapter<SectionTitle>
{
	/**
	 * The context of the activity that created this adapter.
	 */
	private final Context mContext;

	/**
	 * The values that will populate this adapter.
	 */
	private final List<SectionTitle> mValues;

	/**
	 * Constructor
	 * 
	 * @param context
	 *            The context of the calling activity.
	 * @param values
	 *            The values to populate the adapter with.
	 */
	public SectionArrayAdapter( Context context, List<SectionTitle> values )
	{
		super( context, R.layout.section_adapter, values );
		mContext = context;
		mValues = values;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View rowView = inflater.inflate( R.layout.section_adapter, parent, false );

		// Set the text view to have the section's title
		TextView textView = (TextView) rowView.findViewById( R.id.section_title );
		textView.setText( mValues.get( position ).getTitle() );

		// Set the image button up to delete sections if the section is not the
		// start section.
		ImageButton cancelButton = (ImageButton) rowView.findViewById( R.id.button_delete );
		if( mValues.get( position ).isStartSection() )
		{
			cancelButton.setVisibility( View.GONE );
		}
		else
		{
			cancelButton.setFocusable( false );
			cancelButton.setTag( mValues.get( position ) );
			cancelButton.setOnClickListener( new OnClickListener()
			{

				@Override
				public void onClick( View view )
				{
					( (AdventureEditView) mContext ).deletePrompt( view );
				}

			} );
		}
		return rowView;
	}
}
