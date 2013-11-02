package cs.ualberta.cmput301f13t10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public class AdventureReadView extends AdventureView
{

	public void continueButton( View view )
	{
		Bundle choicesBundle = new Bundle();
		// choicesBundle.putSerializable(AppConstants.CHOICES_BUNDLE,
		// mPresenter.getChoices());

		ContinueDialogFragment dialog = new ContinueDialogFragment();
		dialog.setArguments( choicesBundle );
		dialog.show( getFragmentManager(), "" );
	}

}