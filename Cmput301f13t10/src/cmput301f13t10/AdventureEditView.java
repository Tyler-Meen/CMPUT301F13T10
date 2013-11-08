package cmput301f13t10;

import java.util.List;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import cs.ualberta.cmput301f13t10.R;

/**
 * A view that displays an adventure's sections, allows the adventure title to
 * be changed, allows sections to be deleted and created, and provides
 * navigation to the adventures different sections.
 * 
 * @author Braeden Soetaert
 * 
 */
public class AdventureEditView extends Activity implements DeleteSectionDialogFragment.DeleteSectionDialogListener
{
	/**
	 * The title of the adventure to be displayed.
	 */
	private String mDisplayTitle;

	/**
	 * The presenter for the data that will be displayed in the view.
	 */
	private AdventurePresenter mPresenter;

	/**
	 * The list of all section titles present in the current adventure.
	 */
	private List<SectionTitle> mSectionTitles;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.adventure_edit_view );
		mSetupActionBar();
		Intent intent = getIntent();
		if( intent.hasExtra( AppConstants.ADVENTURE_ID ) )
			mPresenter = new AdventurePresenter( this, intent.getIntExtra( AppConstants.ADVENTURE_ID, -1 ) );
		else
			mPresenter = new AdventurePresenter( this );

		mDisplayTitle = mPresenter.getAdventureTitle();
		EditText title = (EditText) getActionBar().getCustomView().findViewById( R.id.adventure_edit_title );
		title.setText( mDisplayTitle );
	}

	/**
	 * A handler for when a section in the list is clicked. It starts a new view
	 * to allow for the section to be edited.
	 */
	private OnItemClickListener mSectionClickedHandler = new OnItemClickListener()
	{
		/**
		 * A handler for when a section in the list is clicked. It starts a new
		 * view to allow for the section to be edited.
		 * 
		 * @param parent
		 *            the AdapterView that was clicked.
		 * @param v
		 *            the view within the AdapterView that was clicked.
		 * @param position
		 *            the position of the view in the adapter.
		 * @param id
		 *            the row id of the item that was clicked.
		 */
		public void onItemClick( AdapterView parent, View v, int position, long id )
		{
			SectionTitle selectedSection = (SectionTitle) parent.getItemAtPosition( position );
			startSectionEdit( selectedSection.getId() );
		}
	};

	/**
	 * Start a section edit view that corresponds for the given section id for
	 * the current adventure. If section id is null then do not pass a section
	 * id so a new section will be created.
	 * 
	 * @param sectionId
	 *            the id of the section to start.
	 */
	private void startSectionEdit( Integer sectionId )
	{
		Intent intent = new Intent( this, SectionEditView.class );
		intent.putExtra( AppConstants.ADVENTURE_ID, mPresenter.getAdventureId() );
		if( sectionId != null )
			intent.putExtra( AppConstants.SECTION_ID, sectionId.intValue() );
		startActivity( intent );
	}

	/**
	 * Load all sections from the presenter into the list view display.
	 */
	private void mLoadSections()
	{
		mSectionTitles = mPresenter.getSectionTitles();
		SectionArrayAdapter adapter = new SectionArrayAdapter( this, mSectionTitles );

		ListView listView = (ListView) findViewById( R.id.adventure_edit_list );
		listView.setAdapter( adapter );

		listView.setOnItemClickListener( mSectionClickedHandler );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.adventure_edit_view, menu );

		return super.onCreateOptionsMenu( menu );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		// Handle presses on the action bar items
		switch( item.getItemId() )
		{
		case R.id.action_settings:
			// Intent intent = new Intent(this, SettingsView.class);
			// startActivity(intent);
			return true;
		case R.id.action_help:
			// Intent intent = new Intent(this, HelpView.class);
			// startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected( item );
		}

	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mPresenter.setAdventureTitle( mDisplayTitle );
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mSectionTitles = mPresenter.getSectionTitles();
		mLoadSections();
	}

	/**
	 * Generate a prompt and display it to ask if the user wants to delete a
	 * section.
	 * 
	 * @param view
	 *            the view that was clicked to open the delete prompt.
	 */
	public void deletePrompt( View view )
	{
		Bundle choicesBundle = new Bundle();
		SectionTitle section = (SectionTitle) view.getTag();
		choicesBundle.putString( AppConstants.SECTION_TITLE, section.getTitle() );
		choicesBundle.putInt( AppConstants.SECTION_ID, section.getId() );

		DeleteSectionDialogFragment dialog = new DeleteSectionDialogFragment();
		dialog.setArguments( choicesBundle );
		dialog.show( getFragmentManager(), "" );
	}

	/**
	 * If the user accepted the delete action, delete the section from the
	 * current adventure and reload the sections.
	 * 
	 * @param dialog
	 *            the dialog fragment that the delete confirmation was accepted
	 *            on.
	 */
	public void onDeleteConfirm( DialogFragment dialog )
	{
		mPresenter.deleteSection( dialog.getArguments().getInt( AppConstants.SECTION_ID ) );
		mLoadSections();
	}

	/**
	 * If the user canceled the delete action, do nothing.
	 * 
	 * @param dialog
	 *            the dialog fragment that the delete confirmation was cancelled
	 *            on.
	 */
	public void onDeleteCancel( DialogFragment dialog )
	{

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void mSetupActionBar()
	{
		if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
		{
			ActionBar actionBar = getActionBar();
			// add the custom view to the action bar
			actionBar.setCustomView( R.layout.adventure_edit_action_bar );
			EditText title = (EditText) actionBar.getCustomView().findViewById( R.id.adventure_edit_title );
			title.addTextChangedListener( new TextWatcher()
			{
				public void afterTextChanged( Editable s )
				{
					mDisplayTitle = s.toString();
				}

				public void beforeTextChanged( CharSequence s, int start, int count, int after )
				{
				}

				public void onTextChanged( CharSequence s, int start, int before, int count )
				{
				}
			} );
			actionBar.setDisplayShowCustomEnabled( true );
			actionBar.setDisplayHomeAsUpEnabled( true );
		}
	}

	/**
	 * Listener for the new section button. Starts the edit section view with no
	 * section.
	 * 
	 * @param view
	 *            The view that the button is a part of
	 */
	public void launchSectionEditView( View view )
	{
		startSectionEdit( null );
	}
}
