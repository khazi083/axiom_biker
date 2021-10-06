package com.axiom.fulfillment.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.adaptor.DispatchOrderAdaptor;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.helper.OrderActionListner;
import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.helper.constants;
import com.axiom.fulfillment.model.DispatchOrderitem;
import com.axiom.fulfillment.model.PosXorders;
import com.axiom.fulfillment.model.UserDetails;
import com.axiom.fulfillment.model.orderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosxOrderList extends BaseActivity implements OrderActionListner {

    RecyclerView recyclerView;
    private DispatchOrderAdaptor mAdapter;
    private List<DispatchOrderitem> orderList = new ArrayList<>();
    private List<DispatchOrderitem> searchorderList = new ArrayList<>();
    UserSharedPreferences upref;
    String key = constants.POSX;
    EditText searchtext;
    TextView count;
    Boolean istab=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        recyclerView = findViewById(R.id.recycler_view);
        upref = new UserSharedPreferences(this);
        searchtext = findViewById(R.id.search_order);
        count=findViewById(R.id.count);

        istab=istablet();
        getToken(this);
        getorderdata();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.Posx_Screen));

        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0) {
                    mAdapter = new DispatchOrderAdaptor(PosxOrderList.this, orderList, PosxOrderList.this,istab);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else if (s.length() > 0) {
                    performFiltering(searchtext.getText().toString());
                    mAdapter = new DispatchOrderAdaptor(PosxOrderList.this, searchorderList, PosxOrderList.this,istab);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    private void performFiltering(String searchtext) {
        if (searchtext != null && searchtext.length() > 0) {
            searchorderList = new ArrayList<>();
            // search content in friend list
            for (DispatchOrderitem order : orderList) {
                if (order.getObohOrderNo().toLowerCase().contains(searchtext.toLowerCase())) {
                    searchorderList.add(order);
                }
            }

        }
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

    private void getorderdata() {

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        orderApi ord = new orderApi();
        ord.setOadbEmployeeId(upref.getKeyEmpCode());
        ord.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        startLoader(getString(R.string.loading), this);
        Call<PosXorders> stringCall = apiService.getPosxorders(ord);

        stringCall.enqueue(new Callback<PosXorders>() {
            @Override
            public void onResponse(Call<PosXorders> call, Response<PosXorders> response) {
                stopLoader();
                if (response.isSuccessful() && response.body() != null) {
                    orderList = response.body().getOrderData();
                    if (orderList.size() == 0) {
                        ShowToast(getString(R.string.noorders), PosxOrderList.this);
                        finish();
                        return;
                    }
                    count.setText("Total Count : "+ orderList.size());
                    mAdapter = new DispatchOrderAdaptor(PosxOrderList.this, orderList, PosxOrderList.this,istab);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<PosXorders> call, Throwable t) {
                stopLoader();
            }
        });

    }

    @Override
    public void ondeliver(int position) {
        if (searchtext.getText().length() == 0) {
            Intent intent = new Intent(PosxOrderList.this, OrderDetailActivity.class);
            intent.putExtra(constants.OABOID, "");
            intent.putExtra(constants.OADBID, "");
            intent.putExtra(constants.ObohSeq, orderList.get(position).getObohSeq());
            intent.putExtra(constants.ORDERNO, orderList.get(position).getObohOrderNo());
            intent.putExtra(constants.CHANNELCODE, orderList.get(position).getAclmChannelCode());
            intent.putExtra(constants.SOURCE, orderList.get(position).getObohOrderSource());
            intent.putExtra(constants.POSSTOCKLOC, orderList.get(position).getStockLocationCode());
            intent.putExtra(constants.POSORDERSTATUS, orderList.get(position).getObohOrderStatus());
            intent.putExtra(constants.POSPURCHASENO, orderList.get(position).getObohPartnerOrderNo());
            intent.putExtra(constants.PICKLOCATION,  orderList.get(position).getOrdPickOrderLocation());

            intent.putExtra(constants.User_name, upref.getFirstName());
            intent.putExtra(constants.userid, upref.getUserId());
            intent.putExtra(constants.usercode, upref.getKeyUserCode());
            intent.putExtra(constants.order_type, key);
            startActivity(intent);
        } else {
            Intent intent = new Intent(PosxOrderList.this, OrderDetailActivity.class);
            intent.putExtra(constants.OABOID, "");
            intent.putExtra(constants.OADBID, "");
            intent.putExtra(constants.ObohSeq, searchorderList.get(position).getObohSeq());
            intent.putExtra(constants.ORDERNO, searchorderList.get(position).getObohOrderNo());
            intent.putExtra(constants.CHANNELCODE, searchorderList.get(position).getStockChannelCode());
            intent.putExtra(constants.SOURCE, searchorderList.get(position).getObohOrderSource());

            intent.putExtra(constants.POSSTOCKLOC, searchorderList.get(position).getStockLocationCode());
            intent.putExtra(constants.POSORDERSTATUS, searchorderList.get(position).getObohOrderStatus());
            intent.putExtra(constants.POSPURCHASENO, searchorderList.get(position).getObohPartnerOrderNo());
            intent.putExtra(constants.PICKLOCATION,  searchorderList.get(position).getOrdPickOrderLocation());
            intent.putExtra(constants.User_name, upref.getFirstName());
            intent.putExtra(constants.userid, upref.getUserId());
            intent.putExtra(constants.usercode, upref.getKeyUserCode());
            intent.putExtra(constants.order_type, key);
            startActivity(intent);
        }

    }
}
