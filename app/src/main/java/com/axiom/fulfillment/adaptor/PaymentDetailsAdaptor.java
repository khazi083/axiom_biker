package com.axiom.fulfillment.adaptor;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.axiom.fulfillment.R;
import com.axiom.fulfillment.model.PaymentDetail;

import java.util.List;

public class PaymentDetailsAdaptor extends RecyclerView.Adapter<PaymentDetailsAdaptor.MyViewHolder> {

    private List<PaymentDetail> paymentdetailslist;
    Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pytype,pyamont,pycurrency,pylocalamt,paymentOnDelivery;

        public MyViewHolder(View view) {
            super(view);
            pytype = view.findViewById(R.id.pay_type);
            pyamont = view.findViewById(R.id.pay_amount);
            pycurrency=view.findViewById(R.id.pay_currency);
            pylocalamt=view.findViewById(R.id.pay_localamt);
            paymentOnDelivery=view.findViewById(R.id.paymentOnDelivery);
        }
    }

    public PaymentDetailsAdaptor(Context ctx, List<PaymentDetail> paymentdetailslist) {
        this.paymentdetailslist = paymentdetailslist;
        this.ctx=ctx;
    }

    @Override
    public PaymentDetailsAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_item, parent, false);

        return new PaymentDetailsAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaymentDetailsAdaptor.MyViewHolder holder, final int position) {
        PaymentDetail or = paymentdetailslist.get(position);
        holder.pytype.setText(or.getPaymentDesc());
        holder.pycurrency.setText(or.getCurrencyCode());
        holder.pyamont.setText(String.valueOf(or.getPaymentAmount()));
        holder.pylocalamt.setText(String.valueOf(or.getLocalAmount()));
        if(or.getPaymentOnDelivery()!=null) {
            if (or.getPaymentOnDelivery().equalsIgnoreCase("n"))
                holder.paymentOnDelivery.setText("No");
            else if (or.getPaymentOnDelivery().equalsIgnoreCase("y"))
                holder.paymentOnDelivery.setText("Yes");
            else
                holder.paymentOnDelivery.setText(or.getPaymentOnDelivery());
        }
    }

    @Override
    public int getItemCount() {
        return paymentdetailslist.size();
    }
}