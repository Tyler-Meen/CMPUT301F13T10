package cmput301f13t10;

import java.io.Serializable;
import java.util.ArrayList;
import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LibraryView extends Activity implements Serializable 
{
	ArrayList<AdventureModel> adventure;
//	ArrayAdapter<AdventureModel> adapter;
	AdventureCache cache;
	private ListView adventureListView;
	int AdventureId;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		
		cache = AdventureCache.getAdventureCache();

		adventure = new ArrayList<AdventureModel>();
		
		super.onCreate( savedInstanceState );
		
		setContentView( R.layout.library_view);
		
		adventure = cache.getAllAdventures();
		populateList();
		
		adventureListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
		
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) 
			{
				AdventureId = (( AdventureModel) parentAdapter.getItemAtPosition( position )).getId();
				startSectionReadView();
			}
		});
	}
	
	private void startSectionReadView()
	{
//		String a;
//		a = Integer.toString(AdventureId);
//		Log.v("second", a);
		Intent intent = new Intent(this, SectionReadView.class);
		Bundle b = new Bundle();
		b.putSerializable(AppConstants.CURRENT_ADVENTURE, AdventureId);
		intent.putExtra(AppConstants.CURRENT_ADVENTURE, b);
		startActivity(intent);
	}
		@Override
		protected void onResume()
		{
			super.onResume();
			adventure = cache.getAllAdventures();
			populateList();
		}
		
		private void populateList()
		{
			adventureListView = (ListView) findViewById(R.id.adventure_read_list);
			
			ArrayAdapter<AdventureModel> adapter = new AdventureArrayAdapter(this, adventure);
			adventureListView.setAdapter(adapter);	
		}
			
}