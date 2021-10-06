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
import com.axiom.fulfillment.adaptor.OrderItemAdaptor;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.helper.OrderActionListner;
import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.helper.constants;
import com.axiom.fulfillment.model.UserDetails;
import com.axiom.fulfillment.model.bikerorder;
import com.axiom.fulfillment.model.deliveryorder;

import com.axiom.fulfillment.model.orderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends BaseActivity implements OrderActionListner {

    RecyclerView recyclerView;
    private OrderItemAdaptor mAdapter;
    private List<deliveryorder> orderList = new ArrayList<>();
    private List<deliveryorder> searchorderList = new ArrayList<>();
    UserSharedPreferences upref;
    String key = "";
    EditText searchtext;
    Boolean istablet=true;
    private TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        if (getIntent().hasExtra(constants.order_type))
            key = getIntent().getStringExtra(constants.order_type);
        istablet=istablet();
        recyclerView = findViewById(R.id.recycler_view);
        count=findViewById(R.id.count);
        upref = new UserSharedPreferences(this);
        getToken(this);
        searchtext = findViewById(R.id.search_order);
//        if (key != null && !key.isEmpty())
//            getorderdata();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (key.equalsIgnoreCase(constants.ORDER_PICKED))
            getSupportActionBar().setTitle(getString(R.string.Picked_screen));
        else if (key.equalsIgnoreCase(constants.ORDER_CREATED))
            getSupportActionBar().setTitle(getString(R.string.new_screen));
        else if (key.equalsIgnoreCase(constants.ORDER_DELIVERED))
            getSupportActionBar().setTitle(getString(R.string.Delivered_screen));
        else if (key.equalsIgnoreCase(constants.ORDER_CANCELLED))
            getSupportActionBar().setTitle(getString(R.string.Cancelled_screen));
        else if (key.equalsIgnoreCase(constants.ORDER_POSTPONE))
            getSupportActionBar().setTitle(getString(R.string.Postponed_screen));


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
                    mAdapter = new OrderItemAdaptor(OrderListActivity.this, orderList, OrderListActivity.this,istablet);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else if (s.length() > 0) {
                    performFiltering(searchtext.getText().toString());
                    mAdapter = new OrderItemAdaptor(OrderListActivity.this, searchorderList, OrderListActivity.this,istablet);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (key != null && !key.isEmpty()) {
            orderList.clear();
            getorderdata();
        }
    }

    private void performFiltering(String searchtext) {
        if (searchtext != null && searchtext.length() > 0) {
            searchorderList = new ArrayList<>();
            for (deliveryorder order : orderList) {
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
        ord.setstaus(key);
        ord.setOadbEmployeeId(upref.getKeyEmpCode());
        ord.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        startLoader(getString(R.string.loading), this);
        Call<bikerorder> stringCall = apiService.getorderlist(ord);
        stringCall.enqueue(new Callback<bikerorder>() {
            @Override
            public void onResponse(Call<bikerorder> call, Response<bikerorder> response) {
                stopLoader();
                if (response.isSuccessful() && response.body() != null) {
                    orderList = response.body().getBikerOrders();
                    if (orderList.size() == 0) {
                        ShowToast(getString(R.string.noorders), OrderListActivity.this);
                        finish();
                        return;
                    }
                    count.setText("Total Count : "+ orderList.size());
                    mAdapter = new OrderItemAdaptor(OrderListActivity.this, orderList, OrderListActivity.this,istablet);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<bikerorder> call, Throwable t) {
                stopLoader();
            }
        });

    }

    @Override
    public void ondeliver(int position) {
        if (searchtext.getText().length() == 0) {
            Intent intent = new Intent(OrderListActivity.this, OrderDetailActivity.class);
            intent.putExtra(constants.OABOID, orderList.get(position).getOaboId());
            intent.putExtra(constants.OADBID, orderList.get(position).getOadbId());
            intent.putExtra(constants.ORDERNO, orderList.get(position).getObohOrderNo());
            intent.putExtra(constants.ObohSeq, "");
            intent.putExtra(constants.DELIVERGPS,  orderList.get(position).getOrderDeliveredGps());
            intent.putExtra(constants.PICKGPS, orderList.get(position).getOrderPickedGps());
            intent.putExtra(constants.PICKLOCATION,  orderList.get(position).getOrdPickOrderLocation());
            intent.putExtra(constants.User_name, upref.getFirstName());
            intent.putExtra(constants.userid, upref.getUserId());
            intent.putExtra(constants.usercode, upref.getKeyUserCode());
            intent.putExtra(constants.order_type, key);
            startActivity(intent);
        } else {
            Intent intent = new Intent(OrderListActivity.this, OrderDetailActivity.class);
            intent.putExtra(constants.OABOID, searchorderList.get(position).getOaboId());
            intent.putExtra(constants.OADBID, searchorderList.get(position).getOadbId());
            intent.putExtra(constants.ORDERNO, searchorderList.get(position).getObohOrderNo());
            intent.putExtra(constants.ObohSeq, "");
            intent.putExtra(constants.DELIVERGPS,  searchorderList.get(position).getOrderDeliveredGps());
            intent.putExtra(constants.PICKLOCATION,  searchorderList.get(position).getOrdPickOrderLocation());
            intent.putExtra(constants.PICKGPS, searchorderList.get(position).getOrderPickedGps());
            intent.putExtra(constants.User_name, upref.getFirstName());
            intent.putExtra(constants.userid, upref.getUserId());
            intent.putExtra(constants.usercode, upref.getKeyUserCode());
            intent.putExtra(constants.order_type, key);
            startActivity(intent);
        }
    }
}
