package com.axiom.fulfillment.view;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.axiom.fulfillment.BuildConfig;
import com.axiom.fulfillment.R;
import com.axiom.fulfillment.adaptor.CustomExpandableListAdapter;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.helper.constants;
import com.axiom.fulfillment.model.Dashboard;
import com.axiom.fulfillment.model.DashboardResponse;
import com.axiom.fulfillment.model.Level1;
import com.axiom.fulfillment.model.UserDetails;
import com.axiom.fulfillment.model.bikerorder;
import com.axiom.fulfillment.model.deliveryorder;
import com.axiom.fulfillment.model.menu;
import com.axiom.fulfillment.model.menuitems;
import com.axiom.fulfillment.model.menurole;
import com.axiom.fulfillment.model.orderApi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.axiom.fulfillment.helper.constants.CHECKTYPE;
import static com.axiom.fulfillment.helper.constants.ERPRICE;
import static com.axiom.fulfillment.helper.constants.ERPSTOCK;


public class HomeDrawerActivity extends BaseActivity {

    private ExpandableListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;
    private UserSharedPreferences upref;
    String Key = "";
    PieChartView pie;
    private PieChartData piedata;
    private ColumnChartView colchart;
    ColumnChartData columnChartData;
    List<Dashboard> dashboarddata;
    Button backgraph, referesh;
    LinearLayout mainlayout, pendingorder_layout, pickedorders_layout;
    List<deliveryorder> PendingOrderList;
    List<deliveryorder> PickedOrderList;
    Button scanorder;
    private TextView Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerList = findViewById(R.id.navList);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        upref = new UserSharedPreferences(this);
        mainlayout = findViewById(R.id.graphoverview);
        pie = findViewById(R.id.piechart);
        pie.setOnValueTouchListener(new ValueTouchListener());
        pendingorder_layout = findViewById(R.id.pending_layout);
        pickedorders_layout = findViewById(R.id.picked_layout);
        colchart = findViewById(R.id.colchart);
        backgraph = findViewById(R.id.backgraph);
        scanorder=findViewById(R.id.scan);
        getToken(HomeDrawerActivity.this);
        scanorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(HomeDrawerActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
                    callPermission();
                }
                else {
                    Intent intent = new Intent(HomeDrawerActivity.this, ScanActivity.class);
                    startActivityForResult(intent, 2);
                }
            }
        });

        backgraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colchart.setVisibility(View.GONE);
                pie.setVisibility(View.VISIBLE);
                backgraph.setVisibility(View.GONE);
                generateData();
            }
        });
        referesh = findViewById(R.id.referesh);
        referesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoader(getString(R.string.refersh), HomeDrawerActivity.this);
                getToken(HomeDrawerActivity.this);
                getDashBoarddata();
                getmenuList();
                if (pendingorder_layout.getChildCount() > 0) ;
                    pendingorder_layout.removeAllViews();

                if (pickedorders_layout.getChildCount() > 0) ;
                    pickedorders_layout.removeAllViews();
                getPendingorderdata(constants.ORDER_CREATED);
                getPickedOrderData(constants.ORDER_PICKED);
            }
        });

        startLoader(getString(R.string.loading), this);
        mDrawerLayout.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDashBoarddata();
                getmenuList();

            }
        }, 2000);


        mDrawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                if (expandableListTitle.get(groupPosition).equalsIgnoreCase(getString(R.string.logout))) {
                    upref.clearSession();
                    finish();
                }

            }
        });

        mDrawerList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


                Key = "";
                String click = expandableListTitle.get(groupPosition);
                if (click.equalsIgnoreCase("Delivery Bikers") && expandableListDetail.get(click).size() > childPosition) {

                    if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Picked Orders")) {
                        Key = constants.ORDER_PICKED;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("New Orders")) {
                        Key = constants.ORDER_CREATED;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Delivered Orders")) {
                        Key = constants.ORDER_DELIVERED;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Cancelled Orders")) {
                        Key = constants.ORDER_CANCELLED;
                    }
                    else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Postponed Orders")) {
                        Key = constants.ORDER_POSTPONE;
                    }
                    if (!Key.isEmpty()) {
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(HomeDrawerActivity.this, OrderListActivity.class);
                        intent.putExtra(constants.order_type, Key);
                        startActivity(intent);
                    }
                } else if (click.equalsIgnoreCase("Order Management")) {

                    if (expandableListDetail.get(click).get(childPosition).toLowerCase().contains("stock check")) {
                        mDrawerLayout.closeDrawers();
                        Intent intent1 = new Intent(HomeDrawerActivity.this, Erp_Stock.class);
                        intent1.putExtra(CHECKTYPE,ERPSTOCK);
                        startActivity(intent1);
                        return true ;
                    }
                    else if (expandableListDetail.get(click).get(childPosition).toLowerCase().contains("price check")) {
                        mDrawerLayout.closeDrawers();
                        Intent intent1 = new Intent(HomeDrawerActivity.this, Erp_Stock.class);
                        intent1.putExtra(CHECKTYPE,ERPRICE);
                        startActivity(intent1);
                        return true ;
                    }
                    else if (expandableListDetail.get(click).get(childPosition).toLowerCase().contains("posx")) {
                        mDrawerLayout.closeDrawers();
                        Intent intent1 = new Intent(HomeDrawerActivity.this, PosxOrderList.class);
                        intent1.putExtra(CHECKTYPE,ERPRICE);
                        startActivity(intent1);
                        return true ;
                    }
                    else if (expandableListDetail.get(click).get(childPosition).toLowerCase().contains("pre-orders")) {
                        Key = constants.PREORDER;
                    }

                    else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Ready to Dispatch")) {
                        Key = constants.order_rtd;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("AWB Created")) {
                        Key = constants.AWBCREATED;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Shipped Orders")) {
                        Key = constants.SHIPPED;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Cancelled Orders")) {
                        Key = constants.CANCELLED;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Delivered Orders")) {
                        Key = constants.COMPLETE;
                    }else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("In Progress")) {
                        Key = constants.INPROGRESS;
                    }
                    if (!Key.isEmpty()) {
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(HomeDrawerActivity.this, DispatchOrderList.class);
                        intent.putExtra(constants.order_type, Key);
                        startActivity(intent);
                    }
                }
                else if (click.equalsIgnoreCase("Returns")) {
                  if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Pending RMO")) {
                        Key = constants.PENDINGRMO;
                    } else if (expandableListDetail.get(click).get(childPosition).equalsIgnoreCase("Pending Receiving")) {
                        Key = constants.PENDINGRECIEVING;
                    }
                    if (!Key.isEmpty()) {
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(HomeDrawerActivity.this, DispatchOrderList.class);
                        intent.putExtra(constants.order_type, Key);
                        startActivity(intent);
                    }
                }

                return true;
            }
        });

        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            callPermission();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem useritem = menu.findItem(R.id.user_profile);
        LinearLayout rootView = (LinearLayout) useritem.getActionView();
        Username = rootView.findViewById(R.id.username);
        Username.setText("Welcome  " + upref.getFirstName());
        return super.onPrepareOptionsMenu(menu);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void callPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION}, 3);

    }

    private void getPendingorderdata(String key) {

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        orderApi ord = new orderApi();
        ord.setstaus(key);
        ord.setOadbEmployeeId(upref.getKeyEmpCode());
        ord.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));

        Call<bikerorder> stringCall = apiService.getorderlist(ord);
        stringCall.enqueue(new Callback<bikerorder>() {
            @Override
            public void onResponse(Call<bikerorder> call, Response<bikerorder> response) {
                if (response.isSuccessful()) {
                    PendingOrderList = response.body().getBikerOrders();

                    if (pendingorder_layout.getChildCount() > 1) ;
                        pendingorder_layout.removeAllViews();
                    addpendingheader();
//                    if (PendingOrderList.size() == 0) {
//                        nopendingheader();
//                        return;
//                    }
                    int temp_size = 5;
                    if (PendingOrderList.size() < temp_size)
                        temp_size = PendingOrderList.size();
                    for (int i = 0; i < temp_size; i++) {
                        addPendingOrders(i);
                    }
                    if (temp_size == 5)
                        addshowallpending();
                }
            }

            @Override
            public void onFailure(Call<bikerorder> call, Throwable t) {
                stopLoader();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2 && data!=null)
        {
            if(data.getStringExtra("MESSAGE")!=null) {
                String barcode = data.getStringExtra("MESSAGE");
                Intent intent = new Intent(HomeDrawerActivity.this, OrderDetailNew.class);
                intent.putExtra(constants.MOVEORDERNO, barcode);
                intent.putExtra(constants.User_name, upref.getFirstName());
                intent.putExtra(constants.userid, upref.getUserId());
                intent.putExtra(constants.usercode, upref.getKeyUserCode());
                startActivity(intent);
            }
        }
    }


    private void getPickedOrderData(String key) {
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        orderApi ord = new orderApi();
        ord.setstaus(key);
        ord.setOadbEmployeeId(upref.getKeyEmpCode());
        ord.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        Call<bikerorder> stringCall = apiService.getorderlist(ord);
        stringCall.enqueue(new Callback<bikerorder>() {
            @Override
            public void onResponse(Call<bikerorder> call, Response<bikerorder> response) {

                if (response.isSuccessful()) {
                    PickedOrderList = response.body().getBikerOrders();

                    if (pickedorders_layout.getChildCount() > 0) ;
                    pickedorders_layout.removeAllViews();
                    addpickedheader();
//                    if (PickedOrderList.size() == 0) {
//                        nopickedheader();
//                        return;
//                    }
                    int temp_size = 5;
                    if (PickedOrderList.size() < temp_size)
                        temp_size = PickedOrderList.size();

                    for (int i = 0; i < temp_size; i++) {
                        addPickedOrders(i);
                    }
                    if (temp_size == 5)
                        addshowallpicked();
                }
            }

            @Override
            public void onFailure(Call<bikerorder> call, Throwable t) {
                stopLoader();
            }
        });

    }

    private void getDashBoarddata() {

        if(!internetavailable(HomeDrawerActivity.this)) {
            ShowToast(getString(R.string.nointernet), HomeDrawerActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        UserDetails user = new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode());
        Call<DashboardResponse> stringCall = apiService.getDashboardData(user);

        stringCall.enqueue(new Callback<DashboardResponse>() {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {

                stopLoader();
                if (response.body() != null && response.body().getDashboard() != null && response.body().getDashboard().size() >= 0) {
                    DashboardResponse responseString = response.body();
                    dashboarddata = responseString.getDashboard();
                    generateData();

                } else if (response.message().equalsIgnoreCase("unauthorized")) {
                    Toast.makeText(HomeDrawerActivity.this, "Unauthorized, Please logout and try again.", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(HomeDrawerActivity.this, response.message(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                stopLoader();
                mDrawerLayout.setVisibility(View.VISIBLE);
                Toast.makeText(HomeDrawerActivity.this, getString(R.string.api_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getmenuList() {
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        menurole mr = new menurole();
        mr.setAppActive("Y");
        mr.setRoleId(upref.getRoleId());

        Call<menuitems> stringCall = apiService.getmenulist(mr);
        stringCall.enqueue(new Callback<menuitems>() {
            @Override
            public void onResponse(Call<menuitems> call, Response<menuitems> response) {
                if (response.isSuccessful()) {
                    List<menu> menu = response.body().getMenu();
                    ExpandableListDataPump(menu);
                }

            }

            @Override
            public void onFailure(Call<menuitems> call, Throwable t) {
                stopLoader();
            }
        });

    }

    private void generateData() {
        List<SliceValue> values = new ArrayList<SliceValue>();
        if (mainlayout.getChildCount() > 1) ;
        mainlayout.removeViewsInLayout(1, mainlayout.getChildCount() - 1);
        int sum = 0;
        for (int j = 0; j < dashboarddata.size(); j++) {
            sum = sum + dashboarddata.get(j).getStatusCount();
        }

        for (int i = 0; i < dashboarddata.size(); ++i) {
            int color = ChartUtils.pickColor();
            if (dashboarddata.get(i).getOrderStatus().contains("DELIVER")) {
                color = Color.GREEN;
            } else if (dashboarddata.get(i).getOrderStatus().contains("CREATED")) {
                color = Color.BLUE;
            } else if (dashboarddata.get(i).getOrderStatus().contains("CANCEL")) {
                color = Color.RED;
            } else if (dashboarddata.get(i).getOrderStatus().contains("PICK")) {
                color = Color.MAGENTA;
            } else if (dashboarddata.get(i).getOrderStatus().contains("POST")) {
                color = Color.YELLOW;
            }

            View to_add = getLayoutInflater().inflate(R.layout.graphitem,
                    mainlayout, false);

            TextView type = to_add.findViewById(R.id.orderitem_type);
            TextView per = to_add.findViewById(R.id.orderitem_per);
            ImageView col = to_add.findViewById(R.id.itemcolor);
            type.setText(dashboarddata.get(i).getOrderStatus().replace("ORDER_", ""));
            per.setText(String.valueOf((dashboarddata.get(i).getStatusCount() * 100 / sum)));
            col.setBackgroundColor(color);

            mainlayout.addView(to_add);


            String lable = dashboarddata.get(i).getOrderStatus().replace("ORDER_", "") + ":" + dashboarddata.get(i).getStatusCount();
            SliceValue sliceValue = new SliceValue(dashboarddata.get(i).getStatusCount(), color).setLabel(lable);
            values.add(sliceValue);
        }

        piedata = new PieChartData(values);
        piedata.setHasLabels(true);
        piedata.setHasCenterCircle(true);
        piedata.setCenterText1("Orders Status");
        piedata.setHasLabelsOutside(false);
        piedata.setCenterCircleScale(0.5f);

        piedata.setCenterText1FontSize(18);
        piedata.setSlicesSpacing(4);
        pie.setPieChartData(piedata);
        mDrawerLayout.setVisibility(View.VISIBLE);

        if(!istablet())
            mainlayout.setVisibility(View.GONE);

    }

    private void ExpandableListDataPump(List<menu> menu) {
        expandableListDetail = new LinkedHashMap<String, List<String>>();
        for (int i = 0; i < menu.size(); i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < menu.get(i).getDetailMenu().size(); j++) {
                temp.add(menu.get(i).getDetailMenu().get(j).getDtlMenuFunction());
            }
            expandableListDetail.put(menu.get(i).getHeadMenuDesc(), temp);
        }
        List<String> temp1 = new ArrayList<>();
        expandableListDetail.put(getString(R.string.logout), temp1);
        expandableListDetail.put("Version "+BuildConfig.VERSION_NAME,temp1 );
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        mDrawerList.setAdapter(expandableListAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        final Dialog dialogView = new Dialog(HomeDrawerActivity.this);
        dialogView.setCancelable(true);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.exit_dialog);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        HomeDrawerActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthLcl = (int) (displayMetrics.widthPixels * 0.8f);
        int heightLcl = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogView.getWindow().setLayout(widthLcl, heightLcl);
        Button yesBtn = dialogView.findViewById(R.id.btn_yes);
        Button noBtn = dialogView.findViewById(R.id.btn_no);
        TextView msg = dialogView.findViewById(R.id.message);
        TextView header = dialogView.findViewById(R.id.txt_header);

        msg.setText(getString(R.string.exit));

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeDrawerActivity.this.finishAffinity();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.cancel();
            }
        });
        dialogView.show();
    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {
        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            pie.setVisibility(View.GONE);
            colchart.setVisibility(View.VISIBLE);
            backgraph.setVisibility(View.VISIBLE);
            if(dashboarddata.size()>=arcIndex)
                ShowLinechartdata(dashboarddata.get(arcIndex).getLevel1());
        }

        @Override
        public void onValueDeselected() {

        }
    }

    private void ShowLinechartdata(List<Level1> sourcedata) {

        if (mainlayout.getChildCount() > 1) ;
        mainlayout.removeViewsInLayout(1, mainlayout.getChildCount() - 1);
        int sum = 0;
        for (int j = 0; j < sourcedata.size(); j++) {
            sum = sum + sourcedata.get(j).getSourceCount();
        }
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < sourcedata.size(); ++i) {
            values = new ArrayList<>();
            int colorcode = ChartUtils.pickColor();
            values.add(new SubcolumnValue(sourcedata.get(i).getSourceCount(), colorcode).setLabel(sourcedata.get(i).getObohOrderSource() + "-" + sourcedata.get(i).getSourceCount()));
            Column column = new Column(values);
            column.setHasLabels(true);
            columns.add(column);
            View to_add = getLayoutInflater().inflate(R.layout.graphitem,
                    mainlayout, false);
            TextView type = to_add.findViewById(R.id.orderitem_type);
            TextView per = to_add.findViewById(R.id.orderitem_per);
            ImageView col = to_add.findViewById(R.id.itemcolor);
            type.setText(sourcedata.get(i).getObohOrderSource());
            per.setText(String.valueOf((sourcedata.get(i).getSourceCount() * 100 / sum)));
            col.setBackgroundColor(colorcode);
            mainlayout.addView(to_add);
        }

        columnChartData = new ColumnChartData(columns);
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Order Source");
        axisY.setName("Count");
        columnChartData.setAxisXBottom(axisX);
        columnChartData.setAxisYLeft(axisY);
        columnChartData.setFillRatio(0.5f);
        colchart.setColumnChartData(columnChartData);
    }


    void addPendingOrders(final int i) {

        View to_add = getLayoutInflater().inflate(R.layout.dashboard_orderitem,
                pendingorder_layout, false);

        TextView cust_name = to_add.findViewById(R.id.cust_name);
        TextView source = to_add.findViewById(R.id.source);
        TextView modified = to_add.findViewById(R.id.modified);
        cust_name.setText(PendingOrderList.get(i).getObohCustFullName());
        if(!istablet())
            source.setVisibility(View.INVISIBLE);
        else
            source.setVisibility(View.VISIBLE);

        source.setText(PendingOrderList.get(i).getObohOrderSource());
        modified.setText(chnagedateformat(PendingOrderList.get(i).getObohOrderDate().substring(0, 10)));
        to_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeDrawerActivity.this, OrderDetailActivity.class);
                intent.putExtra(constants.OABOID, PendingOrderList.get(i).getOaboId());
                intent.putExtra(constants.OADBID, PendingOrderList.get(i).getOadbId());
                intent.putExtra(constants.ORDERNO, PendingOrderList.get(i).getObohOrderNo());
                intent.putExtra(constants.User_name, upref.getFirstName());
                intent.putExtra(constants.userid, upref.getUserId());
                intent.putExtra(constants.usercode, upref.getKeyUserCode());
                intent.putExtra(constants.order_type, constants.ORDER_CREATED);
                startActivity(intent);
            }
        });
        pendingorder_layout.addView(to_add);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (pendingorder_layout.getChildCount() > 0) ;
            pendingorder_layout.removeAllViews();
        if (pickedorders_layout.getChildCount() > 0) ;
            pickedorders_layout.removeAllViews();
        getPendingorderdata(constants.ORDER_CREATED);
        getPickedOrderData(constants.ORDER_PICKED);
    }

    void addPickedOrders(final int i) {

        View to_add = getLayoutInflater().inflate(R.layout.dashboard_orderitem,
                pickedorders_layout, false);
        TextView cust_name = to_add.findViewById(R.id.cust_name);
        TextView source = to_add.findViewById(R.id.source);
        TextView modified = to_add.findViewById(R.id.modified);
        cust_name.setText(PickedOrderList.get(i).getObohCustFullName());

        if(!istablet())
            source.setVisibility(View.INVISIBLE);
        else
            source.setVisibility(View.VISIBLE);

        source.setText(PickedOrderList.get(i).getObohOrderSource());
        modified.setText(chnagedateformat(PickedOrderList.get(i).getObohOrderDate().substring(0, 10)));
        to_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeDrawerActivity.this, OrderDetailActivity.class);
                intent.putExtra(constants.OABOID, PickedOrderList.get(i).getOaboId());
                intent.putExtra(constants.OADBID, PickedOrderList.get(i).getOadbId());
                intent.putExtra(constants.ORDERNO, PickedOrderList.get(i).getObohOrderNo());
                intent.putExtra(constants.User_name, upref.getFirstName());
                intent.putExtra(constants.userid, upref.getUserId());
                intent.putExtra(constants.usercode, upref.getKeyUserCode());
                intent.putExtra(constants.order_type, constants.ORDER_PICKED);

                startActivity(intent);
            }
        });
        pickedorders_layout.addView(to_add);

    }

    private void addshowallpicked() {
        View showallpicked = getLayoutInflater().inflate(R.layout.showall,
                pickedorders_layout, false);
        showallpicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeDrawerActivity.this, OrderListActivity.class);
                intent.putExtra(constants.order_type, constants.ORDER_PICKED);
                startActivity(intent);
            }
        });
        pickedorders_layout.addView(showallpicked);
    }

    private void addshowallpending() {
        View showallpicked = getLayoutInflater().inflate(R.layout.showall,
                pendingorder_layout, false);
        showallpicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeDrawerActivity.this, OrderListActivity.class);
                intent.putExtra(constants.order_type, constants.ORDER_CREATED);
                startActivity(intent);
            }
        });
        pendingorder_layout.addView(showallpicked);
    }

    private void addpendingheader() {
        View pendingheader = getLayoutInflater().inflate(R.layout.pending_header,
                pendingorder_layout, false);

        pendingorder_layout.addView(pendingheader);
    }

    private void addpickedheader() {
        View pickedheader = getLayoutInflater().inflate(R.layout.picked_header,
                pickedorders_layout, false);

        pickedorders_layout.addView(pickedheader);
    }

    private void nopickedheader() {
        View noorders = getLayoutInflater().inflate(R.layout.no_orders,
                pickedorders_layout, false);

        pickedorders_layout.addView(noorders);
    }

    private void nopendingheader() {
        View nopendingorder = getLayoutInflater().inflate(R.layout.no_orders, pendingorder_layout, false);
        pendingorder_layout.addView(nopendingorder);
    }
}