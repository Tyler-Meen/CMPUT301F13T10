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

public class SectionArrayAdapter extends ArrayAdapter<SectionTitle>
{
	private final Context mContext;
	private final List<SectionTitle> mValues;

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
		TextView textView = (TextView) rowView.findViewById( R.id.section_title );
		textView.setText( mValues.get( position ).getTitle() );
		ImageButton cancelButton = (ImageButton) rowView.findViewById( R.id.button_delete );
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
		return rowView;
	}
}
