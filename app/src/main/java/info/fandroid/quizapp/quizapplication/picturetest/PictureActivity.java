package info.fandroid.quizapp.quizapplication.picturetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import info.fandroid.quizapp.quizapplication.R;
import info.fandroid.quizapp.quizapplication.activity.BaseActivity;
import info.fandroid.quizapp.quizapplication.activity.MainActivity;
import info.fandroid.quizapp.quizapplication.activity.NotificationListActivity;
import info.fandroid.quizapp.quizapplication.utilities.ActivityUtilities;

public class PictureActivity extends BaseActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        activity = PictureActivity.this;
        initToolbar(true);
        setToolbarTitle(getString(R.string.about_dev));
        enableUpButton();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                ActivityUtilities.getInstance().invokeNewActivity(activity, MainActivity.class, false);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
