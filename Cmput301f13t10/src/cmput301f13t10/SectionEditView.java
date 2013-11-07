package cmput301f13t10;

import java.io.InputStream;

import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

public class SectionEditView extends Activity implements SectionView
{
	private SectionPresenter mPresenter;
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		mPresenter = new SectionPresenter( this );

		setContentView( R.layout.section_edit_view );

		try
		{
			Intent intent = getIntent();
			Bundle intentBundle = intent.getBundleExtra( AppConstants.CURRENT_ADVENTURE );
			int adventure = intentBundle.getInt( AppConstants.CURRENT_ADVENTURE );
			mPresenter.setCurrentAdventure( adventure );
		}
		catch( Exception e )
		{
			Logger.log( "Invalid AdventureReadView instantiation bundle", e );
			return;
		}
	}

	@Override
	public void updateSectionView()
	{
		//TODO: implement
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate( R.menu.section_edit_view, menu );
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.action_add_media:
			addImage();
			return true;
		default:
			return super.onOptionsItemSelected( item );
		}
	}

	@Override
	public Context getContext()
	{
		return this;
	}
	
	private void launchModifyChoices()
	{
		Intent intent = new Intent( this, SectionModifyChoicesView.class );
		startActivity( intent );
	}
	
	public void addImage()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		startActivityForResult(intent, 0);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 0 && resultCode == Activity.RESULT_OK)
		{
			mPresenter.storeImage(this, data);
			
		}
	}

	
}





