package com.axiom.fulfillment.view;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.adaptor.PriceAdaptor;
import com.axiom.fulfillment.adaptor.StockAdaptor;
import com.axiom.fulfillment.api.APIClient;
import com.axiom.fulfillment.api.APIInterface;
import com.axiom.fulfillment.helper.UserSharedPreferences;
import com.axiom.fulfillment.model.ButtonStatus;
import com.axiom.fulfillment.model.ErpPriceCheck;
import com.axiom.fulfillment.model.ErpStockInput;
import com.axiom.fulfillment.model.ItemDetail;
import com.axiom.fulfillment.model.ItemdescInput;
import com.axiom.fulfillment.model.ItemdescList;
import com.axiom.fulfillment.model.LocationMaster;
import com.axiom.fulfillment.model.Locationlist;
import com.axiom.fulfillment.model.Organization;
import com.axiom.fulfillment.model.Price;
import com.axiom.fulfillment.model.StockList;
import com.axiom.fulfillment.model.StockPriceList;
import com.axiom.fulfillment.model.Stockitem;
import com.axiom.fulfillment.model.UserDetails;
import com.axiom.fulfillment.model.channelinput;
import com.axiom.fulfillment.model.channellist;
import com.axiom.fulfillment.model.org_Channels;
import com.axiom.fulfillment.model.organizationList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.axiom.fulfillment.helper.constants.CHECKTYPE;
import static com.axiom.fulfillment.helper.constants.ERPRICE;
import static com.axiom.fulfillment.helper.constants.ERPSTOCK;

