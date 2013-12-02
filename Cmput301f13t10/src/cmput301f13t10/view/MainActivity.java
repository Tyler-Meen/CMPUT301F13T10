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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import cmput301f13t10.model.AdventureCache;
import cmput301f13t10.model.FileInteractor;
import cmput301f13t10.presenter.AppConstants;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import cs.ualberta.cmput301f13t10.R;

/**
 * The main menu of the app. Navigates to the library views, the help view and
 * the settings view.
 * 
 * @author Braeden Soetaert
 * 
 */
public class MainActivity extends Activity
{
	private static Context mContext;

	/**
	 * Starts up the library for editing adventures on edit button click.
	 * 
	 * @param view
	 *            the view that was clicked
	 */
	public void editAdventures( View view )
	{
		Intent intent = new Intent( this, LibraryEditView.class );
		startActivity( intent );
	}

	/**
	 * Starts up the help view on help button click.
	 * 
	 * @param view
	 *            the view that was clicked
	 */
	public void help( MenuItem menu )
	{
		Intent intent = new Intent( this, HelpView.class );
		startActivity( intent );
	}

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		mContext = this;

		setContentView( R.layout.activity_main );
		
		AdventureCache.getAdventureCache().initialize( this );

	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.help_menu, menu );

		return true;
	}

	/**
	 * Starts up the library for reading adventures on read button click.
	 * 
	 * @param view
	 *            the view that was clicked
	 */
	public void readAdventures( View view )
	{
		Intent intent = new Intent( this, LibraryView.class );
		startActivity( intent );
	}

	public static Context getContext()
	{
		return mContext;
	}

}
