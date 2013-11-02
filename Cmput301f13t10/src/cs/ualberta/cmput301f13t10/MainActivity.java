package cs.ualberta.cmput301f13t10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		/* test code */
		Intent intent = new Intent( this, AdventureReadView.class );
		Bundle b = new Bundle();
		AdventureModel fakeAdventure = new AdventureModel( "test" );
		SectionModel fakeSection = new SectionModel( "temp", true );
		TextMedia fakeText = new TextMedia( 1 );
		fakeText.setText( "O hai wrld!" );
		fakeSection.add( fakeText );
		fakeAdventure.setStartSection( fakeSection );
		b.putSerializable( AppConstants.CURRENT_ADVENTURE, fakeAdventure );
		intent.putExtra( AppConstants.CURRENT_ADVENTURE, b );
		startActivity( intent );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}

}
