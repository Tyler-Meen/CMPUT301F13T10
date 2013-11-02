package cs.ualberta.cmput301f13t10;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;

/**
 * View that allows the user to read and navigate an adventure.
 * 
 * @author Brendan Cowan
 * 
 */
public class AdventureReadView extends AdventureView
{

	/**
	 * Handler for the continue button of the view. Displays a dialog box which
	 * gives the user options as to how to continue with the story.
	 * 
	 * @param view
	 */
	public void continueButton( View view )
	{
		Bundle choicesBundle = new Bundle();
		ArrayList<String> choices = mPresenter.getChoices();
		choicesBundle.putStringArray( AppConstants.CHOICES_BUNDLE, (String[]) choices.toArray( new String[choices.size()] ) );
		choicesBundle.putSerializable( AppConstants.ADVENTURE_READ_VIEW, this );

		ContinueDialogFragment dialog = new ContinueDialogFragment();
		dialog.setArguments( choicesBundle );
		dialog.show( getFragmentManager(), "" );
	}

}