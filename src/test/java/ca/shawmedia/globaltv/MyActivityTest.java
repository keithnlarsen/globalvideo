package ca.shawmedia.globaltv;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {
    MyActivity_ _activity;

    @Before
    public void setup() throws Exception {
        _activity = new MyActivity_();
        _activity.onCreate(null);
    }

    @Test
    public void shouldOpenMyActivity() throws Exception {
        String appName = _activity.getResources().getString(R.string.app_name);
        Assert.assertEquals(appName, "Hello, Android");
    }

    @Test
    public void shouldSetTextViewValueWhenButtonClicked() throws Exception {
        TextView actualTextView = (TextView)_activity.findViewById(R.id.myTextView);
        Button actualButton = (Button)_activity.findViewById(R.id.myButton);
        EditText actualInput = (EditText)_activity.findViewById(R.id.myInput);
        // Check that when the view starts up the text is set to the value defined in hello
        Assert.assertEquals(_activity.getResources().getString(R.string.hello), actualTextView.getText());
        // Set some text, and perform click and verify that the text changes in the TextView
        actualInput.setText("testing");
        actualButton.performClick();
        Assert.assertEquals("Hello testing", actualTextView.getText());
    }
}
