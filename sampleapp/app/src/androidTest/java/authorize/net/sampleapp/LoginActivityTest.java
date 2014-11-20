package authorize.net.sampleapp;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.junit.Test;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private LoginActivity mLoginActivity;
    private EditText mLoginIDView;

    public LoginActivityTest(){
        super(LoginActivity.class);
    }
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);

        mLoginActivity = getActivity();
        mLoginIDView = (EditText)mLoginActivity.findViewById(R.id.loginID);
    }

    public void tearDown() throws Exception {

    }

    @Test
    public void testPreconditions() {
        assertNotNull("mFirstTestActivity is null", mLoginActivity);
        assertNotNull("mFirstTestText is null", mLoginIDView);
    }
    public void testAttemptLogin() throws Exception {

    }
}