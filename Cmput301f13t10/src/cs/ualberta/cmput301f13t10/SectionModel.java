package cs.ualberta.cmput301f13t10;

public class SectionModel {
	
	private boolean mIsStart;
	
	public SectionModel() {
		setStart(false);
	}
	
	public SectionModel(boolean isStart){
		setStart(isStart);
	}
	
	private void setStart(boolean isStart) {
		mIsStart = isStart;
	}
}
