
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
package cmput301f13t10;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import cs.ualberta.cmput301f13t10.R;

/**
 * A dialog box that allows the user to confirm deletion of a section.
 * 
 * @author Braeden Soetaert
 * 
 */
public class DeleteSectionDialogFragment extends DialogFragment
{

	/**
	 * Instance of the interface to deliver action events to.
	 */
	DeleteSectionDialogListener mListener;

	/**
	 * The activity that creates an instance of this dialog fragment must
	 * implement this interface in order to receive event callbacks. Each method
	 * passes the DialogFragment in case the host needs to query it.
	 * 
	 * @author Braeden Soetaert
	 * 
	 */
	public interface DeleteSectionDialogListener
	{
		/**
		 * Called when the user confirms the deletion of the section.
		 * 
		 * @param dialog
		 *            The dialog that was displayed.
		 */
		public void onDeleteConfirm( DialogFragment dialog );

		/**
		 * Called when the user cancels the deletion of the section.
		 * 
		 * @param dialog
		 */
		public void onDeleteCancel( DialogFragment dialog );
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
			mListener = (DeleteSectionDialogListener) activity;
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
		// Build the dialog and set up the button click handlers
		AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
		String sectionTitle = getArguments().getString( AppConstants.SECTION_TITLE );
		String titleString = "Section: " + sectionTitle;

		builder.setTitle( titleString ).setMessage( R.string.delete_section_prompt )
			.setPositiveButton( R.string.accept, new DialogInterface.OnClickListener()
				{
					public void onClick( DialogInterface dialog, int id )
					{
						// Send the positive button event back to the host
						// activity
						mListener.onDeleteConfirm( DeleteSectionDialogFragment.this );
					}
				} ).setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener()
				{
					public void onClick( DialogInterface dialog, int id )
					{
						// Send the negative button event back to the host
						// activity
						mListener.onDeleteCancel( DeleteSectionDialogFragment.this );
					}
				} );
		return builder.create();
	}
}
