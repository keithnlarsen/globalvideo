package ca.shawmedia.globalvideo.test.unit;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.shawmedia.globalvideo.MyActivity_;
import ca.shawmedia.globalvideo.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {
    MyActivity_ _activity;
    TextView _textView;
    Button _button;
    EditText _editText;

    @Before
    public void setup() throws Exception {
        _activity = new MyActivity_();
        _activity.onCreate(null);

        _textView = (TextView)_activity.findViewById(R.id.myTextView);
        _button = (Button)_activity.findViewById(R.id.myButton);
        _editText = (EditText)_activity.findViewById(R.id.myInput);
    }

    @Test
    public void should_Open_MyActivity_With_Default_Values() throws Exception {
        // -- Setup --
        String expectedText = _activity.getResources().getString(R.string.hello);

        // -- Test --
        String actualText = _textView.getText().toString();

        // -- Verify --
        assertThat(actualText, equalTo(expectedText));
    }

    @Test
    public void should_Set_Text_View_Value_When_Button_Clicked() throws Exception {
        // -- Setup --
        String expectedText = "Hello testing!";

        // -- Test --
        _editText.setText("testing");
        _button.performClick();
        String actualText = _textView.getText().toString();

        // -- Verify --
        assertThat(actualText, equalTo(expectedText));
    }
}
