package cs.ualberta.cmput301f13t10;

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
	private AdventureView mView;

	@Override
	public Dialog onCreateDialog( Bundle savedInstanceState )
	{

		AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
		LayoutInflater inflater = getActivity().getLayoutInflater();

		View view = inflater.inflate( R.layout.continue_dialog, null );

		builder.setView( view );

		mView = (AdventureView) getArguments().getSerializable( AppConstants.ADVENTURE_READ_VIEW );

		CancelButtonListener cancelListener = new CancelButtonListener();
		Button btn = (Button) view.findViewById( R.id.cancel_continue_button );
		btn.setOnClickListener( cancelListener );

		ListItemListener itemListener = new ListItemListener();
		ListView listView = (ListView) view.findViewById( android.R.id.list );
		listView.setOnItemClickListener( itemListener );

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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( view.getContext(), R.layout.choice_list_item, choices );

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
			mView.changeSection( position );
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

}
