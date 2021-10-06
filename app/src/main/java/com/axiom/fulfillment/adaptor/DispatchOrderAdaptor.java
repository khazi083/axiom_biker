package com.axiom.fulfillment.adaptor;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.helper.OrderActionListner;
import com.axiom.fulfillment.model.DispatchOrderitem;

import java.text.SimpleDateFormat;
import java.util.List;

public class DispatchOrderAdaptor extends RecyclerView.Adapter<DispatchOrderAdaptor.MyViewHolder> {

    private List<DispatchOrderitem> dispatchOrderitems;
    OrderActionListner listner;
    Boolean istab=true;
    Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView orderno, cust_name,purchaseno,orderdate,order_amt,msource,seqno;
        RelativeLayout mainlayout;
        ImageView deliveryicon;

        public MyViewHolder(View view) {
            super(view);
            orderno = view.findViewById(R.id.orderno);
            cust_name = view.findViewById(R.id.cust_name);
            purchaseno=view.findViewById(R.id.purchaseno);
            orderdate=view.findViewById(R.id.orderdate);
            order_amt=view.findViewById(R.id.order_amt);
            msource=view.findViewById(R.id.source);
            mainlayout=view.findViewById(R.id.mainlayout);
            deliveryicon=view.findViewById(R.id.deliveryicon);
            seqno=view.findViewById(R.id.seqno);
        }
    }

    public DispatchOrderAdaptor(Context ctx, List<DispatchOrderitem> dispatchOrderitems, OrderActionListner deliverylistner,Boolean istab) {
        this.dispatchOrderitems = dispatchOrderitems;
        listner=deliverylistner;
        this.istab=istab;
        this.ctx=ctx;
    }

    @Override
    public DispatchOrderAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dispatch_item, parent, false);

        return new DispatchOrderAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DispatchOrderAdaptor.MyViewHolder holder, final int position) {
        DispatchOrderitem or = dispatchOrderitems.get(position);
        holder.orderno.setText(or.getObohOrderNo());
        holder.cust_name.setText(or.getObohCustFullName());
        if(or.getObohOrderAmountTax()!=null)
            holder.order_amt.setText(or.getObohOrderAmountTax()+" "+or.getObohCurrency());
        else if(or.getObohOrderTotAmount()!=null)
            holder.order_amt.setText(or.getObohOrderTotAmount()+" "+or.getObohCurrency());

        holder.orderdate.setText(chnagedateformat(or.getObohOrderDate().replace("T"," ").substring(0,10)));
        if(or.getObohPartnerOrderNo()!=null && !or.getObohPartnerOrderNo().isEmpty())
            holder.purchaseno.setText(or.getObohPartnerOrderNo());


        holder.msource.setText(or.getObohOrderSource());
        if(istab) {
            holder.deliveryicon.setVisibility(View.VISIBLE);

            if (or.getStockLocationCode() != null && !or.getStockLocationCode().isEmpty()) {
                holder.seqno.setVisibility(View.VISIBLE);
                holder.seqno.setText(or.getStockLocationCode());
            }
            if (or.getObohOrderSource().contains("HUAWEI"))
                holder.deliveryicon.setImageResource(R.drawable.huaweiksa);
            else if (or.getObohOrderSource().contains("NOON"))
                holder.deliveryicon.setImageResource(R.drawable.noon);
            else if (or.getObohOrderSource().contains("SWITCH"))
                holder.deliveryicon.setImageResource(R.drawable.aeswitch1);
            else if (or.getObohOrderSource().contains("HONOR"))
                holder.deliveryicon.setImageResource(R.drawable.honorksa);
            else if (or.getObohOrderSource().contains("HTCVIVE"))
                holder.deliveryicon.setImageResource(R.drawable.htcviveuae);
            else if (or.getObohOrderSource().contains("HTC"))
                holder.deliveryicon.setImageResource(R.drawable.htcuae);
            else if (or.getObohOrderSource().contains("WADI"))
                holder.deliveryicon.setImageResource(R.drawable.wadiksa);
            else if (or.getObohOrderSource().contains("SAMSUNG"))
                holder.deliveryicon.setImageResource(R.drawable.samsung);

        }
        else {
            holder.deliveryicon.setVisibility(View.GONE);
            holder.seqno.setVisibility(View.GONE);
        }



        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.ondeliver(position);
            }
        });
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

    @Override
    public int getItemCount() {
        return dispatchOrderitems.size();
    }
}
