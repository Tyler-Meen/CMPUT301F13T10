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
				.setPositiveButton( R.string.delete_section_accept, new DialogInterface.OnClickListener()
				{
					public void onClick( DialogInterface dialog, int id )
					{
						// Send the positive button event back to the host
						// activity
						mListener.onDeleteConfirm( DeleteSectionDialogFragment.this );
					}
				} ).setNegativeButton( R.string.delete_section_cancel, new DialogInterface.OnClickListener()
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
