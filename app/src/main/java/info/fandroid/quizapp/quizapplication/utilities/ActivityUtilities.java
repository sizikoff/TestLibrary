package info.fandroid.quizapp.quizapplication.utilities;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import info.fandroid.quizapp.quizapplication.constants.AppConstants;
import info.fandroid.quizapp.quizapplication.models.quiz.ResultModel;

public class ActivityUtilities {

    private static ActivityUtilities activityUtilities = null;

    public static ActivityUtilities getInstance() {
        if (activityUtilities == null) {
            activityUtilities = new ActivityUtilities();
        }
        return activityUtilities;
    }

    public void invokeNewActivity(Activity activity, Class<?> tClass, boolean shoulgFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shoulgFinish) {
            activity.finish();
        }
    }

    public void invokeCustomUrlActivity(Activity activity, Class<?> tClass, String pageTitle, String pageUrl, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstants.BUNDLE_KEY_TITLE, pageTitle);
        intent.putExtra(AppConstants.BUNDLE_KEY_URL, pageUrl);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeCommonQuizActivity(Activity activity, Class<?> tClass, String categoryId, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstants.BUNDLE_KEY_INDEX, categoryId);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeScoreCardActivity(Activity activity, Class<?> tClass, int questionsCount, int score, int wrongAns, int skip, String categoryId, ArrayList<ResultModel> resultList, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstants.BUNDLE_KEY_SCORE, score);
        intent.putExtra(AppConstants.QUESTIONS_IN_TEST, questionsCount);
        intent.putExtra(AppConstants.BUNDLE_KEY_WRONG_ANS, wrongAns);
        intent.putExtra(AppConstants.BUNDLE_KEY_SKIP, skip);
        intent.putExtra(AppConstants.BUNDLE_KEY_INDEX, categoryId);
        intent.putParcelableArrayListExtra(AppConstants.BUNDLE_KEY_ITEM, resultList);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

}
