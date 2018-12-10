package info.fandroid.quizapp.quizapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdView;

import info.fandroid.quizapp.quizapplication.R;
import info.fandroid.quizapp.quizapplication.constants.InAppBillingResources;

public class ThanksActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler{

    private BillingProcessor bp;
    Context context;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        btn = (Button)findViewById(R.id.knopka);
        context  = getApplicationContext();
        bp = new BillingProcessor(context,InAppBillingResources.getRsaKey(), InAppBillingResources.getMerchantId(), this);
        bp.initialize();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(ThanksActivity.this, InAppBillingResources.getIdi());
            }
        });
    }


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        if (bp.isPurchased(productId)) {
            Toast.makeText(context, "Спасибо за вашу поддержку", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("LOG", "Oyfn Activity Result");
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }
}
