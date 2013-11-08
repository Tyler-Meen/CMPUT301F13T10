package cmput301f13t10;

import cmput301f13t10.DeleteSectionDialogFragment.DeleteSectionDialogListener;
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

public class SectionChoiceDialogFragment extends DialogFragment
{
	private AddChoiceDialogListener mListener;
	private EditText mSectionTitle;
	private EditText mChoiceDescription;
	private int mSectionId;

	public interface AddChoiceDialogListener
	{
		public void onAddChoice( DialogFragment dialog );
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