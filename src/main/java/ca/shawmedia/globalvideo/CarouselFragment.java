package ca.shawmedia.globalvideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import ca.shawmedia.globalvideo.models.RotatorContent;

public final class CarouselFragment extends Fragment {

    private RotatorContent content;

    public static CarouselFragment newInstance(RotatorContent content) {
        CarouselFragment fragment = new CarouselFragment();
        fragment.content = content;

		return fragment;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ImageView imageView = new ImageView(getActivity());

        imageView.setImageBitmap(content.getThumbnail());

		LinearLayout layout = new LinearLayout(getActivity());
		layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
		layout.setGravity(Gravity.CENTER);
		layout.addView(imageView);

		return layout;
	}
}
