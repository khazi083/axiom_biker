package com.axiom.fulfillment.view;
import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.adaptor.CourierTRackerAdaptor;
import com.axiom.fulfillment.adaptor.OrderDetailsAdaptor;
import com.axiom.fulfillment.adaptor.PaymentDetailsAdaptor;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;

import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.helper.constants;
import com.axiom.fulfillment.model.Biker;
import com.axiom.fulfillment.model.BikerList;
import com.axiom.fulfillment.model.BikerRequest;
import com.axiom.fulfillment.model.ButtonStatus;
import com.axiom.fulfillment.model.ButtonStatusResult;
import com.axiom.fulfillment.model.CancelOrder;
import com.axiom.fulfillment.model.CancelPosxOrder;
import com.axiom.fulfillment.model.Courier;
import com.axiom.fulfillment.model.CourierDetails;
import com.axiom.fulfillment.model.CourierList;
import com.axiom.fulfillment.model.CourierListInput;
import com.axiom.fulfillment.model.CourierOrder;
import com.axiom.fulfillment.model.CourierTracker;
import com.axiom.fulfillment.model.CourierdispatchResponse;
import com.axiom.fulfillment.model.DeliveryRequest;
import com.axiom.fulfillment.model.Invoice;
import com.axiom.fulfillment.model.InvoiceInput;
import com.axiom.fulfillment.model.OrderDetail;
import com.axiom.fulfillment.model.OrderDetailsInput;

import com.axiom.fulfillment.model.Order_details;
import com.axiom.fulfillment.model.ShipmentReport;
import com.axiom.fulfillment.model.ShipmentReportinput;
import com.axiom.fulfillment.model.SystemCourier;
import com.axiom.fulfillment.model.UserDetails;
import com.axiom.fulfillment.model.CommonApiResponse;
import com.axiom.fulfillment.model.bikerdispatch;
import com.axiom.fulfillment.model.createcourierorder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


