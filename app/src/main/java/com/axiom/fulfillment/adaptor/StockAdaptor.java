package com.axiom.fulfillment.adaptor;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.model.Stockitem;

import java.util.List;

public class StockAdaptor extends RecyclerView.Adapter<StockAdaptor.MyViewHolder> {

    List<Stockitem> Stocks;
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


    public StockAdaptor(Context ctx, List<Stockitem> Stocks) {
        this.Stocks = Stocks;
        this.ctx = ctx;
    }

    @Override
    public StockAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_item, parent, false);
        return new StockAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StockAdaptor.MyViewHolder holder, final int position) {
        Stockitem or = Stocks.get(position);
        holder.stockdisc.setText(or.getProductDescription());
        holder.stockchannel.setText(or.getProductCode()+"\n"+String.valueOf(or.getErpItemId().intValue()));
        holder.stockqty.setText(String.valueOf(or.getAvailableStock()));
    }

    @Override
    public int getItemCount() {
        return Stocks.size();
    }
}
