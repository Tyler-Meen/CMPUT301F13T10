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
import cmput301f13t10.view.DeleteSectionDialogFragment.DeleteSectionDialogListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cs.ualberta.cmput301f13t10.R;

/**
 * The dislog to add choice descriptions to a existing or new section
 * 
 * @author Steven Gerdes
 * 
 */
public class SectionChoiceDialogFragment extends DialogFragment
{
	/**
	 * Instance of the interface to deliver action events to.
	 */
	private AddChoiceDialogListener mListener;
	/**
	 * The section title that will be possibly edited in the section
	 */
	private EditText mSectionTitle;
	/**
	 * The choice description to be matched to the section chosen
	 */
	private EditText mChoiceDescription;
	/**
	 * The sections Id to be used to identify the selected section
	 */
	private int mSectionId;

	/**
	 * The activity that creates an instance of this dialog fragment must
	 * implement this interface in order to receive event callbacks. Each method
	 * passes the DialogFragment in case the host needs to query it.
	 * 
	 * @author Steven Gerdes
	 * 
	 */
	public interface AddChoiceDialogListener
	{
		/**
		 * Called when the user confirms the deletion of the section.
		 * 
		 * @param dialog
		 *            The dialog that was displayed.
		 */
		public void onAddChoice( DialogFragment dialog );

		/**
		 * Called when the user cancels the deletion of the section.
		 * 
		 * @param dialog
		 */
		public void onCancel( DialogFragment dialog );
	}

	public String getSectionTitle()
	{
		return mSectionTitle.getText().toString();
	}

	public String getChoiceDescription()
	{
		return mChoiceDescription.getText().toString();
	}

	@Override
	public void onAttach( Activity activity )
	{
		super.onAttach( activity );
		// Verify that the host activity implements the callback interface
		try
		{
			// Instantiate the DeleteSectionDialogListener so we can send events
			// to the host
			mListener = (AddChoiceDialogListener) activity;
		}
		catch( ClassCastException e )
		{
			// Throw an exception if the calling activity doesn't implement the
			// interface
			throw new ClassCastException( activity.toString() + " must implement NoticeDialogListener" );
		}
	}

	@Override
	public Dialog onCreateDialog( Bundle savedInstanceState )
	{

		AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
		LayoutInflater inflater = getActivity().getLayoutInflater();

		View view = inflater.inflate( R.layout.section_choice_dialog, null );
		mSectionTitle = (EditText) view.findViewById( R.id.choice_title_text );
		mChoiceDescription = (EditText) view.findViewById( R.id.choice_description_text );

		String sectionTitle = getArguments().getString( AppConstants.SECTION_TITLE );
		if( sectionTitle != null )
		{
			mSectionTitle.setText( sectionTitle );
			mSectionTitle.setFocusable( false );
			mSectionTitle.setClickable( false );
		}
		mSectionId = getArguments().getInt( AppConstants.SECTION_ID );

		builder.setPositiveButton( R.string.accept, new DialogInterface.OnClickListener()
		{
			public void onClick( DialogInterface dialog, int id )
			{
				// Send the positive button event back to the host
				// activity
				mListener.onAddChoice( SectionChoiceDialogFragment.this );
			}
		} );

		builder.setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener()
		{
			public void onClick( DialogInterface dialog, int id )
			{
				// Send the negative button event back to the host
				// activity
				mListener.onCancel( SectionChoiceDialogFragment.this );
			}
		} );

		builder.setView( view );

		return builder.create();
	}

	public int getChoiceId()
	{
		return mSectionId;
	}
}