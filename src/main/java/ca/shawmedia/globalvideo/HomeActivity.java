package ca.shawmedia.globalvideo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import ca.shawmedia.globalvideo.gateways.PlatformFeedsGateway;
import ca.shawmedia.globalvideo.infrastructure.WebClient;
import ca.shawmedia.globalvideo.parsers.RotatorContentParser;
import ca.shawmedia.globalvideo.services.ContentRotatorService;
import ca.shawmedia.globalvideo.view.CirclePageIndicator;


public class HomeActivity extends FragmentActivity{

    CarouselFragmentAdapter adapter;
	ViewPager pager;
    ContentRotatorService contentRotatorService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

//		mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        contentRotatorService = new ContentRotatorService(new PlatformFeedsGateway(new WebClient()), new RotatorContentParser(new WebClient()));
        adapter = new CarouselFragmentAdapter(getSupportFragmentManager(), contentRotatorService.GetRotatorContentList());

		pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(adapter);

		CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
		indicator.setViewPager(pager);
	}}
