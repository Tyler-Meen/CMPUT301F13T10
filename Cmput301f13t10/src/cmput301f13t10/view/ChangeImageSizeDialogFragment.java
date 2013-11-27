package cmput301f13t10.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import cs.ualberta.cmput301f13t10.R;

public class ChangeImageSizeDialogFragment extends DialogFragment
{

	/**
	 * This is the maximum size an ImageMedia can be
	 */
	private final int MaxSize = 800;

	/**
	 * This is the minimum size that an ImageMedia can be
	 */
	private final int MinSize = 200;

	/**
	 * Instance of the interface to deliver action events to.
	 */
	private ChangeImageSizeDialogListener mListener;

	/**
	 * This is the index of the media that is being edited
	 */
	private int mMediaIndex;

	/**
	 * this is the size to change the new media to
	 */
	private EditText mNewMediaSize;

	public interface ChangeImageSizeDialogListener
	{
		/**
		 * Called when the user confirms the new size of an image.
		 * 
		 * @param dialog
		 *            The dialog that was displayed.
		 */
		public void onImageResize( DialogFragment dialog );

		/**
		 * Called when the user cancels the deletion of the section.
		 * 
		 * @param dialog
		 */
		public void onCancel( DialogFragment dialog );
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
			mListener = (ChangeImageSizeDialogListener) activity;
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
		View view = inflater.inflate( R.layout.image_media_dialog, null );

		mNewMediaSize = (EditText) view.findViewById( R.id.choice_description_text );

		builder.setMessage( "Please enter a new image size between " + MinSize + " and " + MaxSize + "." )
				.setPositiveButton( R.string.accept, new DialogInterface.OnClickListener()
				{
					public void onClick( DialogInterface dialog, int id )
					{
						mListener.onImageResize( ChangeImageSizeDialogFragment.this );
					}
				} ).setNegativeButton( R.string.cancel, new DialogInterface.OnClickListener()
				{
					public void onClick( DialogInterface dialog, int id )
					{
						mListener.onCancel( ChangeImageSizeDialogFragment.this );
					}
				} );
		builder.setView( view );
		// Create the AlertDialog object and return it
		return builder.create();
	}

	public int getMediaIndex()
	{
		return mMediaIndex;
	}

	public void setMediaIndex( int mMediaIndex )
	{
		this.mMediaIndex = mMediaIndex;
	}

	/**
	 * returns the size entered in the dialog. If no size entered MaxSize taken
	 * instead. This is so that no image quality is lost by downsizing the
	 * image.
	 * 
	 * @return new size entered into dialog for selected media
	 */
	public int getNewMediaSize()
	{
		if( mNewMediaSize.getText().toString().isEmpty() )
			return MaxSize;
		else
			return Integer.parseInt( mNewMediaSize.getText().toString() );
	}

	/**
	 * Max size of an ImageMedia
	 * 
	 * @return
	 */
	public int getMaxSize()
	{
		return MaxSize;
	}

	/**
	 * Minimum size of an ImageMedia
	 * 
	 * @return
	 */
	public int getMinSize()
	{
		return MinSize;
	}
}