package cs.ualberta.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs.ualberta.cmput301f13t10.AdventurePresenter;
import cs.ualberta.cmput301f13t10.SectionModel;

public class AdventurePresenterTest {
	
	MockMVPView mMvp;
	AdventurePresenter mPresenter;
	SectionModel mSection;

	public AdventurePresenterTest() {
	}

	@Before
	public void setUp() throws Exception {
		mMvp = new MockMVPView();
		mPresenter = new AdventurePresenter(mMvp);
		
		mPresenter.setCurrentSection(mSection);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetCurrentSectionView() {
		
	}

}