public class Erp_Stock extends BaseActivity  {
    Spinner org_list,channel;
    AutoCompleteTextView autolocations;
    AutoCompleteTextView itemcode;
    UserSharedPreferences upref;
    private List<Organization> organization;
    private List<ItemDetail> itemDetail;
    List<org_Channels> channellist;
    List<LocationMaster> Location;
    List<Stockitem> Stocks;
    private List<Price> prices;
    ArrayList<String> orgname,channelname,locationname;
    ArrayAdapter<String> orgAdapter,chaAdaptor,locAdaptor;
    Button stock_search,stock_clear;
    RecyclerView stock_itemList;
    private StockAdaptor stockAdaptor;
    private PriceAdaptor priceAdaptor;
    private LinearLayout stocksearchheader;
    private View diver;
    TextView pd_id, qty_price;
    String Check_type;
    List<String> suggestions = new ArrayList<>();
    ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erp_stock);
        org_list=findViewById(R.id.org_list);
        channel=findViewById(R.id.channel);
        autolocations=findViewById(R.id.locationlist);
        itemcode=findViewById(R.id.itemcode);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, suggestions);
        itemcode.setAdapter(adapter);
        itemcode.setThreshold(3);

        itemcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                retrieveData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this will call your method every time the user stops typing, if you want to call it for each letter, call it in onTextChanged

            }
        });


        stocksearchheader=findViewById(R.id.stocksearchheader);
        diver=findViewById(R.id.diver);
        pd_id=findViewById(R.id.pd_id);
        qty_price=findViewById(R.id.qty_price);
        upref = new UserSharedPreferences(this);
        orgname=new ArrayList<>();
        channellist=new ArrayList<>();
        Location=new ArrayList<>();
        channelname=new ArrayList<>();
        orgname=new ArrayList<>();
        locationname=new ArrayList<>();
        Stocks=new ArrayList<>();
        prices= new ArrayList<>();
        stock_itemList=findViewById(R.id.stock_itemList);
        getorglist();
        autolocations.setEnabled(false);
        autolocations.setText("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Check_type=getIntent().getStringExtra(CHECKTYPE);
        if(Check_type.equalsIgnoreCase(ERPSTOCK))
            getSupportActionBar().setTitle(getString(R.string.stock_check));
        else if(Check_type.equalsIgnoreCase(ERPRICE))
            getSupportActionBar().setTitle(getString(R.string.price_check));

        stock_search=findViewById(R.id.stock_search);

        stock_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check_type.equalsIgnoreCase(ERPSTOCK))
                    SearchStock();
                else if(Check_type.equalsIgnoreCase(ERPRICE))
                    PriceCheck();
            }
        });
        stock_clear=findViewById(R.id.stock_clear);
        stocksearchheader.setVisibility(View.INVISIBLE);
        diver.setVisibility(View.INVISIBLE);

        stock_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channelname.clear();
                locationname.clear();
                autolocations.clearListSelection();
                autolocations.setEnabled(false);
                autolocations.setText("");
                Stocks.clear();
                prices.clear();
                itemcode.setText("");
                org_list.setSelection(0);
                if(chaAdaptor!=null)
                    chaAdaptor.notifyDataSetChanged();
                if(locAdaptor!=null)
                    locAdaptor.notifyDataSetChanged();
                if(stockAdaptor!=null)
                    stockAdaptor.notifyDataSetChanged();
                if(priceAdaptor!=null)
                    priceAdaptor.notifyDataSetChanged();
                stocksearchheader.setVisibility(View.INVISIBLE);
                diver.setVisibility(View.INVISIBLE);

            }
        });

        org_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    channelname.clear();
                    locationname.clear();
                    autolocations.setEnabled(false);
                    autolocations.clearListSelection();
                    autolocations.setText("");
                    if(chaAdaptor!=null)
                        chaAdaptor.notifyDataSetChanged();
                    if(locAdaptor!=null)
                        locAdaptor.notifyDataSetChanged();
                    return;
                }
                else{
                    getchannellist(organization.get(position-1).getAomsOrganizationCode(),organization.get(position-1).getAbudBusinessUnitCode());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        channel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    locationname.clear();
                    autolocations.setEnabled(false);
                    autolocations.clearListSelection();
                    autolocations.setText("");
                    if(locAdaptor!=null)
                        locAdaptor.notifyDataSetChanged();
                    return;
                }
                else{
                    autolocations.setEnabled(true);
                    autolocations.clearListSelection();
                    autolocations.setText("");
                    getloclist(channellist.get(position-1).getAomsOrganizationCode(),channellist.get(position-1).getAbudBusinessUnitCode(),channellist.get(position-1).getAclmChannelCode());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void retrieveData(String s){
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        ItemdescInput input = new ItemdescInput();
        input.setProductDesc(s);
        input.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<ItemdescList> stringCall = apiService.getItemdesc(input);
        stringCall.enqueue(new Callback<ItemdescList>() {
            @Override
            public void onResponse(Call<ItemdescList> call, Response<ItemdescList> response) {
                stopLoader();
                if (response.body()!=null && response.body().getStatus().getOutResult()) {
                    itemDetail = response.body().getItemDetail();
                    suggestions.clear();
                    for (int i = 0; i < itemDetail.size(); i++)
                        suggestions.add(itemDetail.get(i).getItmrItemDescription());
                    adapter = new ArrayAdapter<>(Erp_Stock.this,
                            android.R.layout.simple_dropdown_item_1line, suggestions);
                    itemcode.setAdapter(adapter);
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), Erp_Stock.this);
                }
            }

            @Override
            public void onFailure(Call<ItemdescList> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), Erp_Stock.this);
            }
        });

    }

    private void PriceCheck() {

        int locationPos=-1;

        if(!itemcode.getText().toString().isEmpty()){
            if(locationname.contains( autolocations.getText().toString())){
                locationPos=locationname.indexOf(autolocations.getText().toString());
            }
        }
        else if(locationname.contains( autolocations.getText().toString())){
            locationPos=locationname.indexOf(autolocations.getText().toString());
        }
        else {
            ShowToast("Please select all details", Erp_Stock.this);
            return;
        }
        try {
                APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
                ErpPriceCheck input = new ErpPriceCheck();
                if(channel.getSelectedItemPosition()>0)
                    input.setAclmChannelCode(channellist.get(channel.getSelectedItemPosition() - 1).getAclmChannelCode());
                if(locationPos!=-1)
                    input.setAlnmLocationCode(Location.get(locationPos).getAlnmLocationCode());
                if (!itemcode.getText().toString().isEmpty()) {
                    for(int i=0; i< suggestions.size();i++){
                        if(itemcode.getText().toString().equalsIgnoreCase(suggestions.get(i))){
                            input.setItmrItemCode(itemDetail.get(i).getItmrItemCode());
                        }
                    }
                }
                input.setAomsOrganizationCode(organization.get(org_list.getSelectedItemPosition() - 1).getAomsOrganizationCode());
                Call<StockPriceList> stringCall = apiService.getStockPrice(input);
                startLoader("loading Product list", this);

                stringCall.enqueue(new Callback<StockPriceList>() {
                    @Override
                    public void onResponse(Call<StockPriceList> call, Response<StockPriceList> response) {
                        stopLoader();
                        if (response.body() != null && response.body().getStatus().getOutResult()) {
                            prices.clear();
                            prices = response.body().getPrices();
                            if (prices.size() > 0)
                                initviewforPrice();
                            ShowToast(response.body().getStatus().getOutMessage(), Erp_Stock.this);
                        } else {
                            ShowToast("Error :" + response.body().getStatus().getOutMessage(), Erp_Stock.this);
                        }
                    }

                    @Override
                    public void onFailure(Call<StockPriceList> call, Throwable t) {
                        stopLoader();
                        ShowToast(getString(R.string.api_fail), Erp_Stock.this);
                    }
                });
        }
        catch (Exception e){
            stopLoader();
            ShowToast(getString(R.string.api_fail), Erp_Stock.this);
        }
    }

    private void initviewforPrice() {
        pd_id.setText("Product Code");
        qty_price.setText("Price With Tax");
        stocksearchheader.setVisibility(View.VISIBLE);
        diver.setVisibility(View.VISIBLE);
        priceAdaptor = new PriceAdaptor(Erp_Stock.this, prices);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        stock_itemList.setLayoutManager(mLayoutManager);
        stock_itemList.setItemAnimator(new DefaultItemAnimator());
        stock_itemList.setAdapter(priceAdaptor);
    }

    private void SearchStock() {
        int locationPos=-1;
        if(!itemcode.getText().toString().isEmpty()){
            if(locationname.contains( autolocations.getText().toString())){
                locationPos=locationname.indexOf(autolocations.getText().toString());
            }
        }
        else if(locationname.contains( autolocations.getText().toString())){
            locationPos=locationname.indexOf(autolocations.getText().toString());
        }
        else {
            ShowToast("Please select all details", Erp_Stock.this);
            return;
        }

//       if(org_list.getSelectedItemPosition()>0 && channel.getSelectedItemPosition()>0 && locationPos>=0){
           APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
           ErpStockInput input = new ErpStockInput();
           if(channel.getSelectedItemPosition()>0)
            input.setAclmChannel(channellist.get(channel.getSelectedItemPosition()-1).getAclmChannelCode());
           if(locationPos!=-1)
            input.setAlnmLocationCode(Location.get(locationPos).getAlnmLocationCode());
           if (!itemcode.getText().toString().isEmpty()) {
               for(int i=0; i< suggestions.size();i++){
                   if(itemcode.getText().toString().equalsIgnoreCase(suggestions.get(i))){
                       input.setProductCode(itemDetail.get(i).getItmrItemCode());
                   }
               }
           }
           input.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
           Call<StockList> stringCall = apiService.getErpStock(input);
           startLoader("loading Product list", this);
           stringCall.enqueue(new Callback<StockList>() {
               @Override
               public void onResponse(Call<StockList> call, Response<StockList> response) {
                   stopLoader();
                   if (response.body()!=null && response.body().getStatus().getOutResult()) {
                       Stocks.clear();
                       Stocks = response.body().getStocks();
                       if(Stocks.size()>0)
                        initview();
                       ShowToast(response.body().getStatus().getOutMessage(), Erp_Stock.this);
                   } else {
                       ShowToast("Error :" + response.body().getStatus().getOutMessage(), Erp_Stock.this);
                   }
               }

               @Override
               public void onFailure(Call<StockList> call, Throwable t) {
                   stopLoader();
                   ShowToast(getString(R.string.api_fail), Erp_Stock.this);
               }
           });
//       }
    }

    private void initview() {
        pd_id.setText("Product Code/ItemId");
        qty_price.setText("Quantity");
        stocksearchheader.setVisibility(View.VISIBLE);
        diver.setVisibility(View.VISIBLE);
        stockAdaptor = new StockAdaptor(Erp_Stock.this, Stocks);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        stock_itemList.setLayoutManager(mLayoutManager);
        stock_itemList.setItemAnimator(new DefaultItemAnimator());
        stock_itemList.setAdapter(stockAdaptor);
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

    private void getorglist() {
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        ButtonStatus input = new ButtonStatus();
        input.setUserRole(upref.getKeyUserRole());
        input.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<organizationList> stringCall = apiService.getorglist(input);
        startLoader("loading Organization list", this);
        stringCall.enqueue(new Callback<organizationList>() {
            @Override
            public void onResponse(Call<organizationList> call, Response<organizationList> response) {
                stopLoader();
                if (response.body()!=null && response.body().getStatus().getOutResult()) {
                    organization = response.body().getOrganization();
                    orgname.clear();
                    orgname.add("select organization");
                    for (int i = 0; i < organization.size(); i++)
                        orgname.add(organization.get(i).getAomsOrganizationName());
                    orgAdapter = new ArrayAdapter<String>(Erp_Stock.this, android.R.layout.simple_spinner_item, orgname);
                    orgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    org_list.setAdapter(orgAdapter);
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), Erp_Stock.this);
                }
            }

            @Override
            public void onFailure(Call<organizationList> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), Erp_Stock.this);
            }
        });
    }

    private void getchannellist(String orgcode,String bizunit) {
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        channelinput input = new channelinput();
        input.setAomsOrganizationCode(orgcode);
        input.setAbudBusinessUnitCode(bizunit);
        input.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<channellist> stringCall = apiService.getchannellist(input);
        startLoader("loading Channel list", this);
        stringCall.enqueue(new Callback<channellist>() {
            @Override
            public void onResponse(Call<channellist> call, Response<channellist> response) {
                stopLoader();
                if (response.body()!=null && response.body().getStatus().getOutResult()) {
                    channellist = response.body().getChannels();
                    channelname.clear();
                    channelname.add("select channel");
                    for (int i = 0; i < channellist.size(); i++)
                        channelname.add(channellist.get(i).getAclmChannelDesc());
                    chaAdaptor = new ArrayAdapter<String>(Erp_Stock.this, android.R.layout.simple_spinner_item, channelname);
                    chaAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    channel.setAdapter(chaAdaptor);
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), Erp_Stock.this);
                }
            }

            @Override
            public void onFailure(Call<channellist> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), Erp_Stock.this);
            }
        });
    }

    private void getloclist(String orgcode,String bizunit, String channelcode) {
        APIInterface apiService = new APIClient(this).getClient().create(APIInterface.class);
        channelinput input = new channelinput();
        input.setAomsOrganizationCode(orgcode);
        input.setAbudBusinessUnitCode(bizunit);
        input.setAclmChannelCode(channelcode);
        input.setUserDetails(new UserDetails(upref.getUserId(), upref.getFirstName(), upref.getKeyUserCode(), upref.getKeyEmpCode()));
        Call<Locationlist> stringCall = apiService.getlocationlist(input);
        startLoader("loading Location list", this);
        stringCall.enqueue(new Callback<Locationlist>() {
            @Override
            public void onResponse(Call<Locationlist> call, Response<Locationlist> response) {
                stopLoader();
                if (response.body()!=null && response.body().getStatus().getOutResult()) {
                    Location = response.body().getLocation();
                    locationname.clear();
                    for (int i = 0; i < Location.size(); i++)
                        locationname.add(Location.get(i).getAlnmLocationCode()+" : "+Location.get(i).getAlnmLocationShortDesc());
                    locAdaptor = new ArrayAdapter<String>(Erp_Stock.this, android.R.layout.simple_list_item_1, locationname);
                    autolocations.setAdapter(locAdaptor);
                    autolocations.setThreshold(0);
                } else {
                    ShowToast("Error :" + response.body().getStatus().getOutMessage(), Erp_Stock.this);
                }
            }

            @Override
            public void onFailure(Call<Locationlist> call, Throwable t) {
                stopLoader();
                ShowToast(getString(R.string.api_fail), Erp_Stock.this);
            }
        });
    }
    }

