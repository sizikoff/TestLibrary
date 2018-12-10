package info.fandroid.quizapp.quizapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import info.fandroid.quizapp.quizapplication.R;
import info.fandroid.quizapp.quizapplication.adapters.ResultAdapter;
import info.fandroid.quizapp.quizapplication.constants.AppConstants;
import info.fandroid.quizapp.quizapplication.models.quiz.ResultModel;
import info.fandroid.quizapp.quizapplication.utilities.ActivityUtilities;

public class ScoreCardActivity extends BaseActivity implements OnChartValueSelectedListener {

    private Activity mActivity;
    private Context mContext;
    private Button mBtnShare, mBtnPlayAgain;
    private TextView mScoreTextView, mWrongAnsTextView, mSkipTextView, mGreetingTextView;
    private int mScore, mWrongAns, mSkip;
    private int mQuestionsCount;
    private String mCategoryId;
    private ArrayList<ResultModel> mResultList;

    private ResultAdapter mAdapter = null;
    private RecyclerView mRecyclerResult;

    private PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        initFunctionality();
        initListener();
    }

    private void initVar() {
        mActivity = ScoreCardActivity.this;
        mContext = mActivity.getApplicationContext();

        Intent intent = getIntent();
        if (intent != null) {
            mScore = intent.getIntExtra(AppConstants.BUNDLE_KEY_SCORE, 0);
            mWrongAns = intent.getIntExtra(AppConstants.BUNDLE_KEY_WRONG_ANS, 0);
            mQuestionsCount = intent.getIntExtra(AppConstants.QUESTIONS_IN_TEST, 0);
            mCategoryId = intent.getStringExtra(AppConstants.BUNDLE_KEY_INDEX);
            mResultList = intent.getParcelableArrayListExtra(AppConstants.BUNDLE_KEY_ITEM);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_score_card);

        mRecyclerResult = (RecyclerView) findViewById(R.id.rvContentScore);
        mRecyclerResult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        mBtnShare = (Button) findViewById(R.id.btn_share);
        mBtnPlayAgain = (Button) findViewById(R.id.btn_play_again);

        mScoreTextView = (TextView) findViewById(R.id.txt_score);
        mWrongAnsTextView = (TextView) findViewById(R.id.txt_wrong);
        mGreetingTextView = (TextView) findViewById(R.id.greeting_text);

        initToolbar(true);
        setToolbarTitle(getResources().getString(R.string.score_card));
        enableUpButton();

    }

    public void initFunctionality() {


        mScoreTextView.setText(String.valueOf(mScore));
        mWrongAnsTextView.setText(String.valueOf(mWrongAns));

        float actualScore = ((float) mScore);
        switch (Math.round(actualScore)) {
            case 12:
            case 11:
            case 10:
                mGreetingTextView.setText(Html.fromHtml(getResources().getString(R.string.greeting_text3)));
                break;
            case 9:
            case 8:
            case 7:
                mGreetingTextView.setText(Html.fromHtml(getResources().getString(R.string.greeting_text2)));
                break;
            default:
                mGreetingTextView.setText(Html.fromHtml(getResources().getString(R.string.greeting_text1)));
                break;

        }

        showPieChart();

        mAdapter = new ResultAdapter(mContext, mActivity, mResultList);
        mRecyclerResult.setAdapter(mAdapter);



    }

    public void initListener() {
        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharing_text, mScore) + " https://play.google.com/store/apps/details?id=" + mActivity.getPackageName());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        });
        mBtnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtilities.getInstance().invokeCommonQuizActivity(mActivity, QuizPromptActivity.class, mCategoryId, true);
            }
        });
    }

    public void showPieChart() {
        mPieChart = (PieChart) findViewById(R.id.piechart);
        mPieChart.setUsePercentValues(true);
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setTransparentCircleRadius(AppConstants.TRANSPARENT_CIRCLE_RADIUS);
        mPieChart.setHoleRadius(AppConstants.TRANSPARENT_CIRCLE_RADIUS);
        mPieChart.setDescription(getString(R.string.score_card));
        mPieChart.animateXY(AppConstants.ANIMATION_VALUE, AppConstants.ANIMATION_VALUE);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(mScore, AppConstants.BUNDLE_KEY_ZERO_INDEX));
        yvalues.add(new Entry(mWrongAns, AppConstants.BUNDLE_KEY_SECOND_INDEX));
        PieDataSet dataSet = new PieDataSet(yvalues, AppConstants.EMPTY_STRING);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(getString(R.string.score));
        xVals.add(getString(R.string.wrong));
        xVals.add(getString(R.string.skipped));
        PieData data = new PieData(xVals, dataSet);

        // In percentage Term
        data.setValueFormatter(new PercentFormatter());
        mPieChart.setData(data);


    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
    }

    @Override
    public void onNothingSelected() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityUtilities.getInstance().invokeNewActivity(mActivity, MainActivity.class, true);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ActivityUtilities.getInstance().invokeNewActivity(mActivity, MainActivity.class, true);
    }

}
