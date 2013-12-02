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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cmput301f13t10.presenter.SectionChoice;
import cs.ualberta.cmput301f13t10.R;

/**
 * Displays section choices in a list view
 * 
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
		super( context, R.layout.list_item_main_text_delete_button, values );
		mContext = context;
		mValues = values;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View rowView = inflater.inflate( R.layout.list_item_main_text_with_sub_text, parent, false );

		// Set the text view to have the section's title
		TextView sectionTitleText = (TextView) rowView.findViewById( R.id.main_text );
		sectionTitleText.setText( mValues.get( position ).getSectionTitle().getTitle() );

		// Set the text view to have the section's title
		TextView choiceDecisionText = (TextView) rowView.findViewById( R.id.sub_text );
		choiceDecisionText.setText( mValues.get( position ).getChoiceDescription() );

		return rowView;
	}
}