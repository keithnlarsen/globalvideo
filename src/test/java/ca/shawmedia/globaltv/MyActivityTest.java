package ca.shawmedia.globaltv;

import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {

    @Test
    public void shouldOpenMyActivity() throws Exception {
        String appName = new MyActivity().getResources().getString(R.string.app_name);
//        assertThat(appName, equalTo("MyActivity"));

        Assert.assertEquals(appName, "Hello, Android");
    }

    @Test
    public void shouldSetTextWhenButtonClicked() throws Exception {
        String appName = new MyActivity().getResources().getString(R.string.app_name);
        String textfield = new MyActivity().getResources().getString(R.id.myTextView);

        Assert.assertEquals(appName, "Hello, Android");
    }
}
