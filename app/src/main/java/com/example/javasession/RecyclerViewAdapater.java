package com.example.javasession;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapater extends RecyclerView.Adapter<RecyclerViewAdapater.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapater";

    ArrayList<Alarm> alarms = new ArrayList<>();

    public RecyclerViewAdapater() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_recycler_view,viewGroup,false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        viewHolder.alarmMessage.setText(alarms.get(i).getMessage());
        String time = alarms.get(i).getHours() + ":" + alarms.get(i).getMinutes();
        viewHolder.alarmTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView alarmMessage;
        private TextView alarmTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            alarmMessage = (TextView) itemView.findViewById(R.id.alarmmessage);
            alarmTime = (TextView) itemView.findViewById(R.id.alarmtime);
        }
    }

    public void setAlarms(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
        notifyDataSetChanged();
    }
}
