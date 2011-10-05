package ca.shawmedia.globaltv;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {

    @Test
    public void shouldHaveHappySmiles() throws Exception {
        String appName = new MyActivity().getResources().getString(R.string.app_name);
//        assertThat(appName, equalTo("MyActivity"));

        Assert.assertEquals(appName, "Hello, Android");
    }
}
