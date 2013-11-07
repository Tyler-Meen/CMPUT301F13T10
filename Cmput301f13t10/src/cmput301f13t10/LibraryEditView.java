package cmput301f13t10;

import java.io.Serializable;
import java.util.ArrayList;

import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class LibraryEditView extends Activity implements Serializable {
	
	ArrayList<AdventureModel> adventure;
	AdventureCache cache;
	private ListView adventureListView;
	int AdventureId;
	private Button btnCreateAdventure;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		cache = AdventureCache.getAdventureCache();
		
		adventure = new ArrayList<AdventureModel>();
		
		super.onCreate( savedInstanceState );
		
		setContentView( R.layout.library_edit);
		
		adventure = cache.getAllAdventures();
		populateList();
		addListenerOnButton();
		
		adventureListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
		
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) 
			{
				AdventureId = (( AdventureModel) parentAdapter.getItemAtPosition( position )).getId();
				startAdventureEditViewId();
			}
		});
		
	}
	
	private void startAdventureEditViewId()
	{
		Intent intent = new Intent(this, AdventureEditView.class);
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
		adventureListView = (ListView) findViewById(R.id.adventure_edit_list);
		ArrayAdapter<AdventureModel> adapter = new AdventureArrayAdapter(this, adventure);
		adventureListView.setAdapter(adapter);	
	}
	
	public void addListenerOnButton() {

		btnCreateAdventure = (Button) findViewById(R.id.create_adventure_button);

		btnCreateAdventure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				startAdventureEditView();				
			}
		});
	}
	
	private void startAdventureEditView()
	{
		Intent intent = new Intent(this, AdventureEditView.class);
		startActivity(intent);
	}

}
