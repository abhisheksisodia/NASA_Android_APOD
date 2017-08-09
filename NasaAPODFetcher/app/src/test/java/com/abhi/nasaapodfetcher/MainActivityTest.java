package com.abhi.nasaapodfetcher;

import android.os.Build;
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

    // The test simply checks that our TextView exists
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

    // The test simply checks that our image view exists
    @Test
    public void validateImageViewExists() {
        ImageView imageView = (ImageView) activity.findViewById(R.id.imageView);
        assertNotNull("Imageview could not be found", imageView);
    }

    @Test
    public void validateProgressBar() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressbar);
        assertNotNull("Progressbar could not be found", progressBar);
    }
}
