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

import cmput301f13t10.presenter.AppConstants;
import cs.ualberta.cmput301f13t10.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * A dialog box that contains a list of choices for the user to pick when
 * deciding how to continue in the story.
 * 
 * @author Brendan Cowan
 * 
 */
public class ContinueDialogFragment extends DialogFragment
{

	/**
	 * The view that created this dialog box.
	 */
	private SectionReadView mView;

	@Override
	public Dialog onCreateDialog( Bundle savedInstanceState )
	{

		AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
		LayoutInflater inflater = getActivity().getLayoutInflater();

		View view = inflater.inflate( R.layout.continue_dialog, null );

		builder.setView( view );

		mView = (SectionReadView) getArguments().getSerializable( AppConstants.ADVENTURE_READ_VIEW );

		Button cancelBtn = (Button) view.findViewById( R.id.cancel_continue_button );
		cancelBtn.setOnClickListener( new CancelButtonListener() );
		
		Button randomBtn = (Button) view.findViewById( R.id.random_choice_button );
		if( mView.isRandomSet() )
			randomBtn.setOnClickListener( new RandomButtonListener() );
		else
			randomBtn.setVisibility( Button.INVISIBLE );
		
		ListView listView = (ListView) view.findViewById( android.R.id.list );
		listView.setOnItemClickListener( new ListItemListener() );

		populateList( listView );

		return builder.create();
	}

	/**
	 * Populate the list of choices
	 * 
	 * @param view
	 *            The list view to populate
	 */
	private void populateList( ListView view )
	{

		String[] choices = getArguments().getStringArray( AppConstants.CHOICES_BUNDLE );
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( view.getContext(), R.layout.list_item_main_text, choices );

		view.setAdapter( adapter );
	}

	/**
	 * Click listener for choices. Advances the story to the section chosen.
	 * 
	 * @author Brendan Cowan
	 * 
	 */
	private class ListItemListener implements AdapterView.OnItemClickListener
	{

		@Override
		public void onItemClick( AdapterView<?> parent, View view, int position, long id )
		{
			mView.changeToSection( position );
			dismiss();
		}
	}

	/**
	 * Click listener for the cancel button. Closes the dialog box.
	 * 
	 * @author Brendan Cowan
	 * 
	 */
	private class CancelButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick( View v )
		{
			dismiss();
		}

	}
	
	/**
	 * 
	 * @author Steven Gerdes
	 *
	 */
	private class RandomButtonListener implements View.OnClickListener
	{
		@Override
		public void onClick( View v)
		{
			mView.changeToRandomSection();
			dismiss();
		}
	}

}
