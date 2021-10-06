package com.axiom.fulfillment.view;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.model.UserLogin;
import com.axiom.fulfillment.model.UserLoginResponse;
import com.axiom.fulfillment.model.deviceDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import androidx.annotation.NonNull;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    EditText username, password;
    Button login, new_reg;
    UserSharedPreferences userpref;
    ImageView toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login);
        getToken(this);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnLogin);
        new_reg = findViewById(R.id.new_reg);
        toggle=findViewById(R.id.toggle);
        userpref = new UserSharedPreferences(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        if (userpref.getUserId() != 0 && !userpref.getKeyCountryCode().isEmpty()) {
            Intent home = new Intent(LoginActivity.this, HomeDrawerActivity.class);
            startActivity(home);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifylogin();
            }
        });

        new_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newreg = new Intent(LoginActivity.this, Registration.class);
                startActivity(newreg);
            }
        });


        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    }
                }, 3000);
            }
        });

        if (userpref.getfcmkey().isEmpty()) {

            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                return;
                            }
                            // Get new Instance ID token
                            userpref.setfcmkey( task.getResult().getToken());

                        }
                    });

        }
        Log.v("axiombiker fcm token",userpref.getfcmkey());

    }

    private void verifylogin() {
        if (username.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_username, Toast.LENGTH_LONG).show();
            return;
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_password, Toast.LENGTH_LONG).show();
            return;
        }
        if(!internetavailable(this)) {
            ShowToast(getString(R.string.nointernet), LoginActivity.this);
            return;
        }

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        UserLogin user = new UserLogin(username.getText().toString(), password.getText().toString());
        deviceDetails devinfo= new deviceDetails();
        devinfo.setDeviceId(userpref.getfcmkey());
        if(!userpref.getfcmkey().isEmpty())
            user.setDeviceinfo(devinfo);
        Call<UserLoginResponse> stringCall = apiService.loginapi(user);
        startLoader(getString(R.string.verify_login), this);
        stringCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
               try {
                   stopLoader();
                    if (!response.body().getIsValidDomainUser()) {
                      ShowToast(getString(R.string.adlocked), LoginActivity.this);
                       return;
                   }

                   else if (!response.body().getIsValiduser()) {
                       if(response.body().getErrorMessage()!=null && !response.body().getErrorMessage().isEmpty() &&
                       !response.body().getErrorMessage().equalsIgnoreCase("null"))
                           ShowToast(response.body().getErrorMessage(),LoginActivity.this);
                       else
                            ShowToast(getString(R.string.fullfillerror), LoginActivity.this);
                       return;
                   }
                   else if (response.body().getIsValiduser() && response.body().getUserDetails().size() > 0) {
                       UserLoginResponse responseString = response.body();
                       userpref.setRoleId(responseString.getEscalationId());
                       userpref.setUserId(responseString.getUserDetails().get(0).getAudsUserId());
                       userpref.setKeyUserCode(responseString.getUserDetails().get(0).getAudsUserCode());
                       userpref.setFirstName(responseString.getUserDetails().get(0).getAudsUserName());
                       userpref.setKeyEmpCode(responseString.getUserDetails().get(0).getAemsEmployeeCode());
                       userpref.setKeyUserRole(responseString.getEscalation());
                       userpref.setKeyCountryCode(responseString.getUserDetails().get(0).getAomsOrganizationCode());
                       Intent home = new Intent(LoginActivity.this, HomeDrawerActivity.class);
                       startActivity(home);
                   }
                   else if(response.body().getErrorMessage()!=null && !response.body().getErrorMessage().isEmpty())
                       ShowToast(response.body().getErrorMessage(),LoginActivity.this);
                   else
                       ShowToast(getString(R.string.invalid_login), LoginActivity.this);
               }
               catch (Exception e){
                   if(e.getMessage()!=null && !e.getMessage().isEmpty())
                        ShowToast(e.getMessage(), LoginActivity.this);
                }

            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), LoginActivity.this);
            }
        });

    }

}