import android.os.Environment;
import android.os.StrictMode;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class OrderDetailActivity extends BaseActivity implements  GoogleApiClient.ConnectionCallbacks {

    private Order_details details;
    String order_status, ChannelCode, source,POSSTOCKLOC,POSORDERSTATUS,POSPURCHASENO, PickLocation;
    String DeliverGps,PickGps;
    int userid, OABOID, OADBID;
    String orderno,move_orderno, user_name, user_code, order_seqno;
    UserSharedPreferences upref;
    TextView ordernotxt, orderdate, sorce, purchaseno, remarks, amount, totamount, comments,cust_name, cust_mob;
    TextView ordernotxt_m, orderdate_m, sorce_m, purchaseno_m,  amount_m, totamount_m, cust_name_m, cust_mob_m;
    TextView  shipping_address;
    TextView picklocation;
    RecyclerView orderitems, paymentdetails;
    OrderDetailsAdaptor orderDetailsAdaptor;
    PaymentDetailsAdaptor paymentDetailsAdaptor;
    RelativeLayout main;
    Button deliver, pick, cancel, assign_biker,shipmentbill,maps,shipmenttracker;
    Button assign_courier, reshedule, invoice,order_cancel,deliveryreciept;
    int req_code = 201;
    private List<Biker> bikers;
    private List<SystemCourier> systemCouriers;
    private ArrayList<String> bikernames;
    private ArrayList<String> couriernames;
    private ButtonStatusResult btnresult;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest locationRequest;
    private LinearLayout layout_comments, layout_remarks,layout_pickloc;
    private LinearLayout maintablayout,maintablayout_mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        main = findViewById(R.id.main);
        main.setVisibility(View.INVISIBLE);
        ordernotxt = findViewById(R.id.morderno);
        orderdate = findViewById(R.id.morderdate);
        sorce = findViewById(R.id.msource);
        purchaseno = findViewById(R.id.purchaseno);
        amount = findViewById(R.id.mamount);
        picklocation=findViewById(R.id.picklocation);
        totamount = findViewById(R.id.mamounttotal);
        cust_name = findViewById(R.id.mname);
        cust_mob = findViewById(R.id.mmobile);
        maintablayout=findViewById(R.id.mainlayouttab);

        ordernotxt_m = findViewById(R.id.morderno_mob);
        orderdate_m = findViewById(R.id.morderdate_mob);
        sorce_m = findViewById(R.id.msource_mob);
        purchaseno_m = findViewById(R.id.purchaseno_mob);
        amount_m = findViewById(R.id.mamount_mob);
        totamount_m = findViewById(R.id.mamounttotal_mob);
        cust_name_m = findViewById(R.id.mname_mob);
        cust_mob_m = findViewById(R.id.mmobile_mob);
        maintablayout_mob=findViewById(R.id.mainlayoutmob);


        comments = findViewById(R.id.mcomments);
        remarks = findViewById(R.id.mremarks);
        maps=findViewById(R.id.maps);

        shipping_address = findViewById(R.id.mshipadress);
        orderitems = findViewById(R.id.order_itemList);
        paymentdetails = findViewById(R.id.payment_itemList);
        deliver = findViewById(R.id.deliver);
        pick = findViewById(R.id.pick);
        cancel = findViewById(R.id.cancel);
        assign_biker = findViewById(R.id.assign_biker);
        assign_courier = findViewById(R.id.assign_courier);
        reshedule = findViewById(R.id.reshedule);
        invoice = findViewById(R.id.invoice);
        order_cancel=findViewById(R.id.order_cancel);
        layout_comments=findViewById(R.id.layout_comments);
        layout_remarks=findViewById(R.id.layout_remarks);
        layout_pickloc=findViewById(R.id.layout_pickloc);
        deliveryreciept=findViewById(R.id.deliveryreciept);
        couriernames = new ArrayList<>();
        bikernames = new ArrayList<>();
        shipmentbill=findViewById(R.id.shipmentbill);
        shipmenttracker=findViewById(R.id.shipmenttracker);
        shipmenttracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipmentTracking();
            }
        });
        shipmentbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShipmentReport();
            }
        });

        deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ondeliver();
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog("pick");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog("cancel");
            }
        });
        reshedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog("reshedule");
            }
        });
        assign_biker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getbikerlist();
            }
        });
        assign_courier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCourierList();
            }
        });

        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInvoice();
            }
        });

        deliveryreciept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdeliveryReport();
            }
        });
        order_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog("cancel order");
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!internetavailable(OrderDetailActivity.this)) {
                    ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
                    return;
                }


                if(DeliverGps!=null &&!DeliverGps.isEmpty() && PickGps!=null && !PickGps.isEmpty() && !DeliverGps.equalsIgnoreCase("No GPS Coordinates") &&!PickGps.equalsIgnoreCase("No GPS Coordinates"))
                    showmaps();
                else
                    ShowToast("Delivery Location not available.", OrderDetailActivity.this);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.Order_Details));
        upref = new UserSharedPreferences(this);
        getToken(this);
        orderno = getIntent().getStringExtra(constants.ORDERNO);
        user_name = getIntent().getStringExtra(constants.User_name);
        move_orderno=getIntent().getStringExtra(constants.MOVEORDERNO);
        userid = getIntent().getIntExtra(constants.userid, 0);
        user_code = getIntent().getStringExtra(constants.usercode);
        order_status = getIntent().getStringExtra(constants.order_type);
        OABOID = getIntent().getIntExtra(constants.OABOID, 0);
        OADBID = getIntent().getIntExtra(constants.OADBID, 0);
        order_seqno = getIntent().getStringExtra(constants.ObohSeq);
        ChannelCode = getIntent().getStringExtra(constants.CHANNELCODE);
        source = getIntent().getStringExtra(constants.SOURCE);
        PickLocation=getIntent().getStringExtra(constants.PICKLOCATION);

        if(PickLocation!=null && !PickLocation.isEmpty() && order_status.equalsIgnoreCase(constants.ORDER_CREATED) )
        {
            layout_pickloc.setVisibility(View.VISIBLE);
            picklocation.setText(  PickLocation );
        }

        POSSTOCKLOC = getIntent().getStringExtra(constants.POSSTOCKLOC);
        POSORDERSTATUS = getIntent().getStringExtra(constants.POSORDERSTATUS);
        POSPURCHASENO = getIntent().getStringExtra(constants.POSPURCHASENO);
        DeliverGps=getIntent().getStringExtra(constants.DELIVERGPS);
        PickGps=getIntent().getStringExtra(constants.PICKGPS);

        if( order_status.equalsIgnoreCase(constants.ORDER_DELIVERED)){
            maps.setVisibility(View.VISIBLE);
        }
        if (!order_status.equalsIgnoreCase(constants.ORDER_CREATED)&& !order_status.equalsIgnoreCase(constants.ORDER_PICKED)&& !order_status.equalsIgnoreCase(constants.POSX)&&
                !order_status.equalsIgnoreCase(constants.ORDER_POSTPONE)&&!order_status.equalsIgnoreCase(constants.ORDER_DELIVERED)&& !order_status.equalsIgnoreCase(constants.ORDER_CANCELLED))
            getButtonStatus();
        getorderdata();
        statusCheck();
        buildGoogleApiClient();
        createLocationRequest();

    }

    private void showmaps() {
        if(PickGps.equalsIgnoreCase(DeliverGps))
            PickGps="25.1208242,55.3782563"; // axiom location
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin="+PickGps+"&destination="+DeliverGps);
        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        intent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install maps application", Toast.LENGTH_LONG).show();
            }
        }
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
}

    @Override
    public void onConnected(Bundle bundle) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
