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

import java.util.ArrayList;

import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.SectionChoice;
import cmput301f13t10.presenter.SectionPresenter;
import cmput301f13t10.presenter.SectionTitle;
import cmput301f13t10.view.SectionChoiceDialogFragment.AddChoiceDialogListener;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cs.ualberta.cmput301f13t10.R;

/**
 * This is the activity that allows a user to change which choices the current
 * section is linked to
 * 
 * @author Steven Gerdes
 * 
 */
public class SectionModifyChoicesView extends Activity implements UpdatableView, AddChoiceDialogListener
{
	/**
	 * The section presenter that gives this view its correctly formatted
	 * information
	 */
	private SectionPresenter mPresenter;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.section_modify_choice_view );
		setupPresenter();
		setUpActionBar();
	}

	/**
	 * Sets up the presenter with proper adventure and section.
	 */
	private void setupPresenter()
	{
		mPresenter = new SectionPresenter( this );
		Intent intent = getIntent();
		int defaultVal = -1;
		if( intent.hasExtra( AppConstants.ADVENTURE_ID ) )
		{
			mPresenter.setCurrentAdventure( intent.getIntExtra( AppConstants.ADVENTURE_ID, defaultVal ) );
		}
		if( intent.hasExtra( AppConstants.SECTION_ID ) )
		{
			mPresenter.setCurrentSectionById( intent.getIntExtra( AppConstants.SECTION_ID, defaultVal ) );
		}
	}

	@Override
	public void updateView()
	{
		updateCurrentChoices();
		updatePossibleChoices();
	}

	/**
	 * Updates the list of possible section to use as choices
	 */
	private void updatePossibleChoices()
	{
		ArrayList<SectionTitle> choices = mPresenter.getSectionTitles();

		ListView sectionList = (ListView) findViewById( R.id.complete_section_list );
		sectionList.setAdapter( new SimpleSectionArrayAdapter( this, choices ) );

		sectionList.setOnItemClickListener( onAddSectionChoice() );
	}

	/**
	 * When some one wants to add a section it will pop up a dialog asking for
	 * more information
	 * 
	 * @return this as a click listener
	 */
	private OnItemClickListener onAddSectionChoice()
	{
		return new OnItemClickListener()
		{
			@Override
			public void onItemClick( AdapterView parent, View v, int position, long id )
			{
				SectionTitle selectedSection = (SectionTitle) parent.getItemAtPosition( position );

				Bundle choicesBundle = new Bundle();
				choicesBundle.putString( AppConstants.TITLE, selectedSection.getTitle() );
				choicesBundle.putInt( AppConstants.SECTION_ID, selectedSection.getId() );

				SectionChoiceDialogFragment dialog = new SectionChoiceDialogFragment();
				dialog.setArguments( choicesBundle );
				dialog.show( getFragmentManager(), "" );

			}
		};
	}

	/**
	 * update the currently selected choices list
	 */
	private void updateCurrentChoices()
	{
		ArrayList<SectionChoice> choices = mPresenter.getChoices();

		ListView sectionList = (ListView) findViewById( R.id.selected_section_list );
		sectionList.setAdapter( new SectionChoiceArrayAdapter( this, choices ) );

		sectionList.setOnItemClickListener( onRemoveSectionChoice() );
	}

	/**
	 * What to do when some one want to remove a section
	 * 
	 * @return this function so it can be used
	 */
	private OnItemClickListener onRemoveSectionChoice()
	{
		return new OnItemClickListener()
		{
			@Override
			public void onItemClick( AdapterView parent, View v, int position, long id )
			{
				SectionChoice selectedChoice = (SectionChoice) parent.getItemAtPosition( position );
				mPresenter.removeSectionChoice( selectedChoice );
			}
		};
	}

	/**
	 * Adds a new section choice dialog
	 * 
	 * @param view
	 *            the view that was clicked
	 */
	public void newSectionChoice( View view )
	{
		SectionChoiceDialogFragment dialog = new SectionChoiceDialogFragment();
		Bundle choicesBundle = new Bundle();
		choicesBundle.putInt( AppConstants.SECTION_ID, -1 );
		dialog.setArguments( choicesBundle );
		dialog.show( getFragmentManager(), "" );
	}

	@Override
	public void onAddChoice( DialogFragment dialog )
	{
		SectionChoiceDialogFragment choiceDialog = (SectionChoiceDialogFragment) dialog;
		mPresenter.addSectionChoice( choiceDialog.getChoiceId(), choiceDialog.getChoiceDescription(), choiceDialog.getSectionTitle() );
	}

	@Override
	public void onCancel( DialogFragment dialog )
	{
		// do nothing
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void setUpActionBar()
	{
		if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
		{
			ActionBar actionBar = getActionBar();
			// add the custom view to the action bar

			actionBar.setDisplayHomeAsUpEnabled( true );
		}
	}

	public boolean onOptionsItemSelected( MenuItem item )
	{
		switch( item.getItemId() )
		{
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected( item );
		}
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.help_menu, menu );
		return true;
	}

	/**
	 * Starts up the help view on help button click.
	 * 
	 * @param view
	 *            the view that was clicked
	 */
	public void help( MenuItem item )
	{
		Intent intent = new Intent( this, HelpView.class );
		startActivity( intent );
	}
}
