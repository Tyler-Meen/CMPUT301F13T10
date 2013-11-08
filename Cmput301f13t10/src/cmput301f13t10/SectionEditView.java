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

import java.io.File;

import cs.ualberta.cmput301f13t10.R;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * This is the view of a section it can have images or text added to it it can
 * also navigate to the modify choices menu and back to the adventure edit view
 * 
 * @author Braeden Soetaert
 * @author Tyler Meen
 * @author Steven Gerdes
 * @author Aly-khan Jamal
 * 
 */
public class SectionEditView extends Activity implements SectionView
{
	/**
	 * The presenter for the view to get data from.
	 */
	private SectionPresenter mPresenter;

	/**
	 * The title for the section that is displayed.
	 */
	private String mDisplayTitle;

	/**
	 * The list of all media in the section.
	 */
	private ArrayList<Media> mMedia;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		mPresenter = new SectionPresenter( this );

		setContentView( R.layout.section_edit_view );
		setUpActionBar();
		int defaultVal = -1;
		Integer adventureId;
		Integer sectionId;
		Intent intent = getIntent();

		adventureId = intent.getIntExtra( AppConstants.ADVENTURE_ID, defaultVal );
		if( intent.hasExtra( AppConstants.SECTION_ID ) )
			sectionId = intent.getIntExtra( AppConstants.SECTION_ID, defaultVal );
		else
			sectionId = null;

		mPresenter.setCurrentAdventure( adventureId );
		mPresenter.setCurrentSectionById( sectionId );
		intent.putExtra( AppConstants.SECTION_ID, mPresenter.getSectionId() );

		mDisplayTitle = mPresenter.getSectionTitle();
		EditText title = (EditText) getActionBar().getCustomView().findViewById( R.id.section_edit_title );
		title.setText( mDisplayTitle );
		if( mDisplayTitle.equals( AppConstants.START ) )
		{
			title.setFocusable( false );
			title.setBackgroundColor(Color.TRANSPARENT);
		}
		loadMedia();
	}

	@Override
	public void updateSectionView()
	{
		ActionBar actionBar = getActionBar();
		EditText title = (EditText) actionBar.getCustomView().findViewById( R.id.section_edit_title );

		LinearLayout scrollBox = (LinearLayout) findViewById( R.id.sectionList );
		mPresenter.setCurrentSectionView( scrollBox, true );
		title.setText( mDisplayTitle );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.section_edit_menu, menu );

		return super.onCreateOptionsMenu( menu );
	}

	@Override
	public Context getContext()
	{
		return this;
	}

	/**
	 * Listener for the modify choices button. Start the
	 * SectionModifyChoicesView with the current section and adventure
	 * 
	 * @param view
	 *            The view of the button that was clicked
	 */
	public void launchModifyChoicesAction( View view )
	{
		Intent intent = new Intent( this, SectionModifyChoicesView.class );
		intent.putExtra( AppConstants.ADVENTURE_ID, mPresenter.getAdventureId() );
		intent.putExtra( AppConstants.SECTION_ID, mPresenter.getSectionId() );
		startActivity( intent );
	}

	/**
	 * Create a new text media and update the display.
	 * 
	 * @param view
	 *            The view that was clicked.
	 */
	public void launchInsertTextAction( View view )
	{
		mMedia.add( new TextMedia() );
		loadMedia();
	}

	/**
	 * Load all media and update the display.
	 */
	public void loadMedia()
	{
		mMedia = mPresenter.getMedia();
		LinearLayout linearLayout = (LinearLayout) findViewById( R.id.sectionList );
		mPresenter.setCurrentSectionView( linearLayout, true );

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

			actionBar.setCustomView( R.layout.section_edit_action_bar );
			EditText title = (EditText) actionBar.getCustomView().findViewById( R.id.section_edit_title );
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
	 * This is the result of clicking on an option or the home button
	 */
	public boolean onOptionsItemSelected( MenuItem item )
	{
		switch( item.getItemId() )
		{
		case R.id.action_add_media:
			addImage();
			return true;
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected( item );
		}

	}

	/**
	 * Opens the camera.
	 */
	private void addImage()
	{
		Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
		startActivityForResult( intent, 0 );
	}

	/**
	 * receives the result from the camera, and passes the result to the
	 * presenter to add the image to the model.
	 */
	protected void onActivityResult( int requestCode, int resultCode, Intent data )
	{
		if( requestCode == 0 && resultCode == Activity.RESULT_OK )
		{
			mPresenter.storeImage( this, data );
			loadMedia();
		}
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mPresenter.UpdateSectionTitle( mDisplayTitle );
	}

}