//            mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                callPermission();
                return;
            }
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /* Second part*/

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void callPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }


        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }


        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 3) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                   /* PackageManager packageManager = getApplicationContext().getPackageManager();
                    if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                    {*/
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }
                    // }

                    //Displaying a toast
                    //
                }  //Displaying another toast if permission is not granted //Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
                /*else {
                  //  Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
                }*/
            }

        }
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void createLocationRequest() {
        int LOCATION_DISTANCE = 10;
        int LOCATION_UPDATE_INTERVAL = 8000;
        locationRequest = new LocationRequest();
        locationRequest.setInterval(LOCATION_UPDATE_INTERVAL); //in milliseconds
        locationRequest.setFastestInterval(5000); //in milliseconds
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setSmallestDisplacement(LOCATION_DISTANCE);
    }

    private void getButtonStatus() {

        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        ButtonStatus input = new ButtonStatus();
        if(order_seqno!=null && !order_seqno.isEmpty())
            input.setObohSeq(Double.valueOf(order_seqno));
        input.setUserRole(upref.getKeyUserRole());
        input.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<ButtonStatusResult> stringCall = apiService.getButtonStatus(input);
        stringCall.enqueue(new Callback<ButtonStatusResult>() {
            @Override
            public void onResponse(Call<ButtonStatusResult> call, Response<ButtonStatusResult> response) {
                stopLoader();
                if (response.body()!=null && response.body().getStatus().getOutResult()) {
                    btnresult = response.body();
                    showbtn();
                } else if(response.body()!=null&& response.body().getStatus()!=null) {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ButtonStatusResult> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }

    private void showbtn() {
        invoice.setVisibility(View.GONE);
        order_cancel.setVisibility(View.GONE);
        shipmentbill.setVisibility(View.GONE);
        assign_biker.setVisibility(View.GONE);

        if (btnresult.getCanCancel()){
            order_cancel.setVisibility(View.VISIBLE);
        }
        if(btnresult.getShowInvoice()){
            invoice.setVisibility(View.VISIBLE);
            deliveryreciept.setVisibility(View.VISIBLE);
        }
        if(btnresult.getShowAirwayBill()){
            shipmentbill.setVisibility(View.VISIBLE);
        }
        if(btnresult.getShowAxiomBikerDelivery()){
            assign_biker.setVisibility(View.VISIBLE);
        }

        if(btnresult.getShowCourierTracker()){
            shipmenttracker.setVisibility(View.VISIBLE);
        }
     main.setVisibility(View.VISIBLE);
    }


    public static String encrypt(String key, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
            System.out.println("encrypted string: " + Base64.encodeToString(encrypted, Base64.DEFAULT));
            return Base64.encodeToString(encrypted, Base64.DEFAULT).replace("\n","");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private void getbikerlist() {
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        CourierListInput ci = new CourierListInput();
        UserDetails usr = new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode());
        ci.setUserDetails(usr);
        ci.setAclmChannelCode(ChannelCode);
        Call<BikerList> stringCall = apiService.getBikerlist(ci);
        startLoader(getString(R.string.loading_bikers), this);
        stringCall.enqueue(new Callback<BikerList>() {
            @Override
            public void onResponse(Call<BikerList> call, Response<BikerList> response) {
                stopLoader();
                bikernames.clear();
                if (response.body().getStatus().getOutResult()) {
                    bikers = response.body().getBikers();
                    for (int i = 0; i < bikers.size(); i++)
                        bikernames.add(bikers.get(i).getEmployeeName());
                    dispatchdailog("biker");
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<BikerList> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });

    }

    private void getCourierList() {
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        CourierListInput ci = new CourierListInput();
        UserDetails usr = new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode());
        ci.setUserDetails(usr);
        ci.setAclmChannelCode(ChannelCode);
        ci.setFulFilSystem(source);
        Call<CourierList> stringCall = apiService.getCourierlist(ci);
        startLoader(getString(R.string.loading_courier), this);
        stringCall.enqueue(new Callback<CourierList>() {
            @Override
            public void onResponse(Call<CourierList> call, Response<CourierList> response) {
                stopLoader();
                couriernames.clear();
                if (response.body().getStatus().getOutResult()) {
                    systemCouriers = response.body().getSystemCouriers();
                    for (int i = 0; i < systemCouriers.size(); i++)
                        couriernames.add(systemCouriers.get(i).getOcmName());
                    dispatchdailog("courier");
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }
            }

            @Override
            public void onFailure(Call<CourierList> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });

    }

    private void dispatchdailog(final String dispatchtype) {

        final Dialog dialogView = new Dialog(OrderDetailActivity.this);
        dialogView.setCancelable(true);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.dispatch_dialog);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        OrderDetailActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthLcl = (int) (displayMetrics.widthPixels * 0.8f);
        int heightLcl = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogView.getWindow().setLayout(widthLcl, heightLcl);
        Button dispatch = dialogView.findViewById(R.id.dispatch);
        final Spinner dispatchlist = dialogView.findViewById(R.id.dispatchlist);
        TextView dispathtype = dialogView.findViewById(R.id.dispatchtype);
        if (dispatchtype.equals("biker")) {
            dispathtype.setText("Select Biker");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(OrderDetailActivity.this, android.R.layout.simple_spinner_item, bikernames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dispatchlist.setAdapter(dataAdapter);
        } else if (dispatchtype.equals("courier")) {
            dispathtype.setText("Select Courier");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(OrderDetailActivity.this, android.R.layout.simple_spinner_item, couriernames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dispatchlist.setAdapter(dataAdapter);
        }
        dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dispatchtype.equals("biker")) {
                    for (int i = 0; i < bikers.size(); i++) {
                        if (dispatchlist.getSelectedItem().toString().equalsIgnoreCase(bikers.get(i).getEmployeeName())) {
                            dispatchbiker(i);
                            break;
                        }
                    }
                } else if (dispatchtype.equals("courier")) {
                    for (int i = 0; i < systemCouriers.size(); i++) {
                        if (dispatchlist.getSelectedItem().toString().equalsIgnoreCase(systemCouriers.get(i).getOcmName())) {
                            dispatchcourier(i);
                            break;
                        }
                    }
                }
                dialogView.dismiss();
            }
        });


        dialogView.show();
    }

    private void dispatchcourier(int pos) {
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        createcourierorder courier = new createcourierorder();
        UserDetails usr = new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode());
        courier.setUserDetails(usr);
        Courier c = new Courier();
        c.setCourierID(systemCouriers.get(pos).getOcmId());
        c.setCourierName(systemCouriers.get(pos).getOcmName());
        courier.setCourier(c);
        List<CourierOrder> orders = new ArrayList<>();
        CourierOrder co = new CourierOrder();
        co.setAxiomOrderNo(orderno);
        orders.add(co);
        courier.setOrders(orders);

        Call<CourierdispatchResponse> stringCall = apiService.createCourierrorder(courier);
        startLoader("Assigning order to " + systemCouriers.get(pos).getOcmName(), this);
        stringCall.enqueue(new Callback<CourierdispatchResponse>() {
            @Override
            public void onResponse(Call<CourierdispatchResponse> call, Response<CourierdispatchResponse> response) {
                stopLoader();

                if (response.body() != null && response.body().getStatus().getOutResult()) {
                    ShowToast("Order assigned Successfully.", OrderDetailActivity.this);
                    finish();
                } else {
                    ShowToast(response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }
            }

            @Override
            public void onFailure(Call<CourierdispatchResponse> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });

    }

    private void dispatchbiker(Integer pos) {
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }

        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        bikerdispatch bk = new bikerdispatch();
        UserDetails usr = new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode());
        bk.setUserDetails(usr);
        bk.setOaboStatus("ORDER_CREATE");
        bk.setObohSeq(Double.valueOf(order_seqno));
        bk.setOadbId(bikers.get(pos).getOadbId());
        Call<CommonApiResponse> stringCall = apiService.createbikerorder(bk);
        startLoader("Assigning order to " + bikers.get(pos).getEmployeeName(), this);
        stringCall.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                stopLoader();

                if (response.body() != null && response.body().getStatus().getOutResult()) {
                    ShowToast("Order assigned Successfully.", OrderDetailActivity.this);
                    finish();
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }

    private void alertDialog(final String msgtxt) {
        final Dialog dialogView = new Dialog(OrderDetailActivity.this);
        dialogView.setCancelable(true);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.exit_dialog);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        OrderDetailActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthLcl = (int) (displayMetrics.widthPixels * 0.8f);
        int heightLcl = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogView.getWindow().setLayout(widthLcl, heightLcl);
        Button yesBtn = dialogView.findViewById(R.id.btn_yes);
        Button noBtn = dialogView.findViewById(R.id.btn_no);
        TextView msg = dialogView.findViewById(R.id.message);
        TextView header = dialogView.findViewById(R.id.txt_header);

        if (msgtxt.equalsIgnoreCase("pick")) {
            msg.setText("You want to pick this order for delivery ?");
            header.setText("Order Pick");
        } else if (msgtxt.equalsIgnoreCase("cancel")) {
            msg.setText("You want to Cancel this order?");
            header.setText("Order Cancel");
        } else if (msgtxt.equalsIgnoreCase("reshedule")) {
            msg.setText("You want to Postpone this order?");
            header.setText("Order Postoned");
        } else if (msgtxt.equalsIgnoreCase("cancel order")) {
            msg.setText("You want to Cancel this order?");
            header.setText("Order Cancel");
        }

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msgtxt.equalsIgnoreCase("pick")) {
                    onpick();
                } else if (msgtxt.equalsIgnoreCase("cancel")) {
                    if  (order_status.equalsIgnoreCase(constants.POSX))
                        cancel_posxorder();
                    else
                        oncancel();
                } else if (msgtxt.equalsIgnoreCase("reshedule")) {
                    onreshedule();
                }else if (msgtxt.equalsIgnoreCase("cancel order")) {
                    cancel_order();
                }
                dialogView.cancel();
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

    private void cancel_posxorder() {
        if(!internetavailable(this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        CancelPosxOrder co= new CancelPosxOrder();
        co.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        co.setAxiomOrderNo(orderno);
        if(order_seqno!=null && !order_seqno.isEmpty())
            co.setOrderSequence(Double.valueOf(order_seqno));
        co.setCancelReason("order cancelled from mobile");
        co.setStockChannel(ChannelCode);
        co.setOrderSource(source);
        co.setEPurchaseNo(Double.valueOf(POSPURCHASENO));
        co.setStockLocation(Double.valueOf(POSSTOCKLOC));
        co.setOrderStatus(POSORDERSTATUS);
        Call<CommonApiResponse> stringCall = apiService.cancelPosxOrder(co);
        startLoader(getString(R.string.loading), this);
        stringCall.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    ShowToast("Order Cancelled.", OrderDetailActivity.this);
                    finish();
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }

    private void cancel_order() {
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        CancelOrder co= new CancelOrder();
        co.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        co.setObohOrderNo(orderno);
        if(order_seqno!=null && !order_seqno.isEmpty())
            co.setObohSeq(Double.valueOf(order_seqno));
        co.setReason("order cancelled from mobile");
        Call<CommonApiResponse> stringCall = apiService.cancelOrder(co);
        startLoader(getString(R.string.loading), this);
        stringCall.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    ShowToast("Order Cancelled.", OrderDetailActivity.this);
                    finish();
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }

    private void onreshedule() {
        DeliveryRequest req = new DeliveryRequest();
        BikerRequest bike = new BikerRequest();
        req.setAction("ORDER_POSTPONE");
        bike.setOaboId(String.valueOf(OABOID));
        bike.setOadbId(String.valueOf(OADBID));
        bike.setOaboStatus("ORDER_POSTPONE");
        bike.setOaboComments("ORDER_POSTPONE");
        bike.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        req.setBikerRequest(bike);
        apicall(req, "Order Postponed.");
    }

    private void getorderdata() {
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        OrderDetailsInput input = new OrderDetailsInput();
        if(orderno!=null && !orderno.isEmpty())
            input.setOrderNo(orderno);
        else
            input.setMove_orderno(move_orderno);
        input.setUser(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<Order_details> stringCall = apiService.orderdetails(input);
        startLoader(getString(R.string.loading), this);
        stringCall.enqueue(new Callback<Order_details>() {
            @Override
            public void onResponse(Call<Order_details> call, Response<Order_details> response) {

                if (response.body()!=null && response.body().getStatus().getOutResult()) {
                    details = response.body();
                    initview();
                } else if (response.body()!=null){
                    stopLoader();
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<Order_details> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });

    }

    private void initview() {
        OrderDetail mOrderDetails = details.getOrderDetail().get(0);

        if(istablet()) {
            maintablayout.setVisibility(View.VISIBLE);
            maintablayout_mob.setVisibility(View.GONE);
            ordernotxt.setText(mOrderDetails.getOrderNumber());
            orderdate.setText(chnagedateformat(mOrderDetails.getOrderDate().replace("T", " ").substring(0,10)));
            sorce.setText(mOrderDetails.getOrderSource());
            purchaseno.setText(mOrderDetails.getObohPartnerOrderNo());
            cust_name.setText(mOrderDetails.getCustomerFullName());
            cust_mob.setText(mOrderDetails.getCustomerMobile());
            amount.setText(String.valueOf(mOrderDetails.getOrderAmount()) + " " + mOrderDetails.getCurrencyCode());
            totamount.setText(String.valueOf(mOrderDetails.getTotalAmount()) + " " + mOrderDetails.getCurrencyCode());
        }
        else{
            maintablayout.setVisibility(View.GONE);
            maintablayout_mob.setVisibility(View.VISIBLE);
            ordernotxt_m.setText(mOrderDetails.getOrderNumber());
            orderdate_m.setText(chnagedateformat(mOrderDetails.getOrderDate().replace("T", " ").substring(0,10)));
            sorce_m.setText(mOrderDetails.getOrderSource());
            purchaseno_m.setText(mOrderDetails.getObohPartnerOrderNo());
            cust_name_m.setText(mOrderDetails.getCustomerFullName());
            cust_mob_m.setText(mOrderDetails.getCustomerMobile());
            amount_m.setText(String.valueOf(mOrderDetails.getOrderAmount()) + " " + mOrderDetails.getCurrencyCode());
            totamount_m.setText(String.valueOf(mOrderDetails.getTotalAmount()) + " " + mOrderDetails.getCurrencyCode());
        }


        if(mOrderDetails.getRemarks()!=null && !mOrderDetails.getRemarks().isEmpty()) {
            remarks.setText(mOrderDetails.getRemarks());
            layout_remarks.setVisibility(View.VISIBLE);
        }
        if(mOrderDetails.getComments()!=null && !mOrderDetails.getComments().isEmpty()) {
            comments.setText(mOrderDetails.getComments());
            layout_comments.setVisibility(View.VISIBLE);
        }


        shipping_address.setText(mOrderDetails.getShippingAddress1() + " " + mOrderDetails.getShippingRegion()
                + " " + mOrderDetails.getShippingCity() + " " + mOrderDetails.getShippingCountry());
        orderDetailsAdaptor = new OrderDetailsAdaptor(OrderDetailActivity.this, mOrderDetails.getOrderDetails());
        paymentDetailsAdaptor = new PaymentDetailsAdaptor(OrderDetailActivity.this, mOrderDetails.getPaymentDetails());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        orderitems.setLayoutManager(mLayoutManager);
        orderitems.setItemAnimator(new DefaultItemAnimator());
        orderitems.setAdapter(orderDetailsAdaptor);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        paymentdetails.setLayoutManager(mLayoutManager1);
        paymentdetails.setItemAnimator(new DefaultItemAnimator());
        paymentdetails.setAdapter(paymentDetailsAdaptor);

        if (order_status.equalsIgnoreCase(constants.ORDER_CREATED)) {
            pick.setVisibility(View.VISIBLE);
            deliver.setVisibility(View.INVISIBLE);
            cancel.setVisibility(View.INVISIBLE);
            assign_biker.setVisibility(View.GONE);
            assign_courier.setVisibility(View.GONE);
            reshedule.setVisibility(View.GONE);
            invoice.setVisibility(View.GONE);

        } else if (order_status.equalsIgnoreCase(constants.ORDER_PICKED)) {
//            pick.setVisibility(View.INVISIBLE);
//            deliver.setVisibility(View.INVISIBLE);
//            cancel.setVisibility(View.INVISIBLE);
//            reshedule.setVisibility(View.INVISIBLE);
            assign_biker.setVisibility(View.GONE);
            assign_courier.setVisibility(View.GONE);

            pick.setVisibility(View.INVISIBLE);
            deliver.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            reshedule.setVisibility(View.VISIBLE);



        } else if (order_status.equalsIgnoreCase(constants.order_rtd)) {
            pick.setVisibility(View.INVISIBLE);
            deliver.setVisibility(View.INVISIBLE);
            cancel.setVisibility(View.INVISIBLE);
            assign_biker.setVisibility(View.VISIBLE);
            assign_courier.setVisibility(View.VISIBLE);
            reshedule.setVisibility(View.GONE);
        } else if  (order_status.equalsIgnoreCase(constants.POSX)) {
            pick.setVisibility(View.GONE);
            deliver.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            order_cancel.setVisibility(View.VISIBLE);
            assign_biker.setVisibility(View.GONE);
            assign_courier.setVisibility(View.GONE);
            reshedule.setVisibility(View.GONE);
            invoice.setVisibility(View.GONE);
        }
        else {
            pick.setVisibility(View.GONE);
            deliver.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            assign_biker.setVisibility(View.GONE);
            assign_courier.setVisibility(View.GONE);
            reshedule.setVisibility(View.GONE);
            invoice.setVisibility(View.GONE);
            reshedule.setVisibility(View.GONE);
        }
        if (order_status.equalsIgnoreCase(constants.ORDER_CREATED)|| order_status.equalsIgnoreCase(constants.ORDER_PICKED)||order_status.equalsIgnoreCase(constants.POSX)||
                order_status.equalsIgnoreCase(constants.ORDER_POSTPONE)|| order_status.equalsIgnoreCase(constants.ORDER_DELIVERED)|| order_status.equalsIgnoreCase(constants.ORDER_CANCELLED)) {
            main.setVisibility(View.VISIBLE);
            stopLoader();
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

    public void ondeliver() {

        Intent intent = new Intent(OrderDetailActivity.this, OrderDeliveryActivity.class);
        intent.putExtra(constants.OABOID, OABOID);
        intent.putExtra(constants.OADBID, OADBID);
        intent.putExtra(constants.ORDERNO, details.getOrderDetail().get(0).getOrderNumber());
        intent.putExtra("order_amount", String.valueOf(details.getOrderDetail().get(0).getTotalAmount()) + " " +
                details.getOrderDetail().get(0).getCurrencyCode());
        intent.putExtra("cust_name", details.getOrderDetail().get(0).getCustomerFullName());
        startActivityForResult(intent, req_code);

//        if(upref.getKeyCountryCode().equalsIgnoreCase("219")){
//            Intent intent = new Intent(OrderDetailActivity.this, OrderDeliverySaudi.class);
//            intent.putExtra(constants.OABOID, OABOID);
//            intent.putExtra(constants.OADBID, OADBID);
//            intent.putExtra(constants.ORDERNO, details.getOrderDetail().get(0).getOrderNumber());
//            intent.putExtra("order_amount", String.valueOf(details.getOrderDetail().get(0).getTotalAmount()) + " " +
//                    details.getOrderDetail().get(0).getCurrencyCode());
//            intent.putExtra("cust_name", details.getOrderDetail().get(0).getCustomerFullName());
//            startActivityForResult(intent, req_code);
//        }
//
//
//       else {
//            Intent intent = new Intent(OrderDetailActivity.this, OrderDeliverySaudi.class);
//            intent.putExtra(constants.OABOID, OABOID);
//            intent.putExtra(constants.OADBID, OADBID);
//            intent.putExtra(constants.ORDERNO, details.getOrderDetail().get(0).getOrderNumber());
//            intent.putExtra("order_amount", String.valueOf(details.getOrderDetail().get(0).getTotalAmount()) + " " +
//                    details.getOrderDetail().get(0).getCurrencyCode());
//            intent.putExtra("cust_name", details.getOrderDetail().get(0).getCustomerFullName());
//            startActivityForResult(intent, req_code);
//        }
    }

    public void oncancel() {
        DeliveryRequest req = new DeliveryRequest();
        BikerRequest bike = new BikerRequest();
        req.setAction("CANCEL");
        bike.setOaboId(String.valueOf(OABOID));
        bike.setOadbId(String.valueOf(OADBID));
        if (mLastLocation != null) {
            bike.setOaboUserGps(mLastLocation.getLatitude() + "," + mLastLocation.getLongitude());
        }
        bike.setOaboStatus(constants.ORDER_CANCELLED);
        bike.setOaboComments("ORDER CANCELLED.");
        bike.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        req.setBikerRequest(bike);
        apicall(req, "Order cancelled.");
    }

    public void onpick() {
        DeliveryRequest req = new DeliveryRequest();
        BikerRequest bike = new BikerRequest();
        req.setAction("PICK");
        bike.setOaboId(String.valueOf(OABOID));
        bike.setOadbId(String.valueOf(OADBID));
        bike.setOaboStatus(constants.ORDER_PICKED);
        if (mLastLocation != null) {
            bike.setOaboUserGps(mLastLocation.getLatitude() + "," + mLastLocation.getLongitude());
        }
        bike.setOaboComments("ORDER PICKED.");
        bike.setOaboSignFileType("");
        bike.setOaboSignFileName("");
        bike.setOaboSignature("");
        bike.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName()));
        req.setBikerRequest(bike);
        apicall(req, "Order Picked.");

    }

    private void apicall(DeliveryRequest req, final String msg) {
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        Call<CommonApiResponse> stringCall = apiService.deliverorder(req);
        startLoader(getString(R.string.loading), this);
        stringCall.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    ShowToast(msg, OrderDetailActivity.this);
                    finish();
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == req_code && resultCode == RESULT_OK) {

                String status = data.getStringExtra("order_status");
                if (status.equalsIgnoreCase("delivered"))
                    finish();
            }
        } catch (Exception ex) {

        }
    }

    private void getShipmentReport() {
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        ShipmentReportinput input= new ShipmentReportinput();
        input.setAxiomOrderNo(orderno);
        input.setUser(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<ShipmentReport> stringCall = apiService.getshipmentreport(input);
        stringCall.enqueue(new Callback<ShipmentReport>() {
            @Override
            public void onResponse(Call<ShipmentReport> call, Response<ShipmentReport> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    if(response.body().getReport().size()>0){
                        convertbase64topdf(response.body().getReport().get(0).getObosdData());
                    }
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<ShipmentReport> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });

    }

    private void getdeliveryReport() {
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClientforinvoice().create(APIInterface.class);
        InvoiceInput input= new InvoiceInput();
        input.setOrderNo(order_seqno);
        startLoader(getString(R.string.loading), this);
//        input.setUser(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<Invoice> stringCall = apiService.getDeliveryReport(input);
        stringCall.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    if(response.body().getInvoice().length()>0){
                        convertbase64topdf(response.body().getInvoice());
                    }
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }


    private void getInvoice() {
        if(!internetavailable(this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClientforinvoice().create(APIInterface.class);
        InvoiceInput input= new InvoiceInput();
        input.setOrderNo(order_seqno);
        startLoader(getString(R.string.loading), this);
//        input.setUser(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<Invoice> stringCall = apiService.getInvoice(input);
        stringCall.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                stopLoader();
                if (response.body().getStatus().getOutResult()) {
                    if(response.body().getInvoice().length()>0){
                        convertbase64topdf(response.body().getInvoice());
                    }
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }

    void convertbase64topdf(String data){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        final File dwldsPath = new File(Environment.getExternalStorageDirectory()+"/Axiom_bikers/" + "bill" + ".pdf");
        byte[] pdfAsBytes = Base64.decode(data,0);
        try {
            FileOutputStream os = new FileOutputStream(dwldsPath, false);
            os.write(pdfAsBytes);
            os.flush();
            os.close();
            openreport();
        }
        catch (Exception e){
            ShowToast(e.toString(),OrderDetailActivity.this);
        }
    }


    void openreport() {
        File file = new File(Environment.getExternalStorageDirectory()+"/Axiom_bikers/" + "bill" + ".pdf");
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), "application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }

    void shipmentTracking(){
        if(!internetavailable(OrderDetailActivity.this)) {
            ShowToast(getString(R.string.nointernet), OrderDetailActivity.this);
            return;
        }
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        CourierOrder input= new CourierOrder();
        input.setAxiomOrderNo(orderno);
        startLoader(getString(R.string.loading), this);
        input.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<CourierDetails> stringCall = apiService.shipmentTracker(input);
        stringCall.enqueue(new Callback<CourierDetails>() {
            @Override
            public void onResponse(Call<CourierDetails> call, Response<CourierDetails> response) {
                stopLoader();
                if (response.body()!=null &&  response.body().getStatus()!=null && response.body().getStatus().getOutResult()) {
                    if(response.body().getCourierTracker().size()>0){
                       ShowTracketList(response.body().getCourierTracker());
                    }
                    else
                        ShowToast("Shipment tracking details not available.", OrderDetailActivity.this);
                } else  if (response.body()!=null &&  response.body().getStatus()!=null ) {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), OrderDetailActivity.this);
                }

            }

            @Override
            public void onFailure(Call<CourierDetails> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), OrderDetailActivity.this);
            }
        });
    }

    private void ShowTracketList(List<CourierTracker> courierTracker) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = getLayoutInflater().inflate(R.layout.dialog_tracker, null);
        RecyclerView lv = (RecyclerView) view.findViewById(R.id.custom_list);
        dialog.setCancelable(true);
        // Change MyActivity.this and myListOfItems to your own values
        CourierTRackerAdaptor clad = new CourierTRackerAdaptor(OrderDetailActivity.this, courierTracker);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lv.setLayoutManager(mLayoutManager);
        lv.setItemAnimator(new DefaultItemAnimator());
        lv.setAdapter(clad);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }
}