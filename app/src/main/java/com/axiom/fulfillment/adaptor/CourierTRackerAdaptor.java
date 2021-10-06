package com.axiom.fulfillment.adaptor;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.axiom.fulfillment.R;
import com.axiom.fulfillment.model.CourierTracker;
import java.util.List;

public class CourierTRackerAdaptor extends RecyclerView.Adapter<CourierTRackerAdaptor.MyViewHolder> {

    List<CourierTracker> courierlist;
    Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date,station, act;

        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.courier_date);
            station = view.findViewById(R.id.courier_station);
            act = view.findViewById(R.id.courier_activity);
        }
    }


    public CourierTRackerAdaptor(Context ctx, List<CourierTracker> courierlist) {
        this.courierlist = courierlist;
        this.ctx = ctx;
    }

    @Override
    public CourierTRackerAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courieritem, parent, false);
        return new CourierTRackerAdaptor.MyViewHolder(itemView);
    }

    @Override
        public void onBindViewHolder(CourierTRackerAdaptor.MyViewHolder holder, final int position) {
        CourierTracker or = courierlist.get(position);
        holder.date.setText(or.getDate().replace("T"," "));
        holder.station.setText(or.getStationCode());
        holder.act.setText(String.valueOf(or.getActivity()));
    }

    @Override
    public int getItemCount() {
        return courierlist.size();
    }
}

