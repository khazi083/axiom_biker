package com.axiom.fulfillment.adaptor;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.axiom.fulfillment.R;
import com.axiom.fulfillment.model.OrderItemDetails;

import java.util.List;

public class OrderDetailsAdaptor extends RecyclerView.Adapter<OrderDetailsAdaptor.MyViewHolder> {

    private List<OrderItemDetails> deliveryorderList;
    Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pdname,pdnamedesc,pdqty,pdunitprice,tax,total;
        LinearLayout layout_top;

        public MyViewHolder(View view) {
            super(view);
            pdname = view.findViewById(R.id.pdName);
            pdnamedesc = view.findViewById(R.id.pdNamedis);
            pdqty=view.findViewById(R.id.pdqty);
            pdunitprice=view.findViewById(R.id.pdprice);
            tax=view.findViewById(R.id.pdtax);
            total=view.findViewById(R.id.pdtotal);
            layout_top=view.findViewById(R.id.layout_top);
        }
    }

    public OrderDetailsAdaptor(Context ctx,List<OrderItemDetails> deliveryorderList) {
        this.deliveryorderList = deliveryorderList;
        this.ctx=ctx;
    }

    @Override
    public OrderDetailsAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_detail_item, parent, false);

        return new OrderDetailsAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderDetailsAdaptor.MyViewHolder holder, final int position) {
        OrderItemDetails or = deliveryorderList.get(position);
        holder.pdname.setText(or.getItemDescription());
        holder.pdnamedesc.setText(or.getItemCode());
        holder.pdqty.setText(String.valueOf(or.getQuantity().intValue()));
        holder.pdunitprice.setText(String.valueOf(or.getItemAmount()));
        holder.tax.setText(String.valueOf(or.getTaxAmount()));
        holder.total.setText(String.valueOf(or.getItemAmountTax()));
    }

    @Override
    public int getItemCount() {
        return deliveryorderList.size();
    }
}
