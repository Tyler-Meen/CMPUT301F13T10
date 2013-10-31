package cs.ualberta.cmput301f13t10;

import java.util.ArrayList;

public class AdventureModel {

	private String mTitle;
	private ArrayList<SectionModel> mSections;
	private SectionModel mStartSection;

	public AdventureModel() {
		mStartSection = new SectionModel();
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}

	public String getTitle() {
		return mTitle;
	}
	
	public void setStartSection(SectionModel startSection) {
		mStartSection = startSection;
	}
	
	public SectionModel getStartSection() {
		return mStartSection;
	}
	
	public void setSections(ArrayList<SectionModel> sections){
		mSections = sections;
	}
	
	public ArrayList<SectionModel> getSections() {
		return mSections;
	}

}
