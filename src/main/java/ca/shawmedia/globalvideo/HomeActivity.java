package ca.shawmedia.globalvideo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import ca.shawmedia.globalvideo.services.IContentRotatorService;
import ca.shawmedia.globalvideo.view.CirclePageIndicator;
import com.google.inject.Inject;
import roboguice.activity.RoboFragmentActivity;


public class HomeActivity extends RoboFragmentActivity {

    CarouselFragmentAdapter adapter;
	ViewPager pager;

    @Inject
    IContentRotatorService contentRotatorService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home);

//		mAdapter = new TestFragmentAdapter(getSupportFragmentManager());
        adapter = new CarouselFragmentAdapter(getSupportFragmentManager(), contentRotatorService.GetRotatorContentList());

		pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(adapter);

		CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
		indicator.setViewPager(pager);
	}}
