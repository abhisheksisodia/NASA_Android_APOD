package com.abhi.nasaapodfetcher;

import android.os.Build;
import android.transition.Visibility;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abhi.nasaapodfetcher.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by asisodia on 2017-08-09.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewExists() {
        TextView imageTitle = (TextView) activity.findViewById(R.id.imageTitle);
        assertNotNull("TextView could not be found", imageTitle);
    }

    @Test
    public void validateTextViewContent() {
        TextView imageTitle = (TextView) activity.findViewById(R.id.imageTitle);
        imageTitle.setText("Test");
        assertTrue("TextView contains incorrect text",
                "Test".equals(imageTitle.getText().toString()));
    }

    @Test
    public void validateImageViewExists() {
        ImageView imageView = (ImageView) activity.findViewById(R.id.imageView);
        assertNotNull("Imageview could not be found", imageView);
    }

    @Test
    public void validateProgressBarExists() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressbar);
        assertNotNull("Progressbar could not be found", progressBar);
    }

    @Test
    public void validateShowProgressBar() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressbar);

        // set visibility to visible and assert
        activity.showProgressBar();
        assertEquals(View.VISIBLE, progressBar.getVisibility());
    }

    @Test
    public void validateHideProgressBar() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressbar);
        // set to visible first
        activity.showProgressBar();
        assertEquals(View.VISIBLE, progressBar.getVisibility());

        // set visibility to invisible and assert
        activity.hideProgressBar();
        assertEquals(View.INVISIBLE, progressBar.getVisibility());
    }

    @Test
    public void validateCorrectBadResponseErrorMessageIsShown() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressbar);
        TextView imageTitle = (TextView) activity.findViewById(R.id.imageTitle);
        activity.showProgressBar();

        activity.displayBadResponseErrorMessage();
        assertEquals(View.INVISIBLE, progressBar.getVisibility());
        assertTrue("TextView contains incorrect text",
                "Bad response from the server".equals(imageTitle.getText().toString()));
    }

    @Test
    public void validateCorrectBadNetworkErrorMessageIsShown() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressbar);
        TextView imageTitle = (TextView) activity.findViewById(R.id.imageTitle);
        activity.showProgressBar();

        activity.displayBadNetworkErrorMessage();
        assertEquals(View.INVISIBLE, progressBar.getVisibility());
        assertTrue("TextView contains incorrect text",
                "Unable to connect to the API".equals(imageTitle.getText().toString()));
    }
}
