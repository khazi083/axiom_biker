package com.axiom.fulfillment.api;

import com.axiom.fulfillment.model.BikerList;
import com.axiom.fulfillment.model.ButtonStatus;
import com.axiom.fulfillment.model.ButtonStatusResult;
import com.axiom.fulfillment.model.CancelOrder;
import com.axiom.fulfillment.model.CancelPosxOrder;
import com.axiom.fulfillment.model.CommonApiResponse;
import com.axiom.fulfillment.model.CourierDetails;
import com.axiom.fulfillment.model.CourierList;
import com.axiom.fulfillment.model.CourierListInput;
import com.axiom.fulfillment.model.CourierOrder;
import com.axiom.fulfillment.model.CourierdispatchResponse;
import com.axiom.fulfillment.model.DashboardResponse;
import com.axiom.fulfillment.model.DeliveryRequest;
import com.axiom.fulfillment.model.DispatchOrders;
import com.axiom.fulfillment.model.ErpPriceCheck;
import com.axiom.fulfillment.model.ErpStockInput;
import com.axiom.fulfillment.model.Invoice;
import com.axiom.fulfillment.model.InvoiceInput;
import com.axiom.fulfillment.model.ItemdescInput;
import com.axiom.fulfillment.model.ItemdescList;
import com.axiom.fulfillment.model.Locationlist;
import com.axiom.fulfillment.model.OrderDetailsInput;
import com.axiom.fulfillment.model.OrderStatusDetails;
import com.axiom.fulfillment.model.Order_details;
import com.axiom.fulfillment.model.PosXorders;
import com.axiom.fulfillment.model.ShipmentReport;
import com.axiom.fulfillment.model.ShipmentReportinput;
import com.axiom.fulfillment.model.StockList;
import com.axiom.fulfillment.model.StockPriceList;
import com.axiom.fulfillment.model.UserDetails;
import com.axiom.fulfillment.model.UserLogin;
import com.axiom.fulfillment.model.UserLoginResponse;
import com.axiom.fulfillment.model.bikerdispatch;
import com.axiom.fulfillment.model.bikerorder;
import com.axiom.fulfillment.model.channelinput;
import com.axiom.fulfillment.model.channellist;
import com.axiom.fulfillment.model.createcourierorder;
import com.axiom.fulfillment.model.menuitems;
import com.axiom.fulfillment.model.menurole;
import com.axiom.fulfillment.model.newuser;
import com.axiom.fulfillment.model.orderApi;
import com.axiom.fulfillment.model.organizationList;
import com.axiom.fulfillment.model.tokenresponse;
import com.axiom.fulfillment.model.userrolesList;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @Headers({"content-type: application/x-www-form-urlencoded;charset=UTF-8",})
    @POST("token")
    Call<tokenresponse> getaccesstoken(@Field("grant_type") String grant_type,
                                       @Field("username") String username,
                                       @Field("password") String password);

    @POST("api/login")
    Call<UserLoginResponse> loginapi(@Body UserLogin user);

    @GET("api/system/roles")
    Call<userrolesList> getUserRoles();

    @POST("api/fulfilment/register")
    Call<CommonApiResponse> newRegistration(@Body newuser user);

    @POST("api/menu/side")
    Call<menuitems> getmenulist(@Body menurole mn);

    @POST("api/biker/pending/order")
    Call<bikerorder> getorderlist(@Body orderApi orderApi);

    @POST("api/biker/order/update")
    Call<CommonApiResponse> deliverorder(@Body DeliveryRequest deliveryRequest);


    @POST("api/Order/Details")
    Call<Order_details> orderdetails(@Body OrderDetailsInput OrderDetailsInput);

    @POST("api/Dashboard/bikerOrders")
    Call<DashboardResponse> getDashboardData(@Body UserDetails user);

    @POST("api/Orders/fulfilment/GetProcessingOrders")
    Call<DispatchOrders> dispatchorderdetails(@Body orderApi orderApi);

    @POST("api/logistics/axiombikers")
    Call<BikerList> getBikerlist(@Body CourierListInput user);

    @POST("api/logistics/GetSystemCouriers")
    Call<CourierList> getCourierlist(@Body CourierListInput courier);

    @POST("api/biker/order")
    Call<CommonApiResponse> createbikerorder(@Body bikerdispatch biker);

    @POST("api/logistics/ICourierAWB")
    Call<CourierdispatchResponse> createCourierrorder(@Body createcourierorder courierorder);

    @POST("api/Orders/fulfilment/OrderActions")
    Call<ButtonStatusResult> getButtonStatus(@Body ButtonStatus userdata);

    @POST("api/Orders/fulfilment/CancelOrder")
    Call<CommonApiResponse> cancelOrder(@Body CancelOrder userdata);

    @POST("api/masters/agt/Organisations")
    Call<organizationList> getorglist(@Body ButtonStatus userdata);

    @POST("api/masters/agt/channels")
    Call<channellist> getchannellist(@Body channelinput input);

    @POST("api/Locations/LocationMaster")
    Call<Locationlist> getlocationlist(@Body channelinput input);

    @POST("api/Stocks/GetErpStockOnHand")
    Call<StockList> getErpStock(@Body ErpStockInput input);

    @POST("api/products/search")
    Call<ItemdescList> getItemdesc(@Body ItemdescInput input);


    @POST("api/Pricing/GetRpmPrices")
    Call<StockPriceList> getStockPrice(@Body ErpPriceCheck input);


    @POST("api/logistics/GetShipmentReport")
    Call<ShipmentReport> getshipmentreport(@Body ShipmentReportinput input);

    @POST("Invoice")
    Call<Invoice> getInvoice(@Body InvoiceInput input);

    @POST("api/active/Orders")
    Call<PosXorders> getPosxorders(@Body orderApi orderApi);

    @POST("api/active/order/cancel")
    Call<CommonApiResponse> cancelPosxOrder(@Body CancelPosxOrder userdata);

    @POST("api/logistics/IntegratedCourierTracker")
    Call<CourierDetails> shipmentTracker(@Body CourierOrder input);

    @POST("DeliveryReceipt")
    Call<Invoice> getDeliveryReport(@Body InvoiceInput input);


    @POST("api/logistics/axiombikers/get")
    Call<OrderStatusDetails> getorderstatus(@Body ButtonStatus input);

}
