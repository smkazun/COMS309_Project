package com.example.admin.callardar.Classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.callardar.DayActivity;
import com.example.admin.callardar.EventDetailActivity;
import com.example.admin.callardar.R;

import java.util.ArrayList;


public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.ViewHolder>{


    private Context mContext;
    private ArrayList<Event> mList;
    private CardView cardview;
    ArrayList<View> EventView;

    public EventAdapter(Context context, ArrayList<Event> list){
        mContext = context;
        mList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.eventlist, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Event contact = mList.get(position);

        // Set item views based on your views and data model
        TextView item_name = holder.item_name;
        TextView item_time = holder.item_time;
        TextView item_date = holder.item_date;
        ImageView item_img = holder.item_image;

        item_name.setText(contact.getItem_title());
        item_time.setText(contact.getItem_desc());
        item_date.setText(contact.getItem_date());
        item_img.setImageResource(contact.getItem_image());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//              mContext.startActivity(new Intent(mContext, EventDetailActivity.class));
//
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView item_image;
        public TextView item_name,item_time,item_date;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            item_image = itemView.findViewById(R.id.rv_item_img);
            item_name = itemView.findViewById(R.id.rv_Etitle);
            item_time = itemView.findViewById(R.id.rv_Desc);
            item_date = itemView.findViewById(R.id.rv_date);
            cardview = (CardView) itemView.findViewById(R.id.card_view);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Toast.makeText(mContext.getApplicationContext(),"Its "+ position, Toast.LENGTH_LONG).show();
                    mContext.startActivity(new Intent(mContext, EventDetailActivity.class));
//                    mContext.startActivity(new Intent(mContext, EventDetailActivity.class).putExtra("e", position));

                }
            });

        }

    }
}
