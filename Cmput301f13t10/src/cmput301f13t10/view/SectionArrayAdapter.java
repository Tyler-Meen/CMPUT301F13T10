/*
Copyright (c) 2013, Brendan Cowan, Tyler Meen, Steven Gerdes, Braeden Soetaert, Aly-khan Jamal
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the FreeBSD Project.
 */
package cmput301f13t10.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import cmput301f13t10.presenter.SectionTitle;
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
		super( context, R.layout.list_item_main_text_delete_button, values );
		mContext = context;
		mValues = values;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View rowView = inflater.inflate( R.layout.list_item_main_text_delete_button, parent, false );

		// Set the text view to have the section's title
		TextView textView = (TextView) rowView.findViewById( R.id.main_text );
		textView.setText( mValues.get( position ).getTitle() );

		// Set the image button up to delete sections if the section is not the
		// start section.
		ImageButton cancelButton = (ImageButton) rowView.findViewById( R.id.delete_button );
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
