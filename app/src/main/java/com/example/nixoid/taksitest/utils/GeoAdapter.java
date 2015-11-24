package com.example.nixoid.taksitest.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nixoid.taksitest.R;
import com.example.nixoid.taksitest.rest.models.GeoObject;

import java.util.List;

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.ViewHolder>  {

        List<GeoObject> geoObjects;
        private Context context;
        private ViewHolder.ClickListener clickListener;

        public GeoAdapter(List<GeoObject> geoObjects,  ViewHolder.ClickListener clickListener ){
            this.geoObjects = geoObjects;
            this.clickListener = clickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_geo, parent, false);
            context = parent.getContext();
            return new ViewHolder(v, clickListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            GeoObject geoObject = geoObjects.get(i);
            viewHolder.name.setText(geoObject.getDesc());
//            viewHolder.adress.setText(geoObject.getDesc());
        }

        @Override
        public int getItemCount() {
            return geoObjects.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
            private TextView name;
            private TextView adress;
            private ClickListener clickListener;

            public ViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.id_name);
//            name = (TextView) itemView.findViewById(R.id.id_adress);

                this.clickListener = clickListener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onItemClicked(getAdapterPosition());
            }

            public interface ClickListener {

                void onItemClicked(int position);

            }
        }
}



