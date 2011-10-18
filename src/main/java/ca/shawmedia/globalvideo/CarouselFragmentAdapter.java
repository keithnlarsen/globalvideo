package ca.shawmedia.globalvideo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ca.shawmedia.globalvideo.models.RotatorContent;

import java.util.List;

class CarouselFragmentAdapter extends FragmentPagerAdapter {

    protected List<RotatorContent> rotatorContents = null;

	public CarouselFragmentAdapter(FragmentManager fm, List<RotatorContent> rotatorContents) {
		super(fm);
        this.rotatorContents = rotatorContents;
	}

	@Override
	public Fragment getItem(int position) {
		return CarouselFragment.newInstance(rotatorContents.get(position));
	}

	@Override
	public int getCount() {
		return rotatorContents.size();
	}
}