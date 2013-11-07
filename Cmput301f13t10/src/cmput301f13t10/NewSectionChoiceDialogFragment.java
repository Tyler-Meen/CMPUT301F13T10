package cmput301f13t10;

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
import cs.ualberta.cmput301f13t10.R;

public class NewSectionChoiceDialogFragment	extends DialogFragment
{
		

		@Override
		public Dialog onCreateDialog( Bundle savedInstanceState )
		{

			AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
			LayoutInflater inflater = getActivity().getLayoutInflater();

			View view = inflater.inflate( R.layout.new_section_choice_dialog, null );

			builder.setView( view );

			return builder.create();
		}
}