package com.axiom.fulfillment.adaptor;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.model.Price;

import java.util.List;

public class PriceAdaptor extends RecyclerView.Adapter<PriceAdaptor.MyViewHolder> {

        List<Price> price;
        Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView stockdisc,stockchannel, stockqty;

    public MyViewHolder(View view) {
        super(view);
        stockdisc = view.findViewById(R.id.stock_disc);
        stockchannel = view.findViewById(R.id.stockchannel);
        stockqty = view.findViewById(R.id.stock_qty);
     }
    }


    public PriceAdaptor(Context ctx, List<Price> price) {
        this.price = price;
        this.ctx = ctx;
    }

    @Override
    public PriceAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_item, parent, false);
        return new PriceAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PriceAdaptor.MyViewHolder holder, final int position) {
        Price or = price.get(position);
        holder.stockdisc.setText(or.getItmrItemDescription());
        holder.stockchannel.setText(or.getItmrItemCode());
        holder.stockqty.setText(String.valueOf(or.getRspeSellpWtTax())+" "+ or.getAcrmCurrCode());
    }

    @Override
    public int getItemCount() {
        return price.size();
    }
}
