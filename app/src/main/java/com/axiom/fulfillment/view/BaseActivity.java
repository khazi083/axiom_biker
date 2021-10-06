package com.axiom.fulfillment.view;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.model.tokenresponse;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {

    ProgressDialog progress;
    private UserSharedPreferences userpref;
    public FirebaseAnalytics mFirebaseAnalytics;

    public void startLoader(String msg, Context ctx) {
        progress = new ProgressDialog(ctx);
        progress.setMessage(msg);
        progress.setCancelable(false);
        progress.show();
    }

    public void stopLoader() {
        try {
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public void ShowToast(String msg, Context ctx) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }


    public Boolean istablet() {
    DisplayMetrics metrics = new DisplayMetrics();
    this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
    float yInches = metrics.heightPixels / metrics.ydpi;
    float xInches = metrics.widthPixels / metrics.xdpi;
    double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        if(diagonalInches>=6.5)
            return true;
        else
            return false;

    }

    public String chnagedateformat(String date){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return output.format(input.parse(date));  // parse input
        } catch (Exception
                e) {
            e.printStackTrace();
            return date;
        }
    }


    public Boolean internetavailable( Context ctx) {
        ConnectivityManager cm= (ConnectivityManager) getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo ninfo= cm.getActiveNetworkInfo();
        if(ninfo!=null && ninfo.isConnected())
            return true;

        return false;
    }
    public void getToken(Context ctx){

        APIInterface apiService = APIClient.gettoken().create(APIInterface.class);
        userpref = new UserSharedPreferences(ctx);
        try {
            Call<tokenresponse> stringCall = apiService.getaccesstoken("password", "ecom_infibeam","@X1omT9266$");

        stringCall.enqueue(new Callback<tokenresponse>() {
            @Override
            public void onResponse(Call<tokenresponse> call, Response<tokenresponse> response) {
                if (response.isSuccessful()) {
                    tokenresponse responseString = response.body();
                    userpref.setAccessToken(responseString.getAccessToken());
//                    userpref.setKeyTokenExpiry(String.valueOf(responseString.getExpiresIn()));
//                    String temp=Calendar.getInstance().getTime().toString();
//                    userpref.setKeyTokenTime(temp);
                }

            }

            @Override
            public void onFailure(Call<tokenresponse> call, Throwable t) {
            }
        });
        }
        catch (Exception e){}
    }



    public void getTokenforpod(Context ctx)  {

        APIInterface apiService = APIClient.gettokenforpod().create(APIInterface.class);
        userpref = new UserSharedPreferences(ctx);
        try {
            Call<tokenresponse> stringCall = apiService.getaccesstoken("password", "ecom_infibeam", "@X1omT9266$");
            stringCall.enqueue(new Callback<tokenresponse>() {
                @Override
                public void onResponse(Call<tokenresponse> call, Response<tokenresponse> response) {
                    if (response.isSuccessful()) {
                        tokenresponse responseString = response.body();
                        userpref.setAccessTokenPod(responseString.getAccessToken());
//                    userpref.setKeyTokenExpiry(String.valueOf(responseString.getExpiresIn()));
//                    String temp=Calendar.getInstance().getTime().toString();
//                    userpref.setKeyTokenTime(temp);
                    }

                }

                @Override
                public void onFailure(Call<tokenresponse> call, Throwable t) {
                }
            });
        }
        catch (Exception e){}
    }
}
