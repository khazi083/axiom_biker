package com.axiom.fulfillment.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.model.CommonApiResponse;
import com.axiom.fulfillment.model.newuser;
import com.axiom.fulfillment.model.userrole;
import com.axiom.fulfillment.model.userrolesList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends BaseActivity {

    List<userrole> roles;
    List<String> userroles = new ArrayList<>();
    EditText id;
    Spinner country, role;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_registration);
        id = findViewById(R.id.id);
        country = findViewById(R.id.country);
        role = findViewById(R.id.userrole);
        register = findViewById(R.id.btnreg);
        userroles.add("Select Role");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyentries();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.reg_title));
        if(!internetavailable(this)) {
            ShowToast(getString(R.string.nointernet), Registration.this);
            return;
        }
        getRoles();
        getToken(this);
    }

    private void verifyentries() {
        if (id.getText().toString().isEmpty()) {
            ShowToast(getString(R.string.id_empty), Registration.this);
            return;
        }
        if (country.getSelectedItem().toString().equalsIgnoreCase("Select Country") ||
                role.getSelectedItem().toString().equalsIgnoreCase("Select Role")) {
            ShowToast(getString(R.string.enter_details), Registration.this);
            return;
        }
        newRegistration();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newRegistration() {

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        newuser user = new newuser();
        user.setOfrEmployeeId(id.getText().toString().trim());
        if (country.getSelectedItem().toString().equalsIgnoreCase("UAE"))
            user.setOfrCountryId("AE");
        else if (country.getSelectedItem().toString().equalsIgnoreCase("KSA"))
            user.setOfrCountryId("SA");

        for (int i = 0; i < roles.size(); i++) {
            if (role.getSelectedItem().toString().equalsIgnoreCase(roles.get(i).getOeDesc()))
                user.setOeId(roles.get(i).getOeId());
        }

        user.setOfrActive("Y");

        Call<CommonApiResponse> stringCall = apiService.newRegistration(user);
        startLoader(getString(R.string.user_register), this);
        stringCall.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    ShowToast(getString(R.string.register_success), Registration.this);
                    finish();
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), Registration.this);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), Registration.this);
            }
        });
    }

    private void getRoles() {

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        Call<userrolesList> stringCall = apiService.getUserRoles();
        startLoader(getString(R.string.loading), this);
        stringCall.enqueue(new Callback<userrolesList>() {
            @Override
            public void onResponse(Call<userrolesList> call, Response<userrolesList> response) {
                stopLoader();
                if (response.body().getRoles() != null && response.body().getRoles().size() > 0) {
                    roles = response.body().getRoles();
                    for (int i = 0; i < roles.size(); i++)
                        userroles.add(roles.get(i).getOeDesc());
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, userroles);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    role.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onFailure(Call<userrolesList> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), Registration.this);
            }
        });
    }
}
